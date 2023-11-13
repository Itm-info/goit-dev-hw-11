package cli;

import client.HibernateClientCrudService;
import client.IClientCrudService;
import client.Client;

import java.sql.SQLException;

public class CreateClientCrud extends CliState {
    public CreateClientCrud(CliFSM fsm) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}