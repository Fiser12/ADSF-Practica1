package org.view;

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
import org.group.model.Reserva;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import java.io.File;
import java.net.URI;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class VentanaInicial {
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	private DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

	private JFrame frame;
	private JTable table;
	private ReservaTableItemModel table_model;
	ClientConfig config = new DefaultClientConfig();
	Client client = Client.create(config);
	WebResource service = client.resource(getBaseURI());

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
	public VentanaInicial(){
		try{
			//Reserva [] reservasTemp  = controller.listarReservas();
			Reserva [] reservasTemp = service.path("rest/hotel/reserva/listado").accept(MediaType.APPLICATION_XML).get(Reserva[].class);
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
					reserva.setStartTime(new Date());
					reserva.setEndTime(df.parse("1900-10-21"));
				} catch (ParseException e1) {
			}
				ClientResponse creado = service.path("rest").path("hotel/reserva/create").type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).post(ClientResponse.class, reserva);
				if(creado.getStatus()==201){
					updateTable(model);
				}
			}
		});
		JButton btnBorrarReserva = new JButton("Borrar Reserva");
		btnBorrarReserva.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					ReservaTableItemModel model = (ReservaTableItemModel) table.getModel();
					ClientResponse borrado = service.path("rest").path("hotel/reserva/delete/"+model.getReservaAt(table.getSelectedRow()).getReservaId().intValue()).type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
					if(borrado.getStatus()==201){
						updateTable(model);
					}
				}
			}
		});
		panel.add(btnBorrarReserva);
	}

	static Schema getSchema(String schemaResourceName) throws SAXException {
		SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
		try {
			return sf.newSchema(new File(schemaResourceName));
		} catch (SAXException se) {
			// this can only happen if there's a deployment error and the resource is missing.
			throw se;
		}
	}
	private void updateTable(ReservaTableItemModel model){
		model.getReservas().clear();
		ArrayList<Reserva> reservasTemp = new ArrayList<Reserva>();
		Reserva [] reservasTemp2  = service.path("rest").path("hotel/reserva/listado").accept(MediaType.APPLICATION_XML).get(Reserva[].class);
		if(reservasTemp2 != null){
			reservasTemp = new ArrayList<Reserva>(Arrays.asList(reservasTemp2));
		}else{
			reservasTemp = new ArrayList<Reserva>();
		}
		model.getReservas().addAll(reservasTemp);
		model.fireTableDataChanged();

	}
	 private static URI getBaseURI() {
			return UriBuilder.fromUri(
					"http://localhost:8080/RestServer").build();
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
