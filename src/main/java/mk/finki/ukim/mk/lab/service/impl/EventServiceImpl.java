package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.EventRepository;

import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEvents(text);
    }

    @Override
    public Optional<Event> findById(Long id){
        return eventRepository.findById(id);
    }

    @Override
    public Optional<Event> save(Long id, String name, String description, Double popularityScore, Long locationId) {
        Location location = this.locationRepository.findById(locationId).orElse(null);
        return this.eventRepository.save(id, name, description, popularityScore, location);
    }

    @Override
    public void deleteById(Long id) {
        this.eventRepository.deleteById(id);
    }
}