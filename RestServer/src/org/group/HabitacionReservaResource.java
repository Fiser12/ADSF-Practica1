package org.group;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.group.model.HabitacionReserva;
import java.net.URI;
import java.util.ArrayList;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "HabitacionResource" path)
 */
@Path("/habitacionReserva")
public class HabitacionReservaResource {
	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */

	@Context
	UriInfo uriInfo;
	public static ArrayList<HabitacionReserva> habitacionesReserva = MockupDatabase.habitacionesReserva;

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/")
	public HabitacionReserva[] listadoHabitacionReserva(){
		HabitacionReserva[] array = new HabitacionReserva[habitacionesReserva.size()];
		array = habitacionesReserva.toArray(array);
		return array;
	}
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{idHabitacion}/{idReserva}")
	public HabitacionReserva getHabitacion(int idHabitacion, int idReserva){
		for (HabitacionReserva habitacion: habitacionesReserva) {
			if(habitacion.getHabitacion().getHabitacionID().equals(new Integer(idHabitacion))&&habitacion.getReserva().getReservaId().equals(new Integer(idReserva))){
				return habitacion;
			}
		}
		return null;
	}


	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{idHabitacion}/{idReserva}")
	public Response updateHabitacion(@PathParam("idHabitacion") Integer idHabitacion, @PathParam("idReserva") Integer idReserva, HabitacionReserva habitacionReserva){
		for (HabitacionReserva habitacionTemp: habitacionesReserva) {
			if(habitacionTemp.getHabitacion().getHabitacionID().equals(new Integer(idHabitacion))&&habitacionTemp.getReserva().getReservaId().equals(new Integer(idReserva))){
				habitacionTemp.setStartDate(habitacionReserva.getStartDate());
				habitacionTemp.setEndDate(habitacionReserva.getEndDate());
			    return Response.ok().entity(habitacionTemp).build();
			}
		}
		return Response.status(409).entity("Error en actualizacion de habitaciones").build();
	}
	@POST
	@Path("/")
	public Response createHabitacion(HabitacionReserva Habitacion){
		habitacionesReserva.add(Habitacion);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(Habitacion).build(); // Code: 201
	}
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{idHabitacion}/{idReserva}")
	public Response deleteHabitacion(@PathParam("idHabitacion") Integer idHabitacion, @PathParam("idReserva") Integer idReserva){
		for(int i = 0; i<habitacionesReserva.size(); i++){
			if(habitacionesReserva.get(i).getHabitacion().getHabitacionID().equals(idHabitacion)&&habitacionesReserva.get(i).getReserva().getReservaId().equals(idReserva)){
				habitacionesReserva.remove(i);
			    return Response.status(201).entity("Delete succesfull").build();
			}
		}
		return Response.status(409).entity("No se ha podido borrar la Habitacion").build();
	}
}
