package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventBookingRepository {
    private final List<EventBooking> bookings = new ArrayList<>();

    public void addBooking(EventBooking booking) {
        bookings.add(booking);
    }

    public List<EventBooking> findAll() {
        return bookings;
    }

    public List<EventBooking> searchByEventName(String eventName) {
        List<EventBooking> result = new ArrayList<>();
        String searchEventName = eventName == null ? "" : eventName.trim().toLowerCase();

        for (EventBooking booking : bookings) {
            if (booking.getEventName().toLowerCase().contains(searchEventName)) {
                result.add(booking);
            }
        }

        return result;
    }

}
