package models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "Clubs")
public class Clubs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false,length = 50)
    private String name;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "count", nullable = false)
    private int count;

    @Lob
    @Column(name = "schedule", nullable = false)
    private String schedule;

    @Column(name = "town", nullable = false,length = 50)
    private String town;

    @Column(name = "address", nullable = false,length = 255)
    private String address;

    @ManyToOne
    @JoinColumn(name = "artist_id",nullable = false)
    private Artists artist;
    @OneToMany
    @JoinColumn(name = "club_id",nullable = false)
    private List<Reservations> reservationsList;

}
