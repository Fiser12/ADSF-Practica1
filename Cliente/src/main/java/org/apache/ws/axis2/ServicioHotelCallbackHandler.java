
/**
 * ServicioHotelCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.3  Built on : May 30, 2016 (04:08:57 BST)
 */

    package org.apache.ws.axis2;

    /**
     *  ServicioHotelCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ServicioHotelCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ServicioHotelCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ServicioHotelCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getReserva method
            * override this method for handling normal response from getReserva operation
            */
           public void receiveResultgetReserva(
                    org.apache.ws.axis2.GetReservaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getReserva operation
           */
            public void receiveErrorgetReserva(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for deleteReserva method
            * override this method for handling normal response from deleteReserva operation
            */
           public void receiveResultdeleteReserva(
                    org.apache.ws.axis2.DeleteReservaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteReserva operation
           */
            public void receiveErrordeleteReserva(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listadoReserva method
            * override this method for handling normal response from listadoReserva operation
            */
           public void receiveResultlistadoReserva(
                    org.apache.ws.axis2.ListadoReservaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listadoReserva operation
           */
            public void receiveErrorlistadoReserva(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getHabitacionesLibres method
            * override this method for handling normal response from getHabitacionesLibres operation
            */
           public void receiveResultgetHabitacionesLibres(
                    org.apache.ws.axis2.GetHabitacionesLibresResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getHabitacionesLibres operation
           */
            public void receiveErrorgetHabitacionesLibres(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for asignarHabitacion method
            * override this method for handling normal response from asignarHabitacion operation
            */
           public void receiveResultasignarHabitacion(
                    org.apache.ws.axis2.AsignarHabitacionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from asignarHabitacion operation
           */
            public void receiveErrorasignarHabitacion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createReserva method
            * override this method for handling normal response from createReserva operation
            */
           public void receiveResultcreateReserva(
                    org.apache.ws.axis2.CreateReservaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createReserva operation
           */
            public void receiveErrorcreateReserva(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateReserva method
            * override this method for handling normal response from updateReserva operation
            */
           public void receiveResultupdateReserva(
                    org.apache.ws.axis2.UpdateReservaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateReserva operation
           */
            public void receiveErrorupdateReserva(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for desasignarHabitacion method
            * override this method for handling normal response from desasignarHabitacion operation
            */
           public void receiveResultdesasignarHabitacion(
                    org.apache.ws.axis2.DesasignarHabitacionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from desasignarHabitacion operation
           */
            public void receiveErrordesasignarHabitacion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for helloService method
            * override this method for handling normal response from helloService operation
            */
           public void receiveResulthelloService(
                    org.apache.ws.axis2.HelloServiceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from helloService operation
           */
            public void receiveErrorhelloService(java.lang.Exception e) {
            }
                


    }
    