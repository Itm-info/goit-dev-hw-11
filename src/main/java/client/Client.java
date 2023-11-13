package client;

import jakarta.persistence.*;
import lombok.Data;
import ticket.Ticket;

import java.util.Set;

@Table(name = "client")
@Entity
@Data
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "client")
    private Set<Ticket> tickets;
}
