
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.3  Built on : May 30, 2016 (04:09:26 BST)
 */

        
            package org.apache.ws.axis2;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://model/xsd".equals(namespaceURI) &&
                  "Habitacion".equals(typeName)){
                   
                            return  model.xsd.Habitacion.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model/xsd".equals(namespaceURI) &&
                  "HabitacionReserva".equals(typeName)){
                   
                            return  model.xsd.HabitacionReserva.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model/xsd".equals(namespaceURI) &&
                  "HabitacionReservaId".equals(typeName)){
                   
                            return  model.xsd.HabitacionReservaId.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model/xsd".equals(namespaceURI) &&
                  "Reserva".equals(typeName)){
                   
                            return  model.xsd.Reserva.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    