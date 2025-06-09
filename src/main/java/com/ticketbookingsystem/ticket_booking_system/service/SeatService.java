package com.ticketbookingsystem.ticket_booking_system.service;

import com.ticketbookingsystem.ticket_booking_system.model.Seat;
import com.ticketbookingsystem.ticket_booking_system.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Transactional
    public boolean bookSeats(List<Integer> seatIds) {
        for (Integer id : seatIds) {
            Optional<Seat> seatOpt = seatRepository.findById(id);
            if (seatOpt.isEmpty() || seatOpt.get().getStatus() == Seat.SeatStatus.BOOKED) {
                return false; // Seat doesn't exist or already booked
            }
        }

        // Mark seats as booked
        for (Integer id : seatIds) {
            Seat seat = seatRepository.getReferenceById(id);
            seat.setStatus(Seat.SeatStatus.BOOKED);
            seatRepository.save(seat);
        }

        return true;
    }
}
