package struts.reserva;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import hibernate.dao.ReservaDAO;
import hibernate.model.Reserva;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class ReservaAction extends ActionSupport
	implements ModelDriven<Reserva>{

	Reserva reserva = new Reserva();
	List<Reserva> reservaList = new ArrayList<Reserva>();
    ReservaDAO dao = new ReservaDAO();
    private String buscar;

    @Override

    public Reserva getModel() {
        return reserva;
    }

    public String execute()
    {
        reserva.setPagado(0);
        double precioDia;
        if(reserva.getTipoReserva().equals("Premium"))
            precioDia = 127;
        else if(reserva.getTipoReserva().equals("Deluxe"))
            precioDia = 143;
        else
            precioDia = 65;
        reserva.setPrecio(precioDia* (reserva.getNumeroPersonas()/2)*Util.daysBetween(reserva.getStartTime(), reserva.getEndTime()));
        dao.addReserva(reserva);
        return "success";
    }
    public String listReservas()
    {
        reservaList = dao.getStudents();
        return "success";
    }
    public String getReservaById()
    {
        boolean search = false;
        listReservas();
        for(Reserva reserva: reservaList){
            if(reserva.getReservaId().toString().equals(reserva.getReservaId().toString())){
                this.reserva = reserva;
                search = true;
            }
        }
        if(search) {
            return "success";
        }else{
            return "fail";
        }
    }
    public String getReservaByIdParameter(){
        boolean search = false;
        listReservas();
        for(Reserva reserva: reservaList){
            if(reserva.getReservaId().toString().equals(buscar)){
                this.reserva = reserva;
                search = true;
            }
        }
        if(search) {
            return "success";
        }else{
            return "fail";
        }

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

    public String index() {
        return "success";
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public String getBuscar() {
        return buscar;
    }
}