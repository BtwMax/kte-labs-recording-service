package com.example.KTElabs_test.dto.patient;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDto {

    private long id;
    private UUID uuid;
    private String fullName;
    private LocalDate birthday;
    private String email;
    private String phoneNumber;
}
