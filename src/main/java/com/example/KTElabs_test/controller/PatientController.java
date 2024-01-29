package com.example.KTElabs_test.controller;

import com.example.KTElabs_test.dto.patient.NewPatientDto;
import com.example.KTElabs_test.dto.patient.PatientDto;
import com.example.KTElabs_test.service.PatientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/create")
    public PatientDto createPatient(@Valid @RequestBody NewPatientDto newPatientDto) {
        log.info("Запрос на добавление нового пациента");
        return patientService.createPatient(newPatientDto);
    }

    @GetMapping("/{patientId}")
    public PatientDto getPatientById(@PathVariable long patientId) {
        log.info("Запрос на показ пользователя с id = " + patientId);
        return patientService.getPatientById(patientId);
    }
}
