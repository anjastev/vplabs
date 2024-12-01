package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.CategoryRepository;
import mk.ukim.finki.wp.lab.repository.LocationRepository;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataHolder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final EventRepository eventRepository;


    public DataHolder(CategoryRepository categoryRepository, LocationRepository locationRepository, EventRepository eventRepository) {
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            Category category1 = new Category("Music");
            Category category2 = new Category("Technology");
            Category category3 = new Category("Arts");
            Category category4 = new Category("Sports");
            Category category5 = new Category("Food & Drink");

            categoryRepository.save(category1);
            categoryRepository.save(category2);
            categoryRepository.save(category3);
            categoryRepository.save(category4);
            categoryRepository.save(category5);
            System.out.println("Categories saved");
        }

        if (locationRepository.count() == 0) {
            Location location1 = new Location("Skopje City Park", "Skopje, Macedonia", "100", "Beautiful park with green spaces and open-air venues.");
            Location location2 = new Location("Macedonian Philharmonic", "Skopje, Macedonia", "300", "Classic concert hall for symphonic music and performances.");
            Location location3 = new Location("Museum of Contemporary Art", "Skopje, Macedonia", "150", "Exhibiting modern and contemporary art by international artists.");
            Location location4 = new Location("City Sports Arena", "Skopje, Macedonia", "5000", "Multipurpose indoor arena for sports and events.");
            Location location5 = new Location("Skopje Fair Grounds", "Skopje, Macedonia", "2000", "Spacious event grounds for exhibitions and festivals.");

            locationRepository.save(location1);
            locationRepository.save(location2);
            locationRepository.save(location3);
            locationRepository.save(location4);
            locationRepository.save(location5);
            System.out.println("Locations saved");
        }

        if (eventRepository.count() == 0) {
            Event event1 = new Event("Summer Music Festival", "Outdoor music festival featuring local bands and international artists.", 1.00, categoryRepository.findById(1L).orElse(null), locationRepository.findById(1L).orElse(null));
            Event event2 = new Event("AI Tech Conference", "Global leaders in AI technology discussing innovations and challenges.", 2.00, categoryRepository.findById(2L).orElse(null), locationRepository.findById(5L).orElse(null));
            Event event3 = new Event("Modern Art Exhibition", "Collection of contemporary art pieces from various artists around the world.", 3.00, categoryRepository.findById(3L).orElse(null), locationRepository.findById(3L).orElse(null));
            Event event4 = new Event("Skopje Marathon", "Annual marathon event through the city's streets, attracting runners worldwide.", 4.00, categoryRepository.findById(4L).orElse(null), locationRepository.findById(4L).orElse(null));
            Event event5 = new Event("Taste of Macedonia", "Food and drink festival showcasing the best local cuisine and beverages.", 5.00, categoryRepository.findById(5L).orElse(null), locationRepository.findById(5L).orElse(null));

            eventRepository.save(event1);
            eventRepository.save(event2);
            eventRepository.save(event3);
            eventRepository.save(event4);
            eventRepository.save(event5);
            System.out.println("Events saved");
        }
    }
}