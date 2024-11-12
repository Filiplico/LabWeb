package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Location> locations = null;
    public static List<Event> events = null;

    @PostConstruct
    public void init() {
        locations = new ArrayList<>();
        locations.add(new Location("Location_1", "Address_1", "100", "Description_1"));
        locations.add(new Location("Location_2", "Address_2", "200", "Description_2"));
        locations.add(new Location("Location_3", "Address_3", "300", "Description_3"));
        locations.add(new Location("Location_4", "Address_4", "400", "Description_4"));
        locations.add(new Location("Location_5", "Address_5", "500", "Description_5"));

        events = new ArrayList<>();
        events.add(new Event("Event_1", "Description_1", 3.1, locations.get(0)));
        events.add(new Event("Event_2", "Description_2", 7.2, locations.get(1)));
        events.add(new Event("Event_3", "Description_3", 2.3, locations.get(2)));
        events.add(new Event("Event_4", "Description_4", 1.4, locations.get(3)));
        events.add(new Event("Event_5", "Description_5", 9.5, locations.get(4)));
        events.add(new Event("Event_6", "Description_6", 1.6, locations.get(0)));
        events.add(new Event("Event_7", "Description_7", 4.7, locations.get(1)));
        events.add(new Event("Event_8", "Description_8", 3.8, locations.get(2)));
        events.add(new Event("Event_9", "Description_9", 6.9, locations.get(3)));
        events.add(new Event("Event_10", "Description_10", 7.10, locations.get(4)));

    }
}
