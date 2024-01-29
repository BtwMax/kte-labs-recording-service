package com.example.KTElabs_test.service;

import com.example.KTElabs_test.dto.patient.NewPatientDto;
import com.example.KTElabs_test.dto.patient.PatientDto;

public interface PatientService {

    PatientDto createPatient(NewPatientDto newPatientDto);

    PatientDto getPatientById(long id);
}
