package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();

    List<Event> searchEvents(String text, Double minPopularityScore, String categoryName);  // Remove body

    Optional<Event> findById(Long id);

    Optional<Event> save(String name, String description, Double popularityScore, Long categoryId, Long locationId);

    void deleteById(Long id);

    List<Event> findEventsByLocation(Long locationId);
}
