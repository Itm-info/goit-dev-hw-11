package cli;

public class IdleState extends CliState {
    public IdleState(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void unknownCommand(String cmd) {
        System.out.println("Unknown command: " + cmd);
    }
    @Override
    public void createClientCrudService() {
        fsm.setState(new createClientCrud(fsm));
    }
    @Override
    public void readClientCrudService() {
        fsm.setState(new readClientCrud(fsm));
    }
    @Override
    public void readByNameClientCrudService() {
        fsm.setState(new readByNameClientCrud(fsm));
    }
    @Override
    public void updateClientCrudService() { fsm.setState(new updateClientCrud(fsm)); }
    @Override
    public void deleteClientCrudService() { fsm.setState(new deleteClientCrud(fsm));  }
    @Override
    public void createPlanetCrudService() { fsm.setState(new createPlanetCrud(fsm)); }
    @Override
    public void readPlanetCrudService() { fsm.setState(new readPlanetCrud(fsm)); }
    @Override
    public void updatePlanetCrudService() { fsm.setState(new updatePlanetCrud(fsm)); }
    @Override
    public void deletePlanetCrudService() { fsm.setState(new deletePlanetCrud(fsm)); }
}