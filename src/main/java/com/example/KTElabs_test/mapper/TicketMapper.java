package com.example.KTElabs_test.mapper;

import com.example.KTElabs_test.dto.ticket.TicketDto;
import com.example.KTElabs_test.model.Doctor;
import com.example.KTElabs_test.model.Ticket;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class TicketMapper {

    public Ticket toTicket(Doctor doctor, LocalDate date, String startTime) {
        return Ticket.builder()
                .doctor(doctor)
                .patient(null)
                .date(date)
                .startTime(startTime)
                .available(true)
                .build();
    }

    public TicketDto toTicketDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId())
                .doctor(DoctorMapper.toShortDoctorDto(ticket.getDoctor()))
                .patient(ticket.getPatient() != null ? PatientMapper.toShortPatientDto(ticket.getPatient()) : null)
                .date(ticket.getDate())
                .startTime(ticket.getStartTime())
                .available(ticket.getAvailable())
                .build();
    }
}
