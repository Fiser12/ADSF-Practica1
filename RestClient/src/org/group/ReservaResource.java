package org.group;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.group.model.Reserva;
import java.net.URI;
import java.util.ArrayList;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "ReservaResource" path)
 */
@Path("/reserva")
public class ReservaResource {
	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */

	@Context
	UriInfo uriInfo;
	public static ArrayList<Reserva> reservas = MockupDatabase.reservas;

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/")
	public Reserva[] listadoReserva(){
		Reserva[] array = new Reserva[reservas.size()];
		array = reservas.toArray(array);
		return array;
	}
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Reserva getReserva(int id){
		for (Reserva reserva: reservas) {
			if(reserva.getReservaId().equals(new Integer(id))){
				return reserva;
			}
		}
		return null;
	}


	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{id}")
	public Response updateReserva(@PathParam("id") Integer id, Reserva reserva){
		for (Reserva reservaTemp: reservas) {
			if(reservaTemp.getReservaId().equals(id)){
				reservaTemp.setApellidos(reserva.getApellidos());
				reservaTemp.setEndTime(reserva.getEndTime());
				reservaTemp.setStartTime(reserva.getStartTime());
				reservaTemp.setNombre(reserva.getNombre());
				reservaTemp.setNumeroPersonas(reserva.getNumeroPersonas());
				reservaTemp.setTelefono(reserva.getTelefono());
				reservaTemp.setPagado(reserva.getPagado());
				reservaTemp.setPrecio(reserva.getPrecio());
				reservaTemp.setTipoReserva(reserva.getTipoReserva());
			    return Response.ok().entity(reservaTemp).build();
			}
		}
		return Response.status(409).entity("Error en actualizacion de Reservas").build();
	}
	@POST
	@Path("/")
	public Response createReserva(Reserva reserva){
		reservas.add(reserva);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(reserva).build(); // Code: 201
	}
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}")
	public Response deleteReserva(@PathParam("id") Integer id){
		System.out.println(id);
		for(int i = 0; i<reservas.size(); i++){
			if(reservas.get(i).getReservaId().equals(id)){
				reservas.remove(i);
			    return Response.status(201).entity("Delete succesfull").build();
			}
		}
		return Response.status(409).entity("No se ha podido borrar la reserva").build();
	}
}
