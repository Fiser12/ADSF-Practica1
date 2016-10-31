package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.swing.JFrame;

import controller.GestionAltaTerminales;
import model.xsd.Reserva;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;

public class VentanaInicial {
	private GestionAltaTerminales controller = GestionAltaTerminales.getInstance();
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

	private JFrame frame;
	private JTable table;
	private ReservaTableItemModel table_model;

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
			Reserva [] reservasTemp  = controller.listarReservas();
			if(reservasTemp != null){
				reservas = new ArrayList<Reserva>(Arrays.asList(reservasTemp));
			}else{
				reservas = new ArrayList<Reserva>();
			}
		}catch(Exception e){
			Reserva reserva = new Reserva();
			reserva.setReservaId(0);
			reserva.setNombre("Benito");
			reserva.setApellidos("Alonso");
			reserva.setNumeroPersonas(3);
			reserva.setTipoReserva("Doble");
			reserva.setTelefono(944323532);
			reserva.setStartTime(new Date());
			reserva.setEndTime(new Date());
			reserva.setPrecio(53.32);
			reserva.setPagado(0);
			reservas.add(reserva);
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table_model = new ReservaTableItemModel(reservas);
        table = new JTable(table_model);

        table.getColumnModel().getColumn(5).setCellRenderer(new DateRenderer());
        table.getColumnModel().getColumn(6).setCellRenderer(new DateRenderer());
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		frame.getContentPane().add(table, BorderLayout.CENTER);
		frame.getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnAnadirReserva = new JButton("Añadir Reserva");
		panel.add(btnAnadirReserva);
		btnAnadirReserva.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
			    ReservaTableItemModel model = (ReservaTableItemModel) table.getModel();
			    Reserva reserva = new Reserva();
			    reserva.setReservaId(new Random().nextInt(Integer.MAX_VALUE));
			    try {
					reserva.setStartTime(df.parse("1900-10-21"));
				    reserva.setEndTime(df.parse("1900-10-21"));
				} catch (ParseException e1) {
				}
			    boolean creado = controller.crearReserva(reserva);
			    if(creado){
			    	model.getReservas().clear();
			    	ArrayList<Reserva> reservasTemp = new ArrayList<Reserva>();
			    	Reserva [] reservasTemp2  = controller.listarReservas();
					if(reservasTemp2 != null){
						reservasTemp = new ArrayList<Reserva>(Arrays.asList(reservasTemp2));
					}else{
						reservasTemp = new ArrayList<Reserva>();
					}
			    	model.getReservas().addAll(reservasTemp);
			    	System.out.println(reservas.size());
			    	model.fireTableDataChanged();
			    }
			}
		});
		JButton btnBorrarReserva = new JButton("Borrar Reserva");
		btnBorrarReserva.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
		        if (table.getSelectedRow() != -1) {
				    ReservaTableItemModel model = (ReservaTableItemModel) table.getModel();
				    boolean borrado = controller.borrarReserva(model.getReservaAt(table.getSelectedRow()));
				    if(borrado){
				    	model.getReservas().clear();
				    	ArrayList<Reserva> reservasTemp = new ArrayList<Reserva>();
				    	Reserva [] reservasTemp2  = controller.listarReservas();
						if(reservasTemp2 != null){
							reservasTemp = new ArrayList<Reserva>(Arrays.asList(reservasTemp2));
						}else{
							reservasTemp = new ArrayList<Reserva>();
						}
						model.getReservas().addAll(reservasTemp);
					    model.fireTableDataChanged();
				    }
		        }
			}
		});

		panel.add(btnBorrarReserva);
	}
	class DateRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;
		private Date dateValue;
		private String valueToString = "";

		@Override
		public void setValue(Object value) {
		    if ((value != null)) {
		        String stringFormat = value.toString();
		        try {
		            dateValue = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(stringFormat);
			        valueToString = df.format(dateValue);
			        value = valueToString;
				    super.setValue(value);
		        } catch (Exception e) {
					super.setValue("");
		        }
		    }
		}
	}
}
