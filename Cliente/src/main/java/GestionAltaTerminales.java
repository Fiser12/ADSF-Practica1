import java.util.Date;

import org.apache.ws.axis2.CreateReserva;
import org.apache.ws.axis2.CreateReservaResponse;
import org.apache.ws.axis2.DeleteReserva;
import org.apache.ws.axis2.DeleteReservaResponse;
import org.apache.ws.axis2.GetReserva;
import org.apache.ws.axis2.GetReservaResponse;
import org.apache.ws.axis2.HelloService;
import org.apache.ws.axis2.HelloServiceResponse;
import org.apache.ws.axis2.ListadoReserva;
import org.apache.ws.axis2.ListadoReservaResponse;
import org.apache.ws.axis2.ServicioHotelStub;
import org.apache.ws.axis2.UpdateReserva;
import org.apache.ws.axis2.UpdateReservaResponse;

import model.xsd.Reserva;

/**
 * Created by Fiser on 4/10/16.
 */

public class GestionAltaTerminales {

	public static void crearReserva(ServicioHotelStub stub, Reserva r){
		try {
			CreateReserva nuevaReserva = new CreateReserva();
			nuevaReserva.setReserva(r);
			CreateReservaResponse nuevaReservaRes = stub.createReserva(nuevaReserva);
			System.out.println("Si sale true va bien: " + nuevaReservaRes.get_return());
		}catch (java.lang.Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void listarReservas(ServicioHotelStub stub){
		try {
			ListadoReserva lreserva = new ListadoReserva();
			ListadoReservaResponse lReservaRes = stub.listadoReserva(lreserva);
			//System.out.println(lReservaRes.get_return());
			for(int i = 0; i < lReservaRes.get_return().length; i++){
				System.out.println(lReservaRes.get_return()[i].getNombre());
			}
		}catch (java.lang.Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void obtenerReserva(ServicioHotelStub stub, Reserva r){
		try {
			GetReserva greserva = new GetReserva();
			greserva.setId(r.getReservaId());
			GetReservaResponse gReservaRes = stub.getReserva(greserva);
			System.out.println("Si sale true lo ha encontrado: " +gReservaRes.get_return());
		}catch (java.lang.Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void actualizarReserva(ServicioHotelStub stub, Reserva r){
		try {
			UpdateReserva ureserva = new UpdateReserva();
			ureserva.setReserva(r);
			UpdateReservaResponse uReservaRes = stub.updateReserva(ureserva);
			System.out.println("Si sale true lo ha actualizado: " +uReservaRes.get_return());
		}catch (java.lang.Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void borrarReserva(ServicioHotelStub stub, Reserva r){
		try {
			DeleteReserva dreserva = new DeleteReserva();
			dreserva.setId(r.getReservaId());
			DeleteReservaResponse dreservaRes = stub.deleteReserva(dreserva);
			System.out.println("Si sale true lo ha borrado: " + dreservaRes.get_return());
		}catch (java.lang.Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		ServicioHotelStub stub;
		try {
			stub = new ServicioHotelStub("http://localhost:8080/axis2/services/HotelService");
			HelloService hs = new HelloService();
			HelloServiceResponse hsr = stub.helloService(hs);
			System.out.println(hsr.get_return());
			
			Reserva r = new Reserva();
	        r.setApellidos("Prueba");
	        r.setStartTime(new Date());
	        r.setEndTime(new Date());
	        r.setNombre("Prueba");
	        r.setPrecio(1000);
	        r.setTelefono(678964578);
	        
	        Reserva r2 = new Reserva();
	        r2.setApellidos("Prueba2");
	        r2.setStartTime(new Date());
	        r2.setEndTime(new Date());
	        r2.setNombre("Prueba2");
	        r2.setPrecio(15000);
	        r2.setTelefono(678978978);
			
	        //prueba nueva reserva
			crearReserva(stub, r);
			crearReserva(stub, r2);
			
			//prueba listado
			listarReservas(stub);
			
			//prueba obtener una reserva concreta
			//obtenerReserva(stub, r2);
			
			/*prueba actualizar una reserva concreta; 
			 * Supongo que aquí ya hemos seleccionado previamente la que queremos actualizar
			 */
			//r2.setPrecio(25000); //finjo que desde la ventana he cambiado un dato
			//actualizarReserva(stub, r2); //envío un r2 cambiado con respecto al almacenado
			
			//prueba eliminar reserva
			//borrarReserva(stub, r);
			
		}catch (java.lang.Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
