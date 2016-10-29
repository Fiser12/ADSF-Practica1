import java.rmi.RemoteException;

import org.apache.ws.axis2.HelloService;
import org.apache.ws.axis2.HelloServiceResponse;
import org.apache.ws.axis2.ServicioHotelStub;

/**
 * Created by Fiser on 4/10/16.
 */

public class GestionAltaTerminales {

	public static void main(String[] args){
		ServicioHotelStub stub;
		try {
			stub = new ServicioHotelStub("http://localhost:8080/axis2/services/HotelService");

			HelloService hs = new HelloService();
			HelloServiceResponse hsr = stub.helloService(hs);
			System.out.println(hsr.get_return());

		}catch (java.lang.Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
