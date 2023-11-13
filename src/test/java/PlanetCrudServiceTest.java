import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import planet.HibernatePlanetCrudService;
import planet.Planet;
import storage.DatabaseInitService;

import java.sql.SQLException;
import java.util.UUID;

public class PlanetCrudServiceTest {
    HibernatePlanetCrudService planetCrudService;

    @BeforeEach
    public void beforeEach() {
        new DatabaseInitService().initDb();
        planetCrudService = new HibernatePlanetCrudService();
    }

    @Test
    void create() throws SQLException {
        Planet planet = new Planet();
        planet.setName("Pluto");
        planet.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        planetCrudService.create(planet);
        Planet planetSaved = planetCrudService.getById(planet.getId());
        Assertions.assertEquals("Pluto", planetSaved.getName());
    }

    @Test
    void update() throws SQLException {
        Planet planet = new Planet();
        planet.setName("Pluto");
        planet.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        planetCrudService.create(planet);
        planetCrudService.update(planet.getId());
        Planet planetSaved = planetCrudService.getById(planet.getId());
        Assertions.assertEquals("Gaia", planetSaved.getName());
    }

    @Test
    void delete() throws SQLException {
        Planet planet = new Planet();
        planet.setName("Pluto");
        planet.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        planetCrudService.create(planet);
        planetCrudService.delete(planet.getId());
        Planet planetSaved = planetCrudService.getById(planet.getId());
        Assertions.assertNull(planetSaved);
    }
}
