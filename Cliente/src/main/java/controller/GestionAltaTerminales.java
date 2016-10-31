package controller;

import org.apache.ws.axis2.CreateReserva;
import org.apache.ws.axis2.CreateReservaResponse;
import org.apache.ws.axis2.DeleteReserva;
import org.apache.ws.axis2.DeleteReservaResponse;
import org.apache.ws.axis2.ListadoReserva;
import org.apache.ws.axis2.ListadoReservaResponse;
import org.apache.ws.axis2.ServicioHotelStub;
import org.apache.ws.axis2.UpdateReserva;
import org.apache.ws.axis2.UpdateReservaResponse;
import model.xsd.Reserva;

public class GestionAltaTerminales {
	private ServicioHotelStub stub = null;
    private static final GestionAltaTerminales INSTANCE = new GestionAltaTerminales();
    private GestionAltaTerminales() {}
    public static GestionAltaTerminales getInstance() {
        return INSTANCE;
    }

	public boolean crearReserva(Reserva r){
		try {
			if (stub == null)
				stub  = new ServicioHotelStub("http://localhost:8080/axis2/services/HotelService");
			CreateReserva nuevaReserva = new CreateReserva();
			nuevaReserva.setReserva(r);
			CreateReservaResponse nuevaReservaRes = stub.createReserva(nuevaReserva);
			return nuevaReservaRes.get_return();
		}catch (java.lang.Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	public Reserva[] listarReservas(){
		try {
			if (stub == null)
				stub  = new ServicioHotelStub("http://localhost:8080/axis2/services/HotelService");
			ListadoReserva lreserva = new ListadoReserva();
			ListadoReservaResponse lReservaRes = stub.listadoReserva(lreserva);
			return lReservaRes.get_return();
		}catch (java.lang.Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}	
	public boolean actualizarReserva(Reserva r){
		try {
			if (stub == null)
				stub  = new ServicioHotelStub("http://localhost:8080/axis2/services/HotelService");
			UpdateReserva ureserva = new UpdateReserva();
			ureserva.setReserva(r);
			UpdateReservaResponse uReservaRes = stub.updateReserva(ureserva);
			return uReservaRes.get_return();
		}catch (java.lang.Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	public boolean borrarReserva(Reserva r){
		try {
			if (stub == null)
				stub  = new ServicioHotelStub("http://localhost:8080/axis2/services/HotelService");
			DeleteReserva dreserva = new DeleteReserva();
			dreserva.setId(r.getReservaId());
			DeleteReservaResponse dreservaRes = stub.deleteReserva(dreserva);
			return dreservaRes.get_return();
		}catch (java.lang.Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
