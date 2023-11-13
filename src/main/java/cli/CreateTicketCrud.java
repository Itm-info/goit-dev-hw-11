package cli;

import client.Client;
import client.HibernateClientCrudService;
import client.IClientCrudService;
import planet.HibernatePlanetCrudService;
import planet.IPlanetCrudService;
import planet.Planet;
import ticket.Ticket;
import ticket.HibernateTicketCrudService;
import ticket.ITicketCrudService;

import java.sql.SQLException;

public class CreateTicketCrud extends CliState {
    public CreateTicketCrud(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        try {

            IClientCrudService ClientCrudService = new HibernateClientCrudService();
            System.out.println("Ticket Creation. Enter Client ID:");
            Client client; String sid;
            do {
                sid = fsm.getScanner().nextLine();
                if( sid.equals("exit") ) System.exit(0);
                client = ClientCrudService.getById(Long.valueOf(sid));
                if ( client == null ) System.out.println("Not found. Enter another Client ID or type exit:");
            }
            while ( client == null );


            IPlanetCrudService PlanetCrudService = new HibernatePlanetCrudService();
            System.out.println("Ticket Creation. Enter Planet_From ID:");
            Planet planet_from;
            do {
                sid = fsm.getScanner().nextLine();
                if( sid.equals("exit") ) System.exit(0);
                planet_from = PlanetCrudService.getById(sid);
                if ( planet_from == null ) System.out.println("Not found. Enter another Planet_From ID or type exit:");
            }
            while ( planet_from == null );

            System.out.println("Ticket Creation. Enter Planet_To ID:");
            Planet planet_to;
            do {
                sid = fsm.getScanner().nextLine();
                if ( sid.equals("exit") ) System.exit(0);
                planet_to = PlanetCrudService.getById(sid);
                if ( planet_to == null ) System.out.println("Not found. Enter another Planet_To ID or type exit:");
            }
            while ( planet_to == null );


            ITicketCrudService TicketCrudService = new HibernateTicketCrudService();
            Ticket ticket = new Ticket();
            ticket.setClient(client);
            ticket.setPlanet_from(planet_from);
            ticket.setPlanet_to(planet_to);
            TicketCrudService.create(ticket);

            System.out.println(ticket + " created and saved.");

            fsm.setState(new IdleState(fsm));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}