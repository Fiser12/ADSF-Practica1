package org.group;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.group.model.Habitacion;
import java.net.URI;
import java.util.ArrayList;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "HabitacionResource" path)
 */
@Path("/habitacion")
public class HabitacionResource {
	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */

	@Context
	UriInfo uriInfo;
	public static ArrayList<Habitacion> habitaciones = MockupDatabase.habitaciones;
	public HabitacionResource(){
		if(habitaciones.isEmpty())
			for(int i = 0; i<6; i++)
				for(int j = 0; j<60; j++)
					if(j<30)
						habitaciones.add(new Habitacion(new Integer(i*100+j), i, "Normal"));
					else if(j<50)
						habitaciones.add(new Habitacion(new Integer(i*100+j), i, "Deluxe"));
					else
						habitaciones.add(new Habitacion(new Integer(i*100+j), i, "Premium"));
	}
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/")
	public Habitacion[] listadoHabitacion(){
		Habitacion[] array = new Habitacion[habitaciones.size()];
		array = habitaciones.toArray(array);
		return array;
	}
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Habitacion getHabitacion(int id){
		for (Habitacion Habitacion: habitaciones) {
			if(Habitacion.getHabitacionID().equals(new Integer(id))){
				return Habitacion;
			}
		}
		return null;
	}


	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{id}")
	public Response updateHabitacion(@PathParam("id") Integer id, Habitacion habitacion){
		for (Habitacion habitacionTemp: habitaciones) {
			if(habitacionTemp.getHabitacionID().equals(id)){
				habitacionTemp.setPlanta(habitacion.getPlanta());
				habitacionTemp.setTipoHabitacion(habitacion.getTipoHabitacion());
			    return Response.ok().entity(habitacionTemp).build();
			}
		}
		return Response.status(409).entity("Error en actualizacion de habitaciones").build();
	}
	@POST
	@Path("/")
	public Response createHabitacion(Habitacion Habitacion){
		habitaciones.add(Habitacion);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(Habitacion).build(); // Code: 201
	}
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}")
	public Response deleteHabitacion(@PathParam("id") Integer id){
		System.out.println(id);
		for(int i = 0; i<habitaciones.size(); i++){
			if(habitaciones.get(i).getHabitacionID().equals(id)){
				habitaciones.remove(i);
			    return Response.status(201).entity("Delete succesfull").build();
			}
		}
		return Response.status(409).entity("No se ha podido borrar la Habitacion").build();
	}
	/*@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({"application/json"})
	@Path("/habitacionesLibres")
	public List<Habitacion> getHabitacionesLibres(SelectHabitacion valor){
		List<Habitacion> devolver = new LinkedList<Habitacion>();
		for (Habitacion habitacion: habitaciones) {
			if(habitacion.getTipoHabitacion().equals(valor.tipoHabitacion))
			{
				boolean libre = true;
				for(HabitacionHabitacion habitacionHabitacion: habitacionesHabitacion)
				{
					if(habitacionHabitacion.getHabitacion().getHabitacionID().equals(habitacion.getHabitacionID())){
						if( valor.startDate.compareTo(habitacionHabitacion.getStartDate()) * habitacionHabitacion.getStartDate().compareTo(valor.endDate) > 0 || valor.startDate.compareTo(habitacionHabitacion.getEndDate()) * habitacionHabitacion.getEndDate().compareTo(valor.endDate) > 0)
						{//Entra aquí si esa franja de tiempo de inicio y fin que ya está ocupada por una Habitacion en esa habitación
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
	@Path("/habitacionHabitacion/get")
	public List<HabitacionHabitacion> getHabitacionesHabitacion(Habitacion Habitacion)
	{
		List<HabitacionHabitacion> devolver = new LinkedList<HabitacionHabitacion>();       
		for(HabitacionHabitacion habitacionHabitacion: habitacionesHabitacion)
		{
			if(habitacionHabitacion.getHabitacion().getHabitacionId()==Habitacion.getHabitacionId())
			{
				devolver.add(habitacionHabitacion);
			}
		}
		return devolver;

	}
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/habitacion/asignar")
	public Response asignarHabitacion(HabitacionHabitacion habitacionHabitacion){
		for(int i = 0; i<habitacionesHabitacion.size(); i++){
			if(habitacionesHabitacion.get(i).getHabitacion().getHabitacionID().equals(habitacionHabitacion.getHabitacion().getHabitacionID())&&habitacionesHabitacion.get(i).getHabitacion().getHabitacionId().equals(habitacionHabitacion.getHabitacion().getHabitacionId())){
				return Response.status(409).entity("Error al asignar habitaci�n").build();
			}
		}
		habitacionesHabitacion.add(habitacionHabitacion);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(habitacionesHabitacion).build(); // Code: 201
	}
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({"application/json"})
	@Path("/habitacion/desasignar")
	public Response desasignarHabitacion(HabitacionHabitacion habitacionHabitacion){
		for(int i = 0; i<habitacionesHabitacion.size(); i++){
			if(habitacionesHabitacion.get(i).getHabitacion().getHabitacionID().equals(new Integer(habitacionHabitacion.getHabitacion().getHabitacionID()))&&habitacionesHabitacion.get(i).getHabitacion().getHabitacionId().equals(new Integer(habitacionHabitacion.getHabitacion().getHabitacionId()))){
				habitacionesHabitacion.remove(i);
				URI uri = uriInfo.getAbsolutePathBuilder().build();
				return Response.created(uri).entity(habitacionesHabitacion).build(); // Code: 201
			}
		}
		return Response.status(409).entity("Error al desasignar habitaci�n").build();
	}
	@XmlRootElement
	class SelectHabitacion{
		public Date startDate;
		public Date endDate;
		public String tipoHabitacion;
	}
	*/
}
