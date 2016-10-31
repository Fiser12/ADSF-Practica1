package view;

import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;

import controller.GestionAltaTerminales;
import model.xsd.Reserva;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.BorderLayout;
import java.awt.Component;

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
			Reserva reserva = new Reserva();
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
		String column_names[]= {"Nombre", "Apellidos","Tipo de reserva","Número personas","Telefono", "Fecha inicio", "Fecha fin", "Precio", "Pagado"};
		table_model=new DefaultTableModel(column_names,0);
		for (Reserva reserva: reservas) {
			boolean pagado = false;
			if(reserva.getPagado()==1)
				pagado = true;
			table_model.addRow(new Object[]{reserva.getNombre(), reserva.getApellidos(), reserva.getTipoReserva(), reserva.getNumeroPersonas(), reserva.getTelefono(), "2013-01-25", "2013-01-25", reserva.getPrecio(), pagado});
		}
        table = new JTable(table_model) {

            private static final long serialVersionUID = 1L;

            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return Integer.class;
                    case 4:
                        return Integer.class;
                    case 5:
                        return String.class;
                    case 6:
                        return String.class;
                    case 7:
                        return Double.class;
                    case 8:
                        return Boolean.class;
                    default:
                    	return String.class;
                }
            }
        };

        table.getColumnModel().getColumn(5).setCellRenderer(new DateRenderer());
        table.getColumnModel().getColumn(6).setCellRenderer(new DateRenderer());

		frame.getContentPane().add(table, BorderLayout.CENTER);
		frame.getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);


	}
	class DateRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;
		private Date dateValue;
		private SimpleDateFormat sdfNewValue = new SimpleDateFormat("yyyy-mm-dd");
		private String valueToString = "";

		@Override
		public void setValue(Object value) {
		    if ((value != null)) {
		        String stringFormat = value.toString();
		        try {
		            dateValue = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(stringFormat);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        valueToString = sdfNewValue.format(dateValue);
		        value = valueToString;
		    }
		    super.setValue(value);
		}
		}

}
