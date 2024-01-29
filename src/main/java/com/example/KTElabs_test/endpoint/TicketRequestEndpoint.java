package com.example.KTElabs_test.endpoint;

import com.example.KTElabs_test.exception.BadRequestException;
import com.example.KTElabs_test.service.soap.SoapTicketService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ws.ktelabs_test.tickets.CreateTicketsResponse;
import ws.ktelabs_test.tickets.TicketsRequest;

@Endpoint
public class TicketRequestEndpoint {

    private static final String NAMESPACE = "http://ktelabs_test.ws/tickets";


    private final SoapTicketService ticketService;

    @Autowired
    public TicketRequestEndpoint(SoapTicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "ticketsRequest")
    @ResponsePayload
    public CreateTicketsResponse createTicketsResponse(@RequestPayload @NotNull TicketsRequest ticketsRequest) throws BadRequestException {
        CreateTicketsResponse createTicketsResponse = new CreateTicketsResponse();
        if (ticketsRequest.getStartTime() == null) {
            throw new BadRequestException("400: Время не может быть не задано");
        }
        try {
            ticketService.createTickets(ticketsRequest.getDoctorId(),
                    ticketsRequest.getStartTime().toGregorianCalendar().toZonedDateTime().toLocalDateTime(),
                    ticketsRequest.getDuration(), ticketsRequest.getCount());
        } catch (BadRequestException ex) {
            createTicketsResponse.setStatus("400: " + ex.getMessage());
            return createTicketsResponse;
        }

        createTicketsResponse.setStatus("Новые поля успешно добавлены на дату");

        return createTicketsResponse;
    }
}
