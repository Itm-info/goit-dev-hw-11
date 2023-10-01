package cli;

import lombok.Getter;
import storage.ConnectionProvider;

import java.util.Scanner;

public class CliFSM {
    private CliState state;

    @Getter
    private Scanner scanner;

    @Getter
    private ConnectionProvider connectionProvider;

    public CliFSM(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;

        state = new IdleState(this);

        scanner = new Scanner(System.in);

        startInputLoop();
    }

    private void startInputLoop() {
        while (true) {
            String command = scanner.nextLine();

            switch (command) {
                case "exit":
                    System.exit(0);
                    break;
                case "cc":
                    createClientCrudService();
                    break;
                case "rc":
                    readClientCrudService();
                    break;
                case "rcn":
                    readByNameClientCrudService();
                    break;
                case "uc":
                    updateClientCrudService();
                    break;
                case "dc":
                    deleteClientCrudService();
                    break;
                case "cp":
                    createPlanetCrudService();
                    break;
                case "rp":
                    readPlanetCrudService();
                    break;
                case "up":
                    updatePlanetCrudService();
                    break;
                case "dp":
                    deletePlanetCrudService();
                    break;
                default:
                    unknownCommand(command);
            }
        }
    }

    public void createClientCrudService() {
        state.createClientCrudService();
    }
    public void readClientCrudService() { state.readClientCrudService(); }
    public void readByNameClientCrudService() { state.readByNameClientCrudService(); }
    public void updateClientCrudService() { state.updateClientCrudService(); }
    public void deleteClientCrudService() { state.deleteClientCrudService(); }
    public void createPlanetCrudService() { state.createPlanetCrudService(); }
    public void readPlanetCrudService() { state.readPlanetCrudService(); }
    public void updatePlanetCrudService() { state.updatePlanetCrudService(); }
    public void deletePlanetCrudService() { state.deletePlanetCrudService(); }
    public void unknownCommand(String cmd) {
        state.unknownCommand(cmd);
    }
    public void setState(CliState state) {
        this.state = state;

        state.init();
    }
}