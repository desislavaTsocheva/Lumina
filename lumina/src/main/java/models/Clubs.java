package models;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Clubs")
public class Clubs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false,columnDefinition = "unique identifier")
    private UUID id;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private int count;

    @Lob
    @Column(nullable = false)
    private String schedule;

    @Column(nullable = false,length = 50)
    private String town;

    @Column(nullable = false,length = 255)
    private String address;

    @ManyToOne
    @JoinColumn(name = "artist_id",nullable = false)
    private Artists artist;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    public String getTown() { return town; }
    public void setTown(String town) { this.town = town; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Artists getArtist() { return artist; }
    public void setArtist(Artists artist) { this.artist = artist; }


}
