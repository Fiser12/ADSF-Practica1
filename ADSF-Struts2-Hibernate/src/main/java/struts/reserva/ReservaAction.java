package struts.reserva;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import hibernate.dao.ReservaDAO;
import hibernate.model.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservaAction extends ActionSupport
	implements ModelDriven<Reserva>{

	Reserva reserva = new Reserva();
	List<Reserva> reservaList = new ArrayList<Reserva>();
    ReservaDAO dao = new ReservaDAO();
    @Override

    public Reserva getModel() {
        return reserva;
    }

    public String execute()
    {
        dao.addReserva(reserva);
        return "success";
    }
    public String listReservas()
    {
        reservaList = dao.getStudents();
        return "success";
    }
    public Reserva getReserva() {
        return reserva;
    }
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    public List<Reserva> getReservaList() {
        return reservaList;
    }
    public void setReservaList(List<Reserva> reservas) {
        this.reservaList = reservas;
    }

}