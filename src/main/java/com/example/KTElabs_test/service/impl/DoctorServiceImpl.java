package com.example.KTElabs_test.service.impl;

import com.example.KTElabs_test.dto.doctor.DoctorDto;
import com.example.KTElabs_test.dto.doctor.NewDoctorDto;
import com.example.KTElabs_test.exception.NotFoundException;
import com.example.KTElabs_test.mapper.DoctorMapper;
import com.example.KTElabs_test.model.Doctor;
import com.example.KTElabs_test.repository.DoctorRepository;
import com.example.KTElabs_test.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorDto createDoctor(NewDoctorDto newDoctorDto) {
        UUID uuid = UUID.randomUUID();
        Doctor doctor = DoctorMapper.toDoctor(newDoctorDto, uuid);
        Doctor doctorStorage = doctorRepository.save(doctor);
        return DoctorMapper.toDoctorDto(doctorStorage);
    }

    @Override
    @Transactional(readOnly = true)
    public DoctorDto getDoctorById(long id) throws NotFoundException {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Доктор с id = " + id + " не найден"));
        return DoctorMapper.toDoctorDto(doctor);
    }
}
