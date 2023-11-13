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
    public void createClientCrudService() { fsm.setState(new CreateClientCrud(fsm)); }
    @Override
    public void readClientCrudService() {
        fsm.setState(new ReadClientCrud(fsm));
    }
    @Override
    public void readByNameClientCrudService() {
        fsm.setState(new ReadByNameClientCrud(fsm));
    }
    @Override
    public void updateClientCrudService() { fsm.setState(new UpdateClientCrud(fsm)); }
    @Override
    public void deleteClientCrudService() { fsm.setState(new DeleteClientCrud(fsm));  }
    @Override
    public void createPlanetCrudService() { fsm.setState(new CreatePlanetCrud(fsm)); }
    @Override
    public void readPlanetCrudService() { fsm.setState(new ReadPlanetCrud(fsm)); }
    @Override
    public void updatePlanetCrudService() { fsm.setState(new UpdatePlanetCrud(fsm)); }
    @Override
    public void deletePlanetCrudService() { fsm.setState(new DeletePlanetCrud(fsm)); }
    @Override
    public void createTicketCrudService() { fsm.setState(new CreateTicketCrud(fsm)); }
    @Override
    public void readTicketCrudService() { fsm.setState(new ReadTicketCrud(fsm)); }
    @Override
    public void updateTicketCrudService() { fsm.setState(new UpdateTicketCrud(fsm)); }
    @Override
    public void deleteTicketCrudService() { fsm.setState(new DeleteTicketCrud(fsm)); }
}