package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.service.CategoryService;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final CategoryService categoryService;
    private final LocationService locationService;

    public EventController(EventService eventService, CategoryService categoryService, LocationService locationService) {
        this.eventService = eventService;
        this.categoryService = categoryService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String searchEvents,
                                @RequestParam(required = false) Double minPopularityScore,
                                @RequestParam(required = false) String searchCategory,
                                Model model) {

        List<Event> events;

        if (searchEvents != null || minPopularityScore != null || searchCategory != null) {
            events = eventService.searchEvents(searchEvents, minPopularityScore, searchCategory);
        } else {
            events = eventService.listAll();
        }

        if (!events.isEmpty()) {
            model.addAttribute("events", events);
        } else {
            model.addAttribute("hasError", true);
            model.addAttribute("error", "No events found with the given criteria.");
        }

        return "listEvents";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        return "redirect:/events";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditEventForm(@PathVariable Long id, Model model) {
        Event event = eventService.findById(id).orElse(null);

        if (event != null) {
            List<Category> categories = categoryService.findAll();
            List<Location> locations = locationService.findAll();
            model.addAttribute("event", event);
            model.addAttribute("categories", categories);
            model.addAttribute("locations", locations);
            return "add-event"; // Return the form to edit the event
        }

        return "redirect:/events?error=EventNotFound";
    }


    @GetMapping("/add-form")
    public String getAddEventPage(Model model) {
        List<Category> categories = categoryService.findAll();
        List<Location> locations = locationService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("locations", locations);
        return "add-event";
    }

    @PostMapping("/add")
    public String addEvent(@RequestParam String name,
                           @RequestParam String description,
                           @RequestParam Double popularityScore,
                           @RequestParam Long category,
                           @RequestParam Long location) {
        eventService.save(name, description, popularityScore, category, location);
        return "redirect:/events";
    }


}
