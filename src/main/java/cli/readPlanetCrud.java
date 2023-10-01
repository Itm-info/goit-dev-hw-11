package cli;

import planet.Planet;
import planet.HibernatePlanetCrudService;
import planet.IPlanetCrudService;

import java.sql.SQLException;

public class readPlanetCrud extends CliState {
    public readPlanetCrud(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        try {
            IPlanetCrudService PlanetCrudService = new HibernatePlanetCrudService();

            System.out.println("Reading. Enter planet Id:");
            String id = fsm.getScanner().nextLine();

            Planet planet = PlanetCrudService.getById(id);

            if (planet != null) {
                System.out.println("Planet " + planet.getName() + " found.");
            } else {
                System.out.println("Planet with id " + id + " not found:");
            }

            fsm.setState(new IdleState(fsm));
//        } catch (SQLException | ExecutionException | InterruptedException e) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}