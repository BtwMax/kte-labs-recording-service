package com.example.KTElabs_test.service;

import com.example.KTElabs_test.dto.doctor.DoctorDto;
import com.example.KTElabs_test.dto.doctor.NewDoctorDto;

public interface DoctorService {

    DoctorDto createDoctor(NewDoctorDto newDoctorDto);

    DoctorDto getDoctorById(long id);
}
