package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e " +
            "WHERE (:text IS NULL OR e.name LIKE %:text% OR e.description LIKE %:text%) " +
            "AND (:minPopularityScore IS NULL OR e.popularityScore >= :minPopularityScore) " +
            "AND (:categoryName IS NULL OR e.category.name = :categoryName)")
    List<Event> searchEvents(@Param("text") String text,
                             @Param("minPopularityScore") Double minPopularityScore,
                             @Param("categoryName") String categoryName);

    List<Event> findAllByLocation_Id(Long locationId);
}
