package planet;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import storage.hibernate.HibernateUtil;

import java.sql.SQLException;

public class HibernatePlanetCrudService implements IPlanetCrudService {
    @Override
    public void create(Planet planet) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(planet);
        transaction.commit();
        session.close();
    }
    @Override
    public Planet getById(String id) throws SQLException {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Query<Planet> query = session.createQuery(
                    "from Planet where id = :id",
                    Planet.class
            );
            query.setParameter("id", id);
            return query.stream().findFirst().orElse(null);
        }
    }
    @Override
    public void update(String id) throws SQLException {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
                Planet existing = session.get(Planet.class, id);
                existing.setName("Gaia");
                session.persist(existing);
            transaction.commit();
        }
    }
    @Override
    public void delete(String id) throws SQLException {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
                Query query = session.createQuery(
                        "delete from Planet where id= :id"
                );
                query.setParameter("id", id);
                System.out.println(query.executeUpdate());
            transaction.commit();
        }
    }
}
