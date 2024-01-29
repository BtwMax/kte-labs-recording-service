package com.example.KTElabs_test.dto.ticket;

import com.example.KTElabs_test.dto.doctor.ShortDoctorDto;
import com.example.KTElabs_test.dto.patient.ShortPatientDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDto {

    private long id;

    private ShortDoctorDto doctor;

    private ShortPatientDto patient;

    private LocalDate date;

    private String startTime;

    private Boolean available;
}
