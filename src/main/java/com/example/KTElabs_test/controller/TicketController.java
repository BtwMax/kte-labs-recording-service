package com.example.KTElabs_test.controller;

import com.example.KTElabs_test.dto.ticket.TicketDto;
import com.example.KTElabs_test.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping("/doctor/{docId}")
    public List<TicketDto> getDoctorAvailableTicketsForDate(@PathVariable long docId,
                                                            @RequestParam String date) {
        log.info("Запрос на получение всех доступных слотов для записи к врачу с id = " + docId + " на дату: " + date);
        return ticketService.getDoctorAvailableTicketsForDate(docId, date);
    }

    @PatchMapping("/{ticketId}/patient/{patientId}")
    public TicketDto changeAvailableForTicket(@PathVariable long ticketId,
                                              @PathVariable long patientId) {
        log.info("Запрос на запись пациентом с id = " + patientId + " в слот с id =  " + ticketId);
        return ticketService.changeAvailableForTicket(ticketId, patientId);
    }

    @GetMapping("/patient/{patientId}")
    public List<TicketDto> getAllPatientTicketsById(@PathVariable long patientId) {
        log.info("Запрос на показ всех записей пользователя id = " + patientId);
        return ticketService.getAllPatientTicketsById(patientId);
    }
}
