import ticket.Ticket;
import ticket.HibernateTicketCrudService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.DatabaseInitService;

import java.sql.SQLException;

public class TicketCrudServiceTest {
    HibernateTicketCrudService ticketCrudService;

    @BeforeEach
    public void beforeEach() {
        new DatabaseInitService().initDb();
        ticketCrudService = new HibernateTicketCrudService();
    }

    @Test
    void create() throws SQLException {
        Ticket ticket = ticketCrudService.createRandomTicket();
        Ticket ticketSaved = ticketCrudService.getById(ticket.getId());
        Assertions.assertEquals(ticket, ticketSaved);
    }

    @Test
    void update() throws SQLException {
        Ticket ticket = ticketCrudService.createRandomTicket();
        ticketCrudService.update(ticket.getId());
        Ticket ticketSaved = ticketCrudService.getById(ticket.getId());
        Assertions.assertEquals("Agent Smith", ticketSaved.getClient().getName());
    }

    @Test
    void delete() throws SQLException {
        Ticket ticket = ticketCrudService.createRandomTicket();
        ticketCrudService.delete(ticket.getId());
        Ticket ticketSaved = ticketCrudService.getById(ticket.getId());
        Assertions.assertNull(ticketSaved);
    }
}
