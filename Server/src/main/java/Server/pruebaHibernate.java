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
        category1.setApellidos("Perosna1");
        category1.setStartTime(new Date());
        category1.setEndTime(new Date());
        category1.setNombre("HOLA");
        category1.setPrecio(4234);
        category1.setTelefono(14231424);
        session.save(category1);


        HabitacionReserva stockCategory = new HabitacionReserva();
        stockCategory.setHabitacion(stock);
        stockCategory.setReserva(category1);
        stockCategory.setStartDate(new Date());
        stockCategory.setEndDate(new Date());
        stockCategory.setCreatedBy("system");
        stock.getHabitacionReservas().add(stockCategory);
        category1.getHabitacionReservas().add(stockCategory);
        session.save(stock);
        session.getTransaction().commit();
        System.out.println("Done");
    }
}
