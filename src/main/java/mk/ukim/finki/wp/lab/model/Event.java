package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double popularityScore;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    public Event(String name, String description, Double popularityScore, Category category, Location location) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.category = category;
        this.location = location;
    }
}
