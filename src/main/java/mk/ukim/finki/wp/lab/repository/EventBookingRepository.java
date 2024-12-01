package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventBookingRepository {

    private final List<EventBooking> bookings = new ArrayList<>();

    public void save(EventBooking booking) {

        bookings.add(booking);
    }

    public List<EventBooking> findAll() {

        return new ArrayList<>(bookings);
    }
}
