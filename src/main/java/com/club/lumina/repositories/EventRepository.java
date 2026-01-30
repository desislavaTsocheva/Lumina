package com.club.lumina.repositories;

import com.club.lumina.models.Club;
import com.club.lumina.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findAllByEventDateBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT e.club FROM Event e WHERE e.artist.name LIKE %:artistName% AND e.eventDate > CURRENT_TIMESTAMP")
    List<Event> findAllByArtistNameIgnoreCase(@Param("artistName") String artistName);
    List<Event> findAllByGenreIgnoreCase(String style);

    @Query("SELECT e FROM Event e WHERE " +
            "LOWER(e.club.name) LIKE LOWER(concat('%', :query, '%')) OR " +
            "LOWER(e.artist.name) LIKE LOWER(concat('%', :query, '%')) OR " +
            "LOWER(e.genre) LIKE LOWER(concat('%', :query, '%'))")
    List<Event> searchEvents(@Param("query") String query);

    Optional<Event> findFirstByClubOrderByEventDateAsc(Club club);
}