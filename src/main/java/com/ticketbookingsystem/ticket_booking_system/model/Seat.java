package com.ticketbookingsystem.ticket_booking_system.model;

import jakarta.persistence.*;
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

    @Version
    private Long version;

    public enum SeatStatus {
        AVAILABLE,
        BOOKED
    }
}
