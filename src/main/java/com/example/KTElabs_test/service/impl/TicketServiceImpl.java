package com.example.KTElabs_test.service.impl;

import com.example.KTElabs_test.dto.ticket.TicketDto;
import com.example.KTElabs_test.exception.BadRequestException;
import com.example.KTElabs_test.exception.NotFoundException;
import com.example.KTElabs_test.mapper.TicketMapper;
import com.example.KTElabs_test.model.Doctor;
import com.example.KTElabs_test.model.Patient;
import com.example.KTElabs_test.model.Ticket;
import com.example.KTElabs_test.repository.DoctorRepository;
import com.example.KTElabs_test.repository.PatientRepository;
import com.example.KTElabs_test.repository.TicketRepository;
import com.example.KTElabs_test.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.ticketRepository = ticketRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketDto> getDoctorAvailableTicketsForDate(long docId, String date)
            throws NotFoundException, DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parseDate;
        try {
            parseDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new BadRequestException("Неверный формат даты");
        }
        Doctor doctor = doctorRepository.findById(docId)
                .orElseThrow(() -> new NotFoundException("Доктор с id = " + docId + " не найден"));
        List<Ticket> tickets = ticketRepository.findAllByDoctorIdAndDateAndAvailableIsTrue(doctor.getId(), parseDate);
        return tickets.stream()
                .map(TicketMapper::toTicketDto)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDto changeAvailableForTicket(long ticketId, long patientId) throws NotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException("Пациент с id = " + patientId + " не найден"));
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Слот для записи с id = " + ticketId + " не найден"));
        if(!ticket.getAvailable()) {
            throw new BadRequestException("Данный слот записи уже занят");
        }
        ticket.setPatient(patient);
        ticket.setAvailable(false);
        ticketRepository.save(ticket);
        return TicketMapper.toTicketDto(ticket);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketDto> getAllPatientTicketsById(long patientId) throws NotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException("Пациент с id = " + patientId + " не найден"));
        List<Ticket> patientTickets = ticketRepository.findAllByPatientId(patient.getId());
        return patientTickets.stream()
                .map(TicketMapper::toTicketDto)
                .collect(Collectors.toList());
    }
}
