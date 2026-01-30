package com.club.lumina.repositories;

import com.club.lumina.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ClubRepository extends JpaRepository<Club, UUID> {
    @Query("SELECT DISTINCT c FROM Club c LEFT JOIN c.events e " +
            "WHERE (:town IS NULL OR LOWER(c.town) LIKE LOWER(CONCAT('%', :town, '%'))) " +
            "AND (:genre IS NULL OR LOWER(e.genre) LIKE LOWER(CONCAT('%', :genre, '%')))")
    List<Club> findByFilters(@Param("town") String town, @Param("genre") String genre);
}
