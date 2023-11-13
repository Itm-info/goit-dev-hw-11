package ticket;

import java.sql.SQLException;
public interface ITicketCrudService {
    void create(Ticket ticket) throws SQLException;
    Ticket getById(long id) throws SQLException;
    void update(long id) throws SQLException;
    void delete(long id) throws SQLException;
}