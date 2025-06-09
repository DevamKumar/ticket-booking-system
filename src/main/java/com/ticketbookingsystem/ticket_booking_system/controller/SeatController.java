package com.ticketbookingsystem.ticket_booking_system.controller;

import com.ticketbookingsystem.ticket_booking_system.model.Seat;
import com.ticketbookingsystem.ticket_booking_system.service.SeatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/")
    public String redirectToSeats() {
        return "redirect:/seats";
    }

    @GetMapping({"/seats"})
    public String getSeats(Model model) {
        List<Seat> seats = seatService.getAllSeats();
        model.addAttribute("seats", seats);
        return "index";
    }

    @PostMapping("/book")
    public String bookSeats(@RequestParam(name = "seatIds", required = false) List<Integer> seatIds,
                            Model model) {
        boolean success = false;
        if (seatIds != null && !seatIds.isEmpty()) {
            success = seatService.bookSeats(seatIds);
        }
        model.addAttribute("success", success);
        model.addAttribute("seats", seatService.getAllSeats());
        return "index";
    }
}
