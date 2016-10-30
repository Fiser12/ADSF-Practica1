/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import model.Habitacion;
import model.HabitacionReserva;
import model.Reserva;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SimpleService {
    ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
    ArrayList<HabitacionReserva> habitacionesReserva = new ArrayList<HabitacionReserva>();
    
    public String  helloService(){
        return "Hello ";
    }
    public Reserva[] listadoReserva(){
        Reserva[] array = new Reserva[reservas.size()];
        array = reservas.toArray(array);
        return array;
    }
    public Reserva getReserva(int id){
        for (Reserva reserva: reservas) {
        	if(reserva.getReservaId().equals(new Integer(id))){
        		return reserva;
        	}
        }
        return null;
    }
    public boolean updateReserva(Reserva reserva){
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
	                return true;
	        	}
	        }
        return false;
    }
    public boolean createReserva(Reserva reserva){
    	reservas.add(reserva);
    	return true;
    }
    public boolean deleteReserva(int id){
    	for(int i = 0; i<reservas.size(); i++){
    		if(reservas.get(i).getReservaId().equals(new Integer(id))){
        		reservas.remove(i);
                return true;
        	}
        }
    return false;
    }
	public List<Habitacion> getHabitacionesLibres(Date startDate, Date endDate, String tipoReserva){
        List<Habitacion> devolver = new LinkedList<Habitacion>();
        for (Habitacion habitacion: habitaciones) {
        	if(habitacion.getTipoHabitacion().equals(tipoReserva))
        	{
        		boolean libre = true;
                for(HabitacionReserva habitacionReserva: habitacionesReserva)
                {
                	if(habitacionReserva.getHabitacion().getHabitacionID().equals(habitacion.getHabitacionID())){
                		if( startDate.compareTo(habitacionReserva.getStartDate()) * habitacionReserva.getStartDate().compareTo(endDate) > 0 || startDate.compareTo(habitacionReserva.getEndDate()) * habitacionReserva.getEndDate().compareTo(endDate) > 0)
                		{//Entra aquí si esa franja de tiempo de inicio y fin que ya está ocupada por una reserva en esa habitación
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
    public boolean asignarHabitacion(HabitacionReserva habitacionReserva){
    	for(int i = 0; i<habitacionesReserva.size(); i++){
        	if(habitacionesReserva.get(i).getHabitacion().getHabitacionID().equals(habitacionReserva.getHabitacion().getHabitacionID())&&habitacionesReserva.get(i).getReserva().getReservaId().equals(habitacionReserva.getReserva().getReservaId())){
        	    return false;
        	}
        }
    	habitacionesReserva.add(habitacionReserva);
        return true;
    }
    public boolean desasignarHabitacion(int idReserva, int idHabitacion){
    	for(int i = 0; i<habitacionesReserva.size(); i++){
        	if(habitacionesReserva.get(i).getHabitacion().getHabitacionID().equals(new Integer(idHabitacion))&&habitacionesReserva.get(i).getReserva().getReservaId().equals(new Integer(idReserva))){
        		habitacionesReserva.remove(i);
        	    return true;
        	}
        }
        return false;
    }
}
