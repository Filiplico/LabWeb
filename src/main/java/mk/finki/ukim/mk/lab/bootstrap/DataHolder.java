package mk.finki.ukim.mk.lab.bootstrap;

import mk.finki.ukim.mk.lab.model.*;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Location> locations = null;
    public static List<Event> events = null;

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public DataHolder(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    public void init() {
        // Locations initialization
        locations = new ArrayList<>();
        if (this.locationRepository.count() == 0) {
            locations.add(new Location("Conference Hall A", "123 Main St", "200", "Main conference hall for large events"));
            locations.add(new Location("Outdoor Park", "45 Park Ave", "500", "Outdoor area for large gatherings"));
            locations.add(new Location("Exhibition Room", "98 Expo Blvd", "150", "Room for exhibitions and displays"));
            locations.add(new Location("Workshop Center", "210 Learning Way", "100", "Center for hands-on workshops"));
            locations.add(new Location("Auditorium", "12 Theater Dr", "300", "Auditorium for presentations and performances"));
            this.locationRepository.saveAll(locations);  // Save locations before events are created
        }

        // Ensure locations are saved and retrieved from the database
        List<Location> savedLocations = locationRepository.findAll();
        if (savedLocations.isEmpty()) {
            throw new IllegalStateException("Locations must be initialized before creating events");
        }

        // Events initialization
        events = new ArrayList<>();
        if (this.eventRepository.count() == 0) {
            events.add(new Event("Tech Conference", "Description-1", 4.5, savedLocations.get(0)));
            events.add(new Event("AI Summit", "Description-2", 5.3, savedLocations.get(0)));
            events.add(new Event("Science Fair", "Description-3", 1.9, savedLocations.get(2)));
            events.add(new Event("Art Showcase", "Description-4", 2.2, savedLocations.get(2)));
            events.add(new Event("Workshop", "Description-5", 7.1, savedLocations.get(3)));
            events.add(new Event("Film Festival", "Description-6", 9.0, savedLocations.get(4)));
            events.add(new Event("Small Film Screening", "Description-7", 7.7, savedLocations.get(1)));
            events.add(new Event("Music Festival", "Description-8", 3.6, savedLocations.get(4)));
            events.add(new Event("Opera", "Description-9", 1.2, savedLocations.get(0)));
            events.add(new Event("Car Exhibit", "Description-10", 5.6, savedLocations.get(1)));

            this.eventRepository.saveAll(events);  // Save events after locations are available
        }
    }
}
