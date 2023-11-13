package cli;

import client.Client;
import client.HibernateClientCrudService;
import client.IClientCrudService;

import java.sql.SQLException;

public class GetClientCrud extends CliState {
    public GetClientCrud(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        Client client;
        try {
            IClientCrudService ClientCrudService = new HibernateClientCrudService();

            String sid;
            do {
                System.out.println("Enter client Id:");
                sid = fsm.getScanner().nextLine();
                client = ClientCrudService.getById(Long.parseLong(sid));
                if ( client == null ) System.out.println("Not found. Enter another Client ID or type exit:");
            }
            while ( client == null && !sid.equals("exit"));

            fsm.setState(new IdleState(fsm));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return client;
    }
}