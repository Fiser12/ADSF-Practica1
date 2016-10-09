package Server;

import Server.Model.Habitacion;
import org.hibernate.Session;

import java.sql.Date;

/**
 * Created by Fiser on 9/10/16.
 */
public class pruebaHibernate {
    public static void main(String[] args) {
        System.out.println("Hibernate one to one (XML mapping)");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Habitacion stock = new Habitacion();
        stock.setPlanta(5);
        stock.setTipoHabitacion("Mala");
        session.save(stock);
        session.getTransaction().commit();
        System.out.println("Done");
    }

}
