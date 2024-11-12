package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;

    @Autowired
    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping()
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Event> events = this.eventService.listAll();
        model.addAttribute("events", events);
        return "listEvents";
    }

    @GetMapping("/add-form")
    public String getAddEventForm(Model model){
        List<Location> locations = this.locationService.findAll();
        model.addAttribute("locations", locations);
        return "add-event";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Double popularityScore,
                            @RequestParam Long locationId) {
        this.eventService.save(name, description, popularityScore, locationId);
        return "redirect:/events";
    }

    @GetMapping("/edit-form/{id}")
    public String editEventForm(@PathVariable Long id, Model model){
        if(this.eventService.findById(id).isPresent()){
            Event event = this.eventService.findById(id).get();
            List<Location> locations = this.locationService.findAll();
            model.addAttribute("locations", locations);
            model.addAttribute("event", event);
            return "add-event";
        }
        return "redirect:/events?error=invalid-event-or-location";
    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id){
        this.eventService.deleteById(id);
        return "redirect:/events";
    }

    @GetMapping("/details/{id}")
    public String getEventDetails(@PathVariable Long id, Model model) {
        Optional<Event> optionalEvent = this.eventService.findById(id);
        if (optionalEvent.isPresent()) {
            model.addAttribute("event", optionalEvent.get());
            return "event-details";
        }
        return "redirect:/events?error=Event not found";
    }
}
