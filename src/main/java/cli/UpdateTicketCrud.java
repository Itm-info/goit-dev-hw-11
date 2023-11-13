package cli;

import ticket.Ticket;
import ticket.HibernateTicketCrudService;
import ticket.ITicketCrudService;

import java.sql.SQLException;

public class UpdateTicketCrud extends CliState {
    public UpdateTicketCrud(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        try {
            ITicketCrudService TicketCrudService = new HibernateTicketCrudService();

            System.out.println("Updating. Enter ticket Id:");
            String sid = fsm.getScanner().nextLine();
            long id = Long.parseLong(sid);

            Ticket ticket = TicketCrudService.getById(id);

            if (ticket != null) {
                System.out.println(ticket + " found. Processing...");
                TicketCrudService.update(id);
                System.out.println("New client's name is " + TicketCrudService.getById(id).getClient().getName() + ".");
            } else {
                System.out.println("Ticket with id " + id + " not found:");
            }

            fsm.setState(new IdleState(fsm));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}