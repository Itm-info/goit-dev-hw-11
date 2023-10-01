package cli;

import planet.Planet;
import planet.HibernatePlanetCrudService;
import planet.IPlanetCrudService;

import java.sql.SQLException;
import java.util.UUID;

public class createPlanetCrud extends CliState {
    public createPlanetCrud(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        try {
            IPlanetCrudService PlanetCrudService = new HibernatePlanetCrudService();

            Planet planet = new Planet();

            planet.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
            System.out.println("Creation. Enter planet id is:" + planet.getId());

//            System.out.println("Creation. Enter planet id:");
//            String id = fsm.getScanner().nextLine();
//            planet.setId(id);

            System.out.println("Creation. Enter planet name:");
            String name = fsm.getScanner().nextLine();
            planet.setName(name);

            PlanetCrudService.create(planet);

            System.out.println("Planet " + planet.getName() +  " saved.");

            fsm.setState(new IdleState(fsm));
//        } catch (SQLException | ExecutionException | InterruptedException e) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}