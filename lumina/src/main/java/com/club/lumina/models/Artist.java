package com.club.lumina.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "Artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable=false, nullable=false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(nullable = false, name = "price_per_client")
    private float pricePerClient;

    @Column(name = "genre", nullable = false, length = 50)
    private String genre;

    @Column(name = "photo", nullable = false, length = 255)
    private String photo;

    @OneToMany
    @JoinColumn(name = "artist_id", nullable = false)
    private List<Club> clubsList;

}
