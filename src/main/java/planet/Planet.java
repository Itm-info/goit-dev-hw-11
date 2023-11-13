package planet;

import jakarta.persistence.*;
import lombok.Data;
import ticket.Ticket;

import java.util.Set;

@Table(name = "planet")
@Entity
@Data
public class Planet {
    @Id
    private String id;

    @Column
    private String name;

    @OneToMany(mappedBy="planet_to")
    private Set<Ticket> planets_to;

    @OneToMany(mappedBy="planet_from")
    private Set<Ticket> planets_from;
}
