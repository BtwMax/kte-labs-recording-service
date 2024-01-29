package com.example.KTElabs_test.mapper;

import com.example.KTElabs_test.dto.patient.NewPatientDto;
import com.example.KTElabs_test.dto.patient.PatientDto;
import com.example.KTElabs_test.dto.patient.ShortPatientDto;
import com.example.KTElabs_test.model.Patient;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class PatientMapper {

    public Patient toPatient(NewPatientDto patientDto, UUID uuid) {
        return Patient.builder()
                .uuid(uuid)
                .fullName(patientDto.getFullName())
                .birthday(patientDto.getBirthday())
                .email(patientDto.getEmail() != null ? patientDto.getEmail() : null)
                .phoneNumber(patientDto.getPhoneNumber())
                .build();
    }

    public PatientDto toPatientDto(Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .uuid(patient.getUuid())
                .fullName(patient.getFullName())
                .birthday(patient.getBirthday())
                .email(patient.getEmail() != null ? patient.getEmail() : null)
                .phoneNumber(patient.getPhoneNumber())
                .build();
    }

    public ShortPatientDto toShortPatientDto(Patient patient) {
        return ShortPatientDto.builder()
                .id(patient.getId())
                .fullName(patient.getFullName())
                .build();
    }
}
