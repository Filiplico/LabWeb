package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    Optional<Event> findById(Long id);
    Optional<Event> save(Long id, String name, String description, Double popularityScore, Long locationId);
    void deleteById(Long id);
}
