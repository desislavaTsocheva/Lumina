package models;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Reservations")
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false,columnDefinition = "unique identifier")
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable=false)
    private int count;

    @Column(nullable = false,length = 100)
    private String type;

    @Column(nullable = false)
    private int discount;

    @Column(nullable = false, name="table_number")
    private int tableNumber;

    @Column(nullable = false,length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "clients_id")
    private Clients clients;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Clubs clubs;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}

    public LocalDateTime getDate() {return date;}
    public void setDate(LocalDateTime date) {this.date = date;}

    public int getCount() {return count;}
    public void setCount(int count) {this.count = count;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public int getDiscount() {return discount;}
    public void setDiscount(int discount) {this.discount = discount;}

    public int getTableNumber() {return tableNumber;}
    public void setTableNumber(int tableNumber) {this.tableNumber = tableNumber;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public Clients getClients() {return clients;}
    public void setClients(Clients clients) {this.clients = clients;}

    public Clubs getClubs() {return clubs;}
    public void setClubs(Clubs clubs) {this.clubs = clubs;}


}
