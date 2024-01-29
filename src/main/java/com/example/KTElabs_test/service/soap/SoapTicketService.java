package com.example.KTElabs_test.service.soap;




import java.time.LocalDateTime;


public interface SoapTicketService {

    void createTickets(long doctorId, LocalDateTime docStartTime, int duration, int count);
}
