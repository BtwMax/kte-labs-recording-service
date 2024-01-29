package com.example.KTElabs_test.dto.patient;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortPatientDto {

    private long id;
    private String fullName;
}
