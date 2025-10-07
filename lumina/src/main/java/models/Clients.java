package models;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "unique identifier",updatable = false,nullable = false)
    private UUID id;

    @Column(nullable=false, length=50)
    private String firstName;

    @Column(nullable=false,length=50)
    private String lastName;

    @Column(nullable=false,length=10)
    private char phoneNumber;

    @Column(nullable = false,length = 255)
    private String email;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false,length = 50)
    private String password;

    @Column(nullable = false,length = 50)
    private String username;

    @Column(nullable = false,length = 50)
    private String role;

    @Column(nullable = false,length = 255)
    private String photo;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public char getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(char phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}

    public String getPhoto() {return photo;}
    public void setPhoto(String photo) {this.photo = photo;}





}
