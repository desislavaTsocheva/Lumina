package models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "Clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",updatable = false,nullable = false)
    private UUID id;

    @Column(name = "first_name", nullable=false, length=50)
    private String firstName;

    @Column(name = "last_name", nullable=false,length=50)
    private String lastName;

    @Column(name = "phone_number", nullable=false,length=10)
    private char phoneNumber;

    @Column(name = "email", nullable = false,length = 255)
    private String email;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "password", nullable = false,length = 50)
    private String password;

    @Column(name = "username", nullable = false,length = 50)
    private String username;

    @Column(name = "role", nullable = false,length = 50)
    private String role;

    @Column(name = "photo", nullable = false,length = 255)
    private String photo;

    @OneToMany
    @JoinColumn(name = "clients_id",nullable = false)
    private List<Reservations> reservationList;

}
