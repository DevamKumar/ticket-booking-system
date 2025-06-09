package com.ticketbookingsystem.ticket_booking_system.repository;

import com.ticketbookingsystem.ticket_booking_system.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
