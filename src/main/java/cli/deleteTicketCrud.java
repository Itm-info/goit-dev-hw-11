package cli;

import client.Client;
import client.HibernateClientCrudService;
import client.IClientCrudService;

import java.sql.SQLException;

public class deleteClientCrud extends CliState {
    public deleteClientCrud(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        try {
            IClientCrudService ClientCrudService = new HibernateClientCrudService();

            System.out.println("Deleting. Enter client Id:");
            String sid = fsm.getScanner().nextLine();
            long id = Long.parseLong(sid);

            Client client = ClientCrudService.getById(id);

            if (client != null) {
                System.out.println("Client " + client.getName() + " found. Processing...");
                ClientCrudService.delete(id);
            } else {
                System.out.println("Client with id " + id + " not found:");
            }

            fsm.setState(new IdleState(fsm));
//        } catch (SQLException | ExecutionException | InterruptedException e) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}