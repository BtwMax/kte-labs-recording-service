package com.example.KTElabs_test.dto.doctor;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDto {

    private long id;
    private UUID uuid;
    private String fullName;
    private String speciality;
}
