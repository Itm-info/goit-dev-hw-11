package cli;

import client.HibernateClientCrudService;
import client.IClientCrudService;
import client.Client;
import jakarta.persistence.Id;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class createClientCrud extends CliState {
    public createClientCrud(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        try {
            IClientCrudService ClientCrudService = new HibernateClientCrudService();

            System.out.println("Creation. Enter client name:");

            String clientName = fsm.getScanner().nextLine();

            Client client = new Client();
            client.setName(clientName);
            ClientCrudService.create(client);

            System.out.println("Client " + client.getName() +  " saved.");

            fsm.setState(new IdleState(fsm));
//        } catch (SQLException | ExecutionException | InterruptedException e) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}