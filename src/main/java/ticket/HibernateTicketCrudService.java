package ticket;

import client.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import planet.HibernatePlanetCrudService;
import planet.IPlanetCrudService;
import planet.Planet;
import storage.hibernate.HibernateUtil;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.SQLException;

import static java.util.Objects.isNull;

public class HibernateTicketCrudService implements ITicketCrudService {
    @Override
    public void create(Ticket ticket) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ticket);
        transaction.commit();
        session.close();
    }
    @Override
    public Ticket getById(long id) throws SQLException {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Query<Ticket> query = session.createQuery(
                    "from Ticket where id = :id",
                    Ticket.class
            );
            query.setParameter("id", id);
            return query.stream().findFirst().orElse(null);
        }
    }
    @Override
    public void update(long id) throws SQLException {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket existing = session.get(Ticket.class, id);
            existing.getClient().setName("Agent Smith");
            session.persist(existing.getClient());
            transaction.commit();
        }
    }
    @Override
    public void delete(long id) throws SQLException {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery(
                    "delete from Ticket where id= :id"
            );
            query.setParameter("id", id);
            System.out.println("Deleted " + query.executeUpdate());
            transaction.commit();
        }
    }

    public Ticket createRandomTicket() throws SQLException {
        IPlanetCrudService planetCrudService = new HibernatePlanetCrudService();
        IClientCrudService clientCrudService = new HibernateClientCrudService();

        Ticket ticket = new Ticket();
        Planet pFrom, pTo;
        Client client;
        String pId;
        int iFrom = 0, iTo = 0;

        do {
            pId = RandomStringUtils.randomAlphabetic(4);
            pFrom = planetCrudService.getById(pId);
            ++iFrom;
        }
        while (isNull(pFrom));
        System.out.println("PlanetFrom found on " + iFrom + "'s try. It's " + pFrom.getName());

        do {
            pId = RandomStringUtils.randomAlphabetic(4);
            pTo = planetCrudService.getById(pId);
            ++iTo;
        }
        while (isNull(pTo));
        System.out.println("PlanetTo found on " + iTo + "'s try. It's " + pTo.getName());

        client = new Client();
        client.setName("Pluto");
        clientCrudService.create(client);

        ticket.setPlanet_from(pFrom);
        ticket.setPlanet_to(pTo);
        ticket.setClient(client);
        create(ticket);

        return ticket;
    }
}
