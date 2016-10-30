import java.util.Date;

import org.apache.ws.axis2.CreateReserva;
import org.apache.ws.axis2.CreateReservaResponse;
import org.apache.ws.axis2.HelloService;
import org.apache.ws.axis2.HelloServiceResponse;
import org.apache.ws.axis2.ListadoReserva;
import org.apache.ws.axis2.ListadoReservaResponse;
import org.apache.ws.axis2.ServicioHotelStub;

import model.xsd.Reserva;

/**
 * Created by Fiser on 4/10/16.
 */

public class GestionAltaTerminales {

	public static void CrearReserva(ServicioHotelStub stub, Reserva r){
		try {
			CreateReserva nuevaReserva = new CreateReserva();
			nuevaReserva.setReserva(r);
			CreateReservaResponse nuevaReservaRes = stub.createReserva(nuevaReserva);
			System.out.println(nuevaReservaRes.get_return());
		}catch (java.lang.Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		ServicioHotelStub stub;
		try {
			stub = new ServicioHotelStub("http://localhost:8080/axis2/services/ServicioHotel");
			HelloService hs = new HelloService();
			HelloServiceResponse hsr = stub.helloService(hs);
			System.out.println(hsr.get_return());
			
			//prueba nueva reserva
			Reserva r = new Reserva();
	        r.setApellidos("Prueba");
	        r.setStartTime(new Date());
	        r.setEndTime(new Date());
	        r.setNombre("Prueba");
	        r.setPrecio(1000);
	        r.setTelefono(678964578);
			CrearReserva(stub, r);
			
			//prueba listado
			ListadoReserva lreserva = new ListadoReserva();
			ListadoReservaResponse lreservaRes = stub.listadoReserva(lreserva);
			System.out.println(lreservaRes.get_return());
		}catch (java.lang.Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
