package com.example.KTElabs_test.dto.doctor;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortDoctorDto {

    private long id;
    private String fullName;
    private String speciality;
}
