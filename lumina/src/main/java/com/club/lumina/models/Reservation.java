package com.club.lumina.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false,name = "id")
    private UUID id;

    @Column(nullable = false, name = "date")
    private LocalDateTime date;

    @Column(nullable=false, name = "count")
    private int count;

    @Column(nullable = false, length = 100, name = "type")
    private String type;

    @Column(nullable = false, name = "discount")
    private int discount;

    @Column(nullable = false, length = 20, name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "clients_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private ClubTable clubTable;
}
