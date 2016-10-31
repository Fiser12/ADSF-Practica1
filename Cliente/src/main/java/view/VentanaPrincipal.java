package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.xsd.Reserva;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import controller.GestionAltaTerminales;

@SuppressWarnings("restriction")
public class VentanaPrincipal extends Application {
	private GestionAltaTerminales controller = GestionAltaTerminales.getInstance();
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>(Arrays.asList(controller.listarReservas()));
	
    @Override
    public void start(Stage primaryStage) {
    	
    	Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("VentanaPrincipal.fxml"));
            primaryStage.setTitle("Hotel");
            primaryStage.setScene(new Scene(root, 700, 450));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * M�todo que escucha el bot�n Actualizar Reserva y realiza el m�todo updateReserva cuando se pulsa
     * @param e
     */
     @FXML private void accionActualizarReserva(ActionEvent e){
     	System.out.println("HOLA");
     }
     
     /**
      * M�todo que escucha el bot�n Borrar Reserva y realiza el m�todo deleteReserva cuando se pulsa
      * @param e
      */
     @FXML private void accionBorrarReserva(ActionEvent e){
     	
     }

    public static void main(String[] args) {
        launch(args);
    }
}