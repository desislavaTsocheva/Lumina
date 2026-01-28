package com.club.lumina.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Clubs")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "count", nullable = false)
    private int count;

    @Lob
    @Column(name = "schedule", nullable = false)
    private String schedule;

    @Column(name = "city", nullable = false, length = 50)
    private String town;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Event> events;

    @OneToMany(mappedBy = "club")
    private List<Reservation> reservationsList;

}
