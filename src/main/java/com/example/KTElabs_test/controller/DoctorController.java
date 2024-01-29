package com.example.KTElabs_test.controller;

import com.example.KTElabs_test.dto.doctor.DoctorDto;
import com.example.KTElabs_test.dto.doctor.NewDoctorDto;
import com.example.KTElabs_test.service.DoctorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/create")
    public DoctorDto createPatient(@Valid @RequestBody NewDoctorDto newDoctorDto) {
        log.info("Запрос на добавление нового доктора");
        return doctorService.createDoctor(newDoctorDto);
    }

    @GetMapping("/{docId}")
    public DoctorDto getPatientById(@PathVariable long docId) {
        log.info("Запрос на показ доктора с id = " + docId);
        return doctorService.getDoctorById(docId);
    }
}
