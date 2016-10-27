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
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
@SuppressWarnings("unchecked")
public class SimpleService {
    
    public String  helloService(){
        return "Hello ";
    }
    public Reserva[] listadoReserva(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Reserva> list = session.createCriteria(Reserva.class).list();
        Reserva[] array = new Reserva[list.size()];
        return list.toArray(array);
    }
    public Reserva getReserva(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Reserva> list = session.createCriteria(Reserva.class).list();
        for (Reserva reserva: list) {
        	if(reserva.getReservaId().equals(new Integer(id))){
        		return reserva;
        	}
        }
        return null;
    }
    public boolean updateReserva(Reserva reserva){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
	        tx = session.beginTransaction();
	        List<Reserva> list = session.createCriteria(Reserva.class).list();
	        for (Reserva reservaTemp: list) {
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
	                session.update(reservaTemp); 
	                tx.commit();
	                return true;
	        	}
	        }
	    }catch (HibernateException e) {
	        return false;
	    }finally {
	        session.close(); 
	    }
        return false;
    }
    public boolean createReserva(Reserva reserva){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
	        session.beginTransaction();
	        session.save(reserva);
	        session.getTransaction().commit();
	    }catch (HibernateException e) {
	        return false;
	    }finally {
	        session.close(); 
	    }
        return true;
    }
    public boolean deleteReserva(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Reserva reserva = (Reserva)session.load(Reserva.class,id);
        session.delete(reserva);
        session.flush() ;
        return true;
    }
	public List<Habitacion> getHabitacionesLibres(Date startDate, Date endDate, String tipoReserva){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Habitacion> list = session.createCriteria(Habitacion.class).list();
        List<Habitacion> devolver = new LinkedList<Habitacion>();
        for (Habitacion habitacion: list) {
        	if(habitacion.getTipoHabitacion().equals(tipoReserva))
        	{
        		boolean libre = true;
                List<HabitacionReserva> list2 = session.createCriteria(HabitacionReserva.class).list();
                for(HabitacionReserva habitacionReserva: list2)
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
    public boolean asignarHabitacion(HabitacionReserva habitacionReserva){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
	        session.beginTransaction();
	        session.save(habitacionReserva);
	        session.getTransaction().commit();
	    }catch (HibernateException e) {
	        return false;
	    }finally {
	        session.close(); 
	    }
        return true;
    }
    public boolean desasignarHabitacion(int idReserva, int idHabitacion){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<HabitacionReserva> list = session.createCriteria(HabitacionReserva.class).list();
        for (HabitacionReserva habitacionReserva: list) {
        	if(habitacionReserva.getHabitacion().getHabitacionID().equals(new Integer(idHabitacion))&&habitacionReserva.getReserva().getReservaId().equals(new Integer(idReserva))){
        	    session.delete(habitacionReserva);
        	    session.flush();
        		return true;
        	}
        }
        return false;
    }
}
