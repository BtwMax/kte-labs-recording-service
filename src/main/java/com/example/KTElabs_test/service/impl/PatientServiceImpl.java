package com.example.KTElabs_test.service.impl;

import com.example.KTElabs_test.dto.patient.NewPatientDto;
import com.example.KTElabs_test.dto.patient.PatientDto;
import com.example.KTElabs_test.exception.BadRequestException;
import com.example.KTElabs_test.exception.NotFoundException;
import com.example.KTElabs_test.exception.ValidationException;
import com.example.KTElabs_test.mapper.PatientMapper;
import com.example.KTElabs_test.model.Patient;
import com.example.KTElabs_test.repository.PatientRepository;
import com.example.KTElabs_test.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientDto createPatient(NewPatientDto newPatientDto) throws ValidationException, BadRequestException {
        Pattern pattern = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
        Matcher mat = pattern.matcher(newPatientDto.getPhoneNumber());
        if(!mat.matches()) {
            throw new ValidationException("Ошибка валидации. Неверный формат номера телефона");
        }
        String email = newPatientDto.getEmail();
        if (email != null) {
            if(patientRepository.findPatientByEmail(email) != null) {
                throw new BadRequestException("Пользователь с такой почтой уже заригестрирован");
            }
        }
        if (patientRepository.findPatientByPhoneNumber(newPatientDto.getPhoneNumber()) != null) {
            throw new BadRequestException("Пользователь с таким номерм телефона уже заригестрирован");
        }
        UUID uuid = UUID.randomUUID();
        Patient patient = PatientMapper.toPatient(newPatientDto, uuid);
        Patient patientStorage = patientRepository.save(patient);
        return PatientMapper.toPatientDto(patientStorage);
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDto getPatientById(long id) throws NotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пациент с id = " + id + " не найден"));
        return PatientMapper.toPatientDto(patient);
    }
}
