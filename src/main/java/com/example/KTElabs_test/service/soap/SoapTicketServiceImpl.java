package com.example.KTElabs_test.service.soap;

import com.example.KTElabs_test.exception.BadRequestException;
import com.example.KTElabs_test.exception.NotFoundException;
import com.example.KTElabs_test.mapper.TicketMapper;
import com.example.KTElabs_test.model.Doctor;
import com.example.KTElabs_test.model.Ticket;
import com.example.KTElabs_test.repository.DoctorRepository;
import com.example.KTElabs_test.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SoapTicketServiceImpl implements SoapTicketService {

    private final TicketRepository ticketRepository;
    private final DoctorRepository doctorRepository;
    private static final int MAX_WORK_TIME = 8;
    private static final int ONE_HOUR_IN_MIN = 60;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    public SoapTicketServiceImpl(TicketRepository ticketRepository, DoctorRepository doctorRepository) {
        this.ticketRepository = ticketRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    @Transactional
    public void createTickets(long doctorId, LocalDateTime docStartTime, int duration, int count) throws
            BadRequestException, NotFoundException {
        if (count <= 0) {
            throw new BadRequestException("Количество слотов не может быть орицательным или равно 0");
        }
        if (LocalDateTime.now().isAfter(docStartTime)) {
            throw new BadRequestException("Нельзя составить расписание на уже прошедший день");
        }
        if (docStartTime.getHour() < 8) {
            throw new BadRequestException("Рабочий день начинается не раньше 8 утра");
        }
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("404: Доктор с id = " + doctorId + " не найден"));
        double workTime = (double) (duration * count) / ONE_HOUR_IN_MIN;
        if (workTime > MAX_WORK_TIME) {
            throw new BadRequestException("Рабочий день не может быть больше 8 часов");
        }
        LocalDate date = docStartTime.toLocalDate();
        List<Ticket> dateTickets = ticketRepository.findTicketsByDate(date);
        if (!dateTickets.isEmpty()) {
            throw new BadRequestException("На данную дату уже составлено расписание");
        }
        LocalDateTime startTime = docStartTime;
        String start = startTime.format(FORMATTER);
        List<Ticket> newTickets = new ArrayList<>();
        newTickets.add(TicketMapper.toTicket(doctor, date, start));
        for (int i = 0; i < count - 1; i++) {
            startTime = startTime.plusMinutes(duration);
            String stringTime = startTime.format(FORMATTER);
            newTickets.add(TicketMapper.toTicket(doctor, date, stringTime));
        }
        ticketRepository.saveAll(newTickets);

    }
}
