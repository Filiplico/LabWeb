package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {

    public List<Event> findAll() {
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text) {
        List<Event> result = new ArrayList<>();
        String searchText = text == null ? "" : text.trim().toLowerCase();
        for (Event event : DataHolder.events) {
            if (event.getName().toLowerCase().contains(searchText)) {
                result.add(event);
            }
        }
        return result;
    }

    public Optional<Event> findById(Long id) {
        return DataHolder.events.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    public Optional<Event> save(String name, String description, Double popularityScore, Location location) {
        Event product = new Event(name, description, popularityScore, location);
        DataHolder.events.removeIf(p -> p.getName().equals(product.getName()));
        DataHolder.events.add(product);
        return Optional.of(product);
    }

    public void deleteById(Long id) {
        DataHolder.events.removeIf(i -> i.getId().equals(id));
    }
}
