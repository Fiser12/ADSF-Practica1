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

package Server;

import Server.Model.Habitacion;
import Server.Model.HabitacionReserva;
import Server.Model.Reserva;

import java.util.Date;

public class SimpleService {
    
    public String  helloService(){
        return "Hello ";
    }
    public Reserva[] listadoReserva(){
        return null;
    }
    public Reserva getReserva(int id){
        return null;
    }
    public boolean updateReserva(Reserva reserva){
        return false;
    }
    public boolean createReserva(Reserva reserva){
        return false;
    }
    public boolean deleteReserva(int id){
        return false;
    }
    public Habitacion getHabitacionesLibres(Date startDate, Date endDate, String tipoReserva){
        return null;
    }
    public boolean asignarHabitacion(HabitacionReserva habitacionReserva){
        return false;
    }
    public boolean desasignarHabitacion(int idReserva, int idHabitacion){
        return false;
    }
}
