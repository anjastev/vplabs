package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {



    @PostMapping
    public String getEventBookingPage(@RequestParam String attendeeName,
                                      @RequestParam String eventName,
                                      @RequestParam String numTickets, Model model, HttpServletRequest req) {

        model.addAttribute("attendeeName", attendeeName);
        model.addAttribute("eventName", eventName);
        model.addAttribute("numTickets", numTickets);
        String clientAddress = req.getRemoteAddr();
        model.addAttribute("clientAddress", clientAddress);
        return "bookingConfirmation";
    }

}
