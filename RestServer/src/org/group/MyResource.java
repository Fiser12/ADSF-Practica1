package org.group;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import org.group.model.Habitacion;
import org.group.model.HabitacionReserva;
import org.group.model.Reserva;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/hotel")
public class MyResource {
	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */

	@Context
	UriInfo uriInfo;
	public static ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	public static ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
	public static ArrayList<HabitacionReserva> habitacionesReserva = new ArrayList<HabitacionReserva>();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hello")
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/reserva/listado")
	public Reserva[] listadoReserva(){
		Reserva[] array = new Reserva[reservas.size()];
		System.out.println(reservas.size());
		array = reservas.toArray(array);
		return array;
	}
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/reserva/get/{id}")
	public Reserva getReserva(int id){
		for (Reserva reserva: reservas) {
			if(reserva.getReservaId().equals(new Integer(id))){
				return reserva;
			}
		}
		return null;
	}
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/reserva/update")
	public Response updateReserva(Reserva reserva){
		for (Reserva reservaTemp: reservas) {
			if(reservaTemp.getReservaId().equals(reserva.getReservaId())){
				reservaTemp.setApellidos(reserva.getApellidos());
				reservaTemp.setEndTime(reserva.getEndTime());
				reservaTemp.setStartTime(reserva.getStartTime());
				reservaTemp.setNombre(reserva.getNombre());
				reservaTemp.setNumeroPersonas(reserva.getNumeroPersonas());
				reservaTemp.setTelefono(reserva.getTelefono());
				reservaTemp.setPagado(reserva.getPagado());
				reservaTemp.setPrecio(reserva.getPrecio());
				reservaTemp.setTipoReserva(reserva.getTipoReserva());
				URI uri = uriInfo.getAbsolutePathBuilder().build();
				return Response.created(uri).entity(reserva).build(); // Code: 201
			}
		}
		return Response.status(409).entity("Error en actualizacion de Reservas").build();
	}
	@DELETE
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/reserva/delete/{id}")
	public Response deleteReserva(int id){
		for(int i = 0; i<reservas.size(); i++){
			if(reservas.get(i).getReservaId().equals(new Integer(id))){
				reservas.remove(i);
				URI uri = uriInfo.getAbsolutePathBuilder().build();
				return Response.created(uri).entity(id).build(); // Code: 201
			}
		}
		return Response.status(409).entity("No se ha podido borrar la reserva").build();
	}
	@POST
	@Path("/reserva/create")
	public Response createReserva(Reserva reserva){
		System.out.println(reserva.getNombre());
		reservas.add(reserva);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(reserva).build(); // Code: 201
	}
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({"application/json"})
	@Path("/habitacionesLibres")
	public List<Habitacion> getHabitacionesLibres(SelectHabitacion valor){
		List<Habitacion> devolver = new LinkedList<Habitacion>();
		for (Habitacion habitacion: habitaciones) {
			if(habitacion.getTipoHabitacion().equals(valor.tipoReserva))
			{
				boolean libre = true;
				for(HabitacionReserva habitacionReserva: habitacionesReserva)
				{
					if(habitacionReserva.getHabitacion().getHabitacionID().equals(habitacion.getHabitacionID())){
						if( valor.startDate.compareTo(habitacionReserva.getStartDate()) * habitacionReserva.getStartDate().compareTo(valor.endDate) > 0 || valor.startDate.compareTo(habitacionReserva.getEndDate()) * habitacionReserva.getEndDate().compareTo(valor.endDate) > 0)
						{//Entra aqu√≠ si esa franja de tiempo de inicio y fin que ya est√° ocupada por una reserva en esa habitaci√≥n
							libre = false;
						}
					}
				}
				if(libre)
				{
					devolver.add(habitacion);
				}
			}
		}
		return devolver;
	}
	@GET
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/habitacionreserva/get")
	public List<HabitacionReserva> getHabitacionesReserva(Reserva reserva)
	{
		List<HabitacionReserva> devolver = new LinkedList<HabitacionReserva>();       
		for(HabitacionReserva habitacionReserva: habitacionesReserva)
		{
			if(habitacionReserva.getReserva().getReservaId()==reserva.getReservaId())
			{
				devolver.add(habitacionReserva);
			}
		}
		return devolver;

	}
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/habitacion/asignar")
	public Response asignarHabitacion(HabitacionReserva habitacionReserva){
		for(int i = 0; i<habitacionesReserva.size(); i++){
			if(habitacionesReserva.get(i).getHabitacion().getHabitacionID().equals(habitacionReserva.getHabitacion().getHabitacionID())&&habitacionesReserva.get(i).getReserva().getReservaId().equals(habitacionReserva.getReserva().getReservaId())){
				return Response.status(409).entity("Error al asignar habitaciÛn").build();
			}
		}
		habitacionesReserva.add(habitacionReserva);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(habitacionesReserva).build(); // Code: 201
	}
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({"application/json"})
	@Path("/habitacion/desasignar")
	public Response desasignarHabitacion(HabitacionReserva habitacionReserva){
		for(int i = 0; i<habitacionesReserva.size(); i++){
			if(habitacionesReserva.get(i).getHabitacion().getHabitacionID().equals(new Integer(habitacionReserva.getHabitacion().getHabitacionID()))&&habitacionesReserva.get(i).getReserva().getReservaId().equals(new Integer(habitacionReserva.getReserva().getReservaId()))){
				habitacionesReserva.remove(i);
				URI uri = uriInfo.getAbsolutePathBuilder().build();
				return Response.created(uri).entity(habitacionesReserva).build(); // Code: 201
			}
		}
		return Response.status(409).entity("Error al desasignar habitaciÛn").build();
	}
	@XmlRootElement
	class SelectHabitacion{
		public Date startDate;
		public Date endDate;
		public String tipoReserva;
	}
}
