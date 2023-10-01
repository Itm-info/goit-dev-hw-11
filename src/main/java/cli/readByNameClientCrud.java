package cli;

import client.Client;
import client.HibernateClientCrudService;
import client.IClientCrudService;

import java.sql.SQLException;

public class readByNameClientCrud extends CliState {
    public readByNameClientCrud(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        try {
            IClientCrudService ClientCrudService = new HibernateClientCrudService();

            System.out.println("Reading. Enter client name:");
            String name = fsm.getScanner().nextLine();

            Client client = ClientCrudService.getByName(name);

            if (client != null) {
                System.out.println("Client " + client.getId() + " found.");
            } else {
                System.out.println("Client with name " + name + " not found:");
            }

            fsm.setState(new IdleState(fsm));
//        } catch (SQLException | ExecutionException | InterruptedException e) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}