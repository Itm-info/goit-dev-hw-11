import client.HibernateClientCrudService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import client.Client;
import storage.DatabaseInitService;

import java.sql.SQLException;

public class ClientCrudServiceTest {
    HibernateClientCrudService clientCrudService;

    @BeforeEach
    public void beforeEach() {
        new DatabaseInitService().initDb();
        clientCrudService = new HibernateClientCrudService();
    }

    @Test
    void create() throws SQLException {
        Client client = new Client();
        client.setName("Pluto");
        clientCrudService.create(client);
        Client clientSaved = clientCrudService.getById(client.getId());
        Assertions.assertEquals("Pluto", clientSaved.getName());
    }

    @Test
    void update() throws SQLException {
        Client client = new Client();
        client.setName("Pluto");
        clientCrudService.create(client);
        clientCrudService.update(client.getId());
        Client clientSaved = clientCrudService.getById(client.getId());
        Assertions.assertEquals("Agent Smith", clientSaved.getName());
    }

    @Test
    void delete() throws SQLException {
        Client client = new Client();
        client.setName("Pluto");
        clientCrudService.create(client);
        clientCrudService.delete(client.getId());
        Client clientSaved = clientCrudService.getById(client.getId());
        Assertions.assertNull(clientSaved);
    }
}
