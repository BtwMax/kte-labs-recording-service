package com.example.KTElabs_test.mapper;

import com.example.KTElabs_test.dto.doctor.DoctorDto;
import com.example.KTElabs_test.dto.doctor.NewDoctorDto;
import com.example.KTElabs_test.dto.doctor.ShortDoctorDto;
import com.example.KTElabs_test.model.Doctor;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class DoctorMapper {

    public Doctor toDoctor(NewDoctorDto newDoctorDto, UUID uuid) {
        return Doctor.builder()
                .uuid(uuid)
                .fullName(newDoctorDto.getFullName())
                .speciality(newDoctorDto.getSpeciality())
                .build();
    }

    public DoctorDto toDoctorDto(Doctor doctor) {
        return DoctorDto.builder()
                .id(doctor.getId())
                .uuid(doctor.getUuid())
                .fullName(doctor.getFullName())
                .speciality(doctor.getSpeciality())
                .build();
    }

    public ShortDoctorDto toShortDoctorDto(Doctor doctor) {
        return ShortDoctorDto.builder()
                .id(doctor.getId())
                .fullName(doctor.getFullName())
                .speciality(doctor.getSpeciality())
                .build();
    }
}
