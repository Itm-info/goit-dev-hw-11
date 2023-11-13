package ticket;

import client.Client;
import jakarta.persistence.*;
import lombok.Data;
import planet.Planet;

@Table(name = "ticket")
@Entity
@Data
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "planet_from_id", nullable = false)
    private Planet planet_from;

    @ManyToOne
    @JoinColumn(name = "planet_to_id", nullable = false)
    private Planet planet_to;

    @Override
    public String toString() {
        return "Ticket " + getId() +
                       " from "  + getPlanet_from().getName() +
                       " to "  + getPlanet_to().getName() +
                       " for " + getClient().getName();
    }

    @Override
    public boolean equals(Object o) {
        return this==o || (
                this.getClass() == o.getClass() &&
                        this.getId() == ((Ticket) o).getId() &&
                        this.getClient().getId() == ((Ticket) o).getClient().getId() &&
                        this.getPlanet_to().getId().equals(((Ticket) o).getPlanet_to().getId()) &&
                        this.getPlanet_from().getId().equals(((Ticket) o).getPlanet_from().getId())
                );
    }

    @Override
    public int hashCode() {
        return (getId() + "," + getClient().getId() + "," + getPlanet_to().getId() + "," + getPlanet_from().getId()).hashCode();
    }
}
