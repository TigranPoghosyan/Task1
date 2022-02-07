package com.example.task1.controller;

import com.example.task1.entity.Ticket;
import com.example.task1.repository.TicketRepository;
import com.example.task1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/tickets")
    public ResponseEntity<List<Ticket>> getAllTicketsByUserId(@PathVariable(value = "userId") Long userId) throws Exception {
        if (!ticketRepository.existsById(userId)) {
            throw new Exception("Not found User with id = " + userId);
        }
        List<Ticket> tickets = ticketRepository.findByUserId(userId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/tickets/countOfTickets")
    public int getCountOfTickets(@PathVariable(value = "userId") Long userId) throws Exception {
        if (!ticketRepository.existsById(userId)) {
            throw new Exception("Not found User with id = " + userId);
        }
        List<Ticket> tickets = ticketRepository.findByUserId(userId);
        return tickets.size();
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getTicketsByUserId(@PathVariable(value = "id") Long id) throws Exception {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found Ticket with id = " + id));
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping("/tickets/ticketStatus")
    public ResponseEntity<List<Ticket>> findByPublished() {
        List<Ticket> tickets = ticketRepository.findByTicketStatus(true);
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/tickets")
    public ResponseEntity<Ticket> createTicketInUser(@PathVariable(value = "userId") Long userId,
                                                 @RequestBody Ticket ticketRequest) throws Exception {
        Ticket ticket = userRepository.findById(userId).map(user -> {
            ticketRequest.setUser(user);
            return ticketRepository.save(ticketRequest);
        }).orElseThrow(() -> new Exception("Not found User with id = " + userId));
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }
    @PutMapping("/tickets/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") long id, @RequestBody Ticket ticketRequest) throws Exception {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new Exception("TicketId " + id + "not found"));
        ticket.setTicketType(ticketRequest.getTicketType());
        ticket.setTicketStatus(ticketRequest.isTicketStatus());
        return new ResponseEntity<Ticket>(ticketRepository.save(ticket), HttpStatus.OK);
    }
    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable("id") long id) {
        ticketRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/users/{userId}/tickets")
    public ResponseEntity<List<Ticket>> deleteAllTicketsOfUser(@PathVariable(value = "userId") Long userId) throws Exception {
        if (!userRepository.existsById(userId)) {
            throw new Exception("Not found User with id = " + userId);
        }
        ticketRepository.deleteByUserId(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}