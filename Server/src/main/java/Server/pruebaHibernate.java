package Server;

import java.util.Date;
import org.hibernate.Session;
import Server.Model.Habitacion;
import Server.Model.Reserva;
import Server.Model.HabitacionReserva;

public class pruebaHibernate {
    public static void main(String[] args) {
        System.out.println("Hibernate many to many - join table + extra column (Annotation)");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Habitacion stock = new Habitacion();
        stock.setPlanta(12);
        stock.setTipoHabitacion("PADINI");

        Reserva category1 = new Reserva();
        //new category, need save to get the id first
        session.save(category1);

        //Category category1 = (Category)session.get(Category.class, 8);

        HabitacionReserva stockCategory = new HabitacionReserva();

        stockCategory.setHabitacion(stock);
        stockCategory.setReserva(category1);
        stockCategory.setStartDate(new Date());
        stockCategory.setEndDate(new Date());
        stockCategory.setCreatedBy("system");
        stock.getHabitacionReservas().add(stockCategory);
        session.save(stock);

        session.getTransaction().commit();
        System.out.println("Done");
    }
}
