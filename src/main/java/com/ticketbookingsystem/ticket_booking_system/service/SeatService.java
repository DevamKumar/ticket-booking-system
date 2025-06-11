package com.ticketbookingsystem.ticket_booking_system.service;

import com.ticketbookingsystem.ticket_booking_system.model.Seat;
import com.ticketbookingsystem.ticket_booking_system.repository.SeatRepository;
import org.springframework.data.domain.Sort;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
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
        return seatRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Transactional
    public boolean bookSeats(List<Integer> seatIds) {
        if (seatIds == null || seatIds.isEmpty()) {
            return false;
        }

        try {
            for (Integer id : seatIds) {

                Optional<Seat> seatOpt = seatRepository.findById(id);
                if (seatOpt.isEmpty()) {
                    System.err.println("Seat with ID " + id + " not found.");
                    throw new RuntimeException("Seat not found: " + id);
                }

                Seat seat = seatOpt.get();

                if (seat.getStatus() == Seat.SeatStatus.BOOKED) {
                    System.err.println("Seat with ID " + id + " is already booked.");
                    return false;
                }

                seat.setStatus(Seat.SeatStatus.BOOKED);
                seatRepository.save(seat);
            }
            return true; // All seats successfully booked
        } catch (ObjectOptimisticLockingFailureException e) {
            System.err.println("Booking failed due to concurrent modification (optimistic lock failure). " + e.getMessage());
            return false; // Booking failed due to concurrency
        } catch (RuntimeException e) {
            System.err.println("Booking failed due to an unexpected error: " + e.getMessage());
            return false;
        }
    }
}