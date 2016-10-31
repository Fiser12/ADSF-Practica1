package view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

import controller.GestionAltaTerminales;
import model.xsd.Reserva;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;

public class VentanaInicial {
	private GestionAltaTerminales controller = GestionAltaTerminales.getInstance();
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	
	private JFrame frame;
	private JTable table;
	private DefaultTableModel table_model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicial window = new VentanaInicial();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaInicial() {
		try{
			reservas = new ArrayList<Reserva>(Arrays.asList(controller.listarReservas()));
		}catch(Exception e){
			
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String column_names[]= {"Nombre", "Apellidos","Tipo de reserva","Número personas","Telefono", "Fecha inicio", "Fecha fin", "Precio", "Pagado"};
		table_model=new DefaultTableModel(column_names,0);
		for (Reserva reserva: reservas) {
			table_model.addRow(new Object[]{reserva.getNombre(), reserva.getApellidos(), reserva.getTipoReserva(), reserva.getNumeroPersonas(), reserva.getTelefono(), reserva.getStartTime().toString(), reserva.getEndTime().toString(), reserva.getPrecio(), reserva.getPagado()});
		}
		table = new JTable(table_model);

		frame.getContentPane().add(table, BorderLayout.CENTER);
		frame.getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);


	}

}
