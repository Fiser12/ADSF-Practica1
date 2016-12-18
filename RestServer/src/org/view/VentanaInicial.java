package org.view;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import javax.swing.JFrame;

import org.group.model.Habitacion;
import org.group.model.HabitacionReserva;
import org.group.model.HabitacionReservaId;
import org.group.model.Reserva;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;

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
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VentanaInicial {
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	private ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

	private DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

	private JFrame frmRestClient;
	private JTable tableReservas;
	private ReservaTableItemModel table_model;
	private HabitacionReservaTableItemModel table_model_2;
	ClientConfig config = new DefaultClientConfig();
	Client client = Client.create(config);
	WebResource service = client.resource(getBaseURI());
	private JTable tableReservaPorHabitacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicial window = new VentanaInicial();
					window.frmRestClient.setVisible(true);
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
			Reserva [] reservasTemp = service.path("rest/reserva").accept(MediaType.APPLICATION_XML).get(Reserva[].class);
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
		frmRestClient = new JFrame();
		frmRestClient.setResizable(false);
		frmRestClient.setTitle("Rest Client");
		frmRestClient.setBounds(100, 100, 700, 400);
		frmRestClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRestClient.setSize(new Dimension(1300, 600));
		table_model = new ReservaTableItemModel(reservas);
		table_model_2 = new HabitacionReservaTableItemModel(habitaciones);
		JPanel panelCentral = new JPanel();
		frmRestClient.getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		tableReservas = new JTable(table_model);
		JScrollPane scrollPane = new JScrollPane(tableReservas);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelCentral.add(scrollPane, BorderLayout.CENTER);

		tableReservas.getColumnModel().getColumn(5).setCellRenderer(new DateRenderer());
		tableReservas.getColumnModel().getColumn(6).setCellRenderer(new DateRenderer());
		tableReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableReservas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ReservaTableItemModel model = (ReservaTableItemModel) tableReservas.getModel();
				HabitacionReservaTableItemModel model2 = (HabitacionReservaTableItemModel) tableReservaPorHabitacion.getModel();
				updateTableHabitaciones(model, model2);
			}
		});
		tableReservaPorHabitacion = new JTable(table_model_2);
		JScrollPane scrollPane_1 = new JScrollPane(tableReservaPorHabitacion);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelCentral.add(scrollPane_1, BorderLayout.EAST);

		JPanel panelDerecho = new JPanel();
		frmRestClient.getContentPane().add(panelDerecho, BorderLayout.EAST);
		panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.PAGE_AXIS));

		JLabel lblReservas = new JLabel("Reservas");
		panelDerecho.add(lblReservas);

		JButton btnAnadirReserva = new JButton("Añadir Reserva");
		panelDerecho.add(btnAnadirReserva);
		btnAnadirReserva.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				ReservaTableItemModel model = (ReservaTableItemModel) tableReservas.getModel();
				Reserva reserva = new Reserva();
				reserva.setReservaId(new Random().nextInt(Integer.MAX_VALUE));
				try {
					reserva.setStartTime(new Date());
					reserva.setEndTime(df.parse("1900-10-21"));
				} catch (ParseException e1) {
				}
				ClientResponse creado = service.path("rest").path("reserva/").type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).post(ClientResponse.class, reserva);
				if(creado.getStatus()==201){
					updateTableReservas(model);
				}
			}
		});
		JButton btnBorrarReserva = new JButton("Borrar Reserva");
		panelDerecho.add(btnBorrarReserva);
		btnBorrarReserva.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if (tableReservas.getSelectedRow() != -1) {
					ReservaTableItemModel model = (ReservaTableItemModel) tableReservas.getModel();
					ClientResponse borrado = service.path("rest").path("reserva/"+model.getReservaAt(tableReservas.getSelectedRow()).getReservaId().intValue()).type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
					if(borrado.getStatus()==201){
						updateTableReservas(model);
					}
				}
			}
		});

		JLabel lblHabitacionesDeLa = new JLabel("Habitaciones de la reserva");
		panelDerecho.add(lblHabitacionesDeLa);

		JButton btnReservarHabitacin = new JButton("Reservar habitación");
		btnReservarHabitacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog reservarDialog = new JDialog(frmRestClient, "Elige entre las habitaciones disponibles", true);
				reservarDialog.setResizable(false);
				reservarDialog.setSize(500, 400);
				reservarDialog.setBounds(100, 100, 700, 400);
				Reserva reserva = ((ReservaTableItemModel) tableReservas.getModel()).getReservaAt(tableReservas.getSelectedRow());
				ArrayList<Habitacion> habitacionesDisponibles = buscarHabitacionesLibre(reserva);
				HabitacionReservaTableItemModel table_model_3 = new HabitacionReservaTableItemModel(habitacionesDisponibles);
				JTable tableHabitaciones = new JTable(table_model_3);
				JScrollPane scrollPane = new JScrollPane(tableHabitaciones);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				reservarDialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
				JButton elegir = new JButton("Elegir");
				elegir.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						HabitacionReserva habitacionReserva = new HabitacionReserva();
						habitacionReserva.setEndDate(reserva.getEndTime());
						habitacionReserva.setStartDate(reserva.getStartTime());
						HabitacionReservaId temp = new HabitacionReservaId();
						temp.setReserva(reserva);
						temp.setHabitacion(((HabitacionReservaTableItemModel) tableHabitaciones.getModel()).getReservaAt(tableHabitaciones.getSelectedRow()));
						habitacionReserva.setPk(temp);
						ClientResponse creado = service.path("rest").path("habitacionReserva/").type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).post(ClientResponse.class, habitacionReserva);
						if(creado.getStatus()==201){
							reservarDialog.dispose();
							updateTableHabitaciones((ReservaTableItemModel) tableReservas.getModel(), (HabitacionReservaTableItemModel) tableReservaPorHabitacion.getModel());
						}
						else{
					        JOptionPane.showMessageDialog(null, "Error al registrar la reserva");
						}
					}
				});
				reservarDialog.getContentPane().add(new JPanel(new FlowLayout()).add(elegir), BorderLayout.SOUTH);
				reservarDialog.setVisible(true);

			}
		});
		panelDerecho.add(btnReservarHabitacin);
		JButton btnLiberarHabitacion = new JButton("Liberar habitacion");
		btnLiberarHabitacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int idHabitacion = ((HabitacionReservaTableItemModel)tableReservaPorHabitacion.getModel()).getReservaAt(tableReservaPorHabitacion.getSelectedRow()).getHabitacionID().intValue();
				int idReserva = ((ReservaTableItemModel) tableReservas.getModel()).getReservaAt(tableReservas.getSelectedRow()).getReservaId().intValue();
				ClientResponse borrado = service.path("rest").path("habitacionReserva/"+idHabitacion+"/"+idReserva).type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
				if(borrado.getStatus()==201){
					updateTableHabitaciones((ReservaTableItemModel) tableReservas.getModel(), (HabitacionReservaTableItemModel) tableReservaPorHabitacion.getModel());
				}
				else{
			        JOptionPane.showMessageDialog(null, "Error al borrar la reserva");
				}
			}
		});
		panelDerecho.add(btnLiberarHabitacion);

	}
	private ArrayList<Habitacion> buscarHabitacionesLibre(Reserva reserva){
		ArrayList<Habitacion> devolver = new ArrayList<Habitacion>();
		Habitacion [] habitaciones = service.path("rest").path("habitacion/").accept(MediaType.APPLICATION_XML).get(Habitacion[].class);
		HabitacionReserva [] habitacionesReservas  = service.path("rest").path("habitacionReserva/").accept(MediaType.APPLICATION_XML).get(HabitacionReserva[].class);		
		for (Habitacion habitacion: habitaciones) {
			if(habitacion.getTipoHabitacion().equals(reserva.getTipoReserva()))
			{
				boolean libre = true;
				for(HabitacionReserva habitacionReserva: habitacionesReservas)
				{
					if(habitacionReserva.getHabitacion().getHabitacionID().equals(habitacion.getHabitacionID())){
						if(betweenDates(habitacionReserva.getStartDate(), habitacionReserva.getEndDate(), reserva.getStartTime())){
							libre = false;
						}
						else if(betweenDates(habitacionReserva.getStartDate(), habitacionReserva.getEndDate(), reserva.getEndTime())){
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
	private boolean betweenDates(Date min, Date max, Date test)
	{
		return min.compareTo(test) * test.compareTo(max) > 0;
	}
	static Schema getSchema(String schemaResourceName) throws SAXException {
		SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
		try {
			return sf.newSchema(new File(schemaResourceName));
		} catch (SAXException se) {
			throw se;
		}
	}
	private void updateTableReservas(ReservaTableItemModel model){
		ArrayList<Reserva> reservasTemp = new ArrayList<Reserva>();
		Reserva [] reservasTemp2  = service.path("rest").path("reserva/").accept(MediaType.APPLICATION_XML).get(Reserva[].class);
		if(reservasTemp2 != null){
			reservasTemp = new ArrayList<Reserva>(Arrays.asList(reservasTemp2));
		}else{
			reservasTemp = new ArrayList<Reserva>();
		}
		model.getReservas().clear();
		model.getReservas().addAll(reservasTemp);
		model.fireTableDataChanged();
	}
	private void updateTableHabitaciones(ReservaTableItemModel model, HabitacionReservaTableItemModel model2)
	{
		try{
			int reservaId = model.getReservaAt(tableReservas.getSelectedRow()).getReservaId().intValue();
			HabitacionReserva [] habitacionReserva  = service.path("rest").path("habitacionReserva/").accept(MediaType.APPLICATION_XML).get(HabitacionReserva[].class);
			ArrayList<Habitacion> habitacionesTemp = new ArrayList<Habitacion>();
	        for (HabitacionReserva i: habitacionReserva) {
	        	if(i.getReserva().getReservaId().intValue()==reservaId)
	        	{
	        		habitacionesTemp.add(i.getHabitacion());
	        	}
	        }
	        model2.getHabitaciones().clear();
	        model2.getHabitaciones().addAll(habitacionesTemp);
	        model2.fireTableDataChanged();
		}catch(ArrayIndexOutOfBoundsException e){
			
		}
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
