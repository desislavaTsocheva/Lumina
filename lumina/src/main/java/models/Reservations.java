package models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "Reservations")
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false,name = "id")
    private UUID id;

    @Column(nullable = false,name = "date")
    private LocalDateTime date;

    @Column(nullable=false,name = "count")
    private int count;

    @Column(nullable = false,length = 100,name = "type")
    private String type;

    @Column(nullable = false,name = "discount")
    private int discount;

    @Column(nullable = false, name="table_number")
    private int tableNumber;

    @Column(nullable = false,length = 20,name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "clients_id")
    private Clients clients;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Clubs clubs;
}
