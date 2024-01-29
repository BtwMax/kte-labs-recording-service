package com.example.KTElabs_test.repository;

import com.example.KTElabs_test.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findTicketsByDate(LocalDate date);

    List<Ticket> findAllByDoctorIdAndDateAndAvailableIsTrue(long docId, LocalDate date);

    List<Ticket> findAllByPatientId(long patientId);
}
