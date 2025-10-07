package models;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Artists")
public class Artists {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition="unique identifier",updatable=false,nullable=false)
    private UUID id;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false, name = "price_per_client")
    private float pricePerClient;

    @Column(nullable = false,length = 50)
    private String genre;

    @Column(nullable = false,length = 255)
    private String photo;

    public UUID getId(){return id;}
    public void setId(UUID id){this.id = id;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public float getPrice_per_client(){return pricePerClient;}
    public void setPrice_per_client(float pricePerClient)
    {
        this.pricePerClient = pricePerClient;
    }
    public String getGenre(){return genre;}
    public void setGenre(String genre){this.genre = genre;}

    public String getPhoto(){return photo;}
    public void setPhoto(String photo){this.photo = photo;}
}
