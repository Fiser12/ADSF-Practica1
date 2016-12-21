package struts.reserva;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import hibernate.model.Reserva;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ReservaAction extends ActionSupport
	implements ModelDriven{

	Reserva reserva = new Reserva();
	List<Reserva> reservaList = new ArrayList<Reserva>();

	public String execute() throws Exception {
		return SUCCESS;
	}

	public Object getModel() {
		return reserva;
	}
	
	public List<Reserva> getReservaList() {
		return reservaList;
	}

	public void setReservaList(List<Reserva> reservaList) {
		this.reservaList = reservaList;
	}
	Session session = null;

	public String addReserva() throws Exception{
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Reserva reserva = new Reserva();
		session.save(reserva);
		tx.commit();
		reservaList = null;
        reservaList = session.createQuery("from Reserva").list();
		return SUCCESS;
	}
	public String listReserva() throws Exception{
    	SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
        reservaList = session.createQuery("from Reserva").list();
		return SUCCESS;
	}

}