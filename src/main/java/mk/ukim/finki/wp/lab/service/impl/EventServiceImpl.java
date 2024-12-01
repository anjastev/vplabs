package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.repository.CategoryRepository;
import mk.ukim.finki.wp.lab.repository.LocationRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, CategoryRepository categoryRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text, Double minPopularityScore, String categoryName) {
        return eventRepository.searchEvents(text, minPopularityScore, categoryName);
    }


    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Optional<Event> save(String name, String description, Double popularityScore, Long categoryId, Long locationId) {


        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {

            throw new IllegalArgumentException("Invalid category ID");
        }

        Optional<Location> location = locationRepository.findById(locationId);
        if (location.isEmpty()) {

            throw new IllegalArgumentException("Invalid location ID");
        }


        Event event = new Event(name, description, popularityScore, category.get(), location.get());

        return Optional.of(eventRepository.save(event));
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> findEventsByLocation(Long locationId) {
        return eventRepository.findAllByLocation_Id(locationId);
    }
}
