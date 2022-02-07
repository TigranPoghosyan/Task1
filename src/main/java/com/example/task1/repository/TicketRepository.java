package com.example.task1.repository;

import com.example.task1.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> findByUserId(Long findUserById);

    List<Ticket> findByTicketStatus(boolean ticketStatus);

    List<Ticket> findByTitle(String title);

    @Transactional
    void deleteByUserId(long deleteUserById);
}
