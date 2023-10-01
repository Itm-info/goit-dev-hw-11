package cli;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CliState {
    protected final CliFSM fsm;

    public void init() {  }
    public void createClientCrudService() { }
    public void readClientCrudService() { }
    public void readByNameClientCrudService() { }
    public void updateClientCrudService() { }
    public void deleteClientCrudService() { }
    public void createPlanetCrudService() { }
    public void readPlanetCrudService() { }
    public void updatePlanetCrudService() { }
    public void deletePlanetCrudService() { }
    public void unknownCommand(String cmd) {  }
}