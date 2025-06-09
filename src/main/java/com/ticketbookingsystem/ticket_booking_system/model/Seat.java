package com.ticketbookingsystem.ticket_booking_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    public enum SeatStatus {
        AVAILABLE,
        BOOKED
    }
}
