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

public class SimpleService {
    
    public String  helloService(){
        return "Hello ";
    }
   public Terminal obtenerTerminal(String idTerminal){
        return new Terminal(1, "Apple", "iPhone 7 128 Gigas", 869);
    }
    public Terminal[] obtenerTerminales(){
        Terminal [] terminales = { new Terminal(1, "Apple", "iPhone 7 128 Gigas", 869), new Terminal(1, "Apple", "iPhone 7 32 Gigas", 749), new Terminal(1, "Nexus", "Nexus 5X", 400)};

        return terminales;
    }
    public Terminal[] obtenerTerminalesPorMarca(String marca){
        Terminal [] terminales = { new Terminal(1, "Apple", "iPhone 7 128 Gigas", 869), new Terminal(1, "Apple", "iPhone 7 128 Gigas", 869)};
        return terminales;
    }
    public boolean insertarTerminal(Terminal terminal){
        return true;
    }
    public boolean actualizarTerminal(String idTerminal, Terminal terminal){
        return true;
    }
    public boolean borrarTerminal(String idTerminal){
        return true;
    }
}
