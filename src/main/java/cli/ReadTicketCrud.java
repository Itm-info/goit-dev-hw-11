package cli;

import ticket.Ticket;
import ticket.HibernateTicketCrudService;
import ticket.ITicketCrudService;

import java.sql.SQLException;

public class ReadTicketCrud extends CliState {
    public ReadTicketCrud(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        try {
            ITicketCrudService TicketCrudService = new HibernateTicketCrudService();

            System.out.println("Reading. Enter ticket Id:");
            String sid = fsm.getScanner().nextLine();
            long id = Long.parseLong(sid);

            Ticket ticket = TicketCrudService.getById(id);

            if (ticket != null) {
                System.out.println(ticket + " found.");
            } else {
                System.out.println("Ticket with id " + id + " not found:");
            }

            fsm.setState(new IdleState(fsm));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}