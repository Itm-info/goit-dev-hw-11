package storage.hibernate;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import client.Client;
import planet.Planet;
import storage.DatabaseInitService;

public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    @Getter
    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    private HibernateUtil() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .buildSessionFactory();
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }

    public static void main(String[] args) {
        //Init DB using flyway
        new DatabaseInitService().initDb();

        HibernateUtil util = HibernateUtil.getInstance();

        //Get single
//        Session session = util.getSessionFactory().openSession();
//        client client = session.get(client.class, 1L);
//        System.out.println("client = " + client);
//        session.close();

        //List all clients
//        Session session = util.getSessionFactory().openSession();
//        List<client> clients = session.createQuery("from client", client.class).list();
//        System.out.println("clients = " + clients);
//        session.close();

        //Save new client
//        Session session = util.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//                client newclient = new client();
//                newclient.setName("Boo");
//                session.persist(newclient);
//
//                System.out.println("newclient = " + newclient);
//            transaction.commit();
//        session.close();

        //Save new planet
//        Session session = util.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        planet newplanet = new planet();
//        newplanet.setId("BOO1");
//        newplanet.setName("Boo");
//        session.persist(newplanet);
//
//        System.out.println("newplanet = " + newplanet);
//        transaction.commit();
//        session.close();

        //Modify existing client
//        Session session = util.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//                client existing = session.get(client.class, 2L);
//                existing.setName("Modified client name");
//                session.persist(existing);
//            transaction.commit();
//        session.close();
    }
}