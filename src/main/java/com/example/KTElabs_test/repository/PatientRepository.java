package com.example.KTElabs_test.repository;

import com.example.KTElabs_test.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findPatientByPhoneNumber(String phone);

    Patient findPatientByEmail(String email);
}
