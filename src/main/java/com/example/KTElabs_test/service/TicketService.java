package com.example.KTElabs_test.service;

import com.example.KTElabs_test.dto.ticket.TicketDto;

import java.util.List;

public interface TicketService {

    List<TicketDto> getDoctorAvailableTicketsForDate(long docId, String date);

    TicketDto changeAvailableForTicket(long ticketId, long patientId);

    List<TicketDto> getAllPatientTicketsById(long patientId);
}
