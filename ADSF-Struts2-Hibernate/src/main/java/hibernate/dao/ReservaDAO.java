package hibernate.dao;

import hibernate.model.Reserva;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fiser on 21/12/16.
 */
public class ReservaDAO {

    private Session session;
    private static URL url = ReservaDAO.class.getResource("/hibernate.cfg.xml");
    private static AnnotationConfiguration config = new AnnotationConfiguration().configure(url);
    private static SessionFactory sessionFactory = config.buildSessionFactory();

    public List<Reserva> getStudents()
    {
        session = sessionFactory.openSession();

        List<Reserva> students = new ArrayList<Reserva>();
        try
        {
            students = session.createQuery("from Reserva").list();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        session.close();
        return students;
    }

    public void addReserva(Reserva reserva)
    {
        session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(reserva);
            tx.commit();
            session.flush();
        } catch(Exception e) {
            tx.rollback();
        }
    }
}

