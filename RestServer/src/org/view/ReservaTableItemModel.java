package org.view;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.group.model.Reserva;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ReservaTableItemModel extends AbstractTableModel {

	private static final long serialVersionUID = 7161436789543183207L;
	private List<Reserva> reservas;
	private String[] columnas = {"Nombre","Apellidos","Tipo de Reserva","Numero de Personas","Telefono","Fecha Inicio","Fecha Fin","Precio","Pagado"};
	ClientConfig config = new DefaultClientConfig();
	Client client = Client.create(config);
	WebResource service = client.resource(getBaseURI());
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	//private GestionAltaTerminales controller = GestionAltaTerminales.getInstance();

    public ReservaTableItemModel(List<Reserva> reservas) {

        this.reservas = new ArrayList<Reserva>(reservas);

    }
    public void addRow(Reserva reserva) {
    	reservas.add(reserva);
	}
	public void removeRow(int selectedRow) {
		reservas.remove(selectedRow);
	}
	public int getRowCount() {
        return reservas.size();
    }

    public int getColumnCount() {
        return 9;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        Reserva reserva = reservas.get(rowIndex);
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

        switch (columnIndex) {
            case 0:
                value = reserva.getNombre();
                break;
            case 1:
                value = reserva.getApellidos();
                break;
            case 2:
                value = reserva.getTipoReserva();
                break;
            case 3:
                value = reserva.getNumeroPersonas();
                break;
            case 4:
                value = reserva.getTelefono();
                break;
            case 5:
                value = df.format(reserva.getStartTime());
                break;
            case 6:
                value = df.format(reserva.getEndTime());
                break;
            case 7:
                value = reserva.getPrecio();
                break;
            case 8:
    			boolean pagado = false;
    			if(reserva.getPagado()==1)
    				pagado = true;
            	value = pagado;
                break;

        }

        return value;

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	Reserva reserva = reservas.get(rowIndex);
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        switch (columnIndex) {
        case 0:
            reserva.setNombre(aValue.toString());
            break;
        case 1:
            reserva.setApellidos(aValue.toString());
            break;
        case 2:
            reserva.setTipoReserva(aValue.toString());
            break;
        case 3:
        	reserva.setNumeroPersonas(Integer.parseInt(aValue.toString()));
        	break;
        case 4:
        	reserva.setTelefono(Integer.parseInt(aValue.toString()));
            break;
        case 5:
            try {
				reserva.setStartTime(df.parse(aValue.toString()));
			} catch (ParseException e) {
			}
            break;
        case 6:
            try {
				reserva.setEndTime(df.parse(aValue.toString()));
			} catch (ParseException e) {
			}
            break;
        case 7:
        	reserva.setPrecio(Double.parseDouble(aValue.toString()));
            break;
        case 8:
        	if(aValue.toString().equals("false")){
        		reserva.setPagado(0);
        	}else{
        		reserva.setPagado(1);
        	}
			break;
        }
        //boolean actualizado = controller.actualizarReserva(reserva);
        ClientResponse actualizado = service.path("rest").path("hotel/reserva/update").type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).put(ClientResponse.class, reserva);
        if(actualizado.getStatus() == 201)
        {
	    	reservas.clear();
	    	ArrayList<Reserva> reservasTemp = new ArrayList<Reserva>();
	    	Reserva [] reservasTemp2  = service.path("rest").path("hotel/reserva/listado").accept(MediaType.APPLICATION_XML).get(Reserva[].class);
			if(reservasTemp2 != null){
				reservasTemp = new ArrayList<Reserva>(Arrays.asList(reservasTemp2));
			}else{
				reservasTemp = new ArrayList<Reserva>();
			}
	    	reservas.addAll(reservasTemp);
		    fireTableDataChanged();
        }
    }
    public boolean isCellEditable(int row, int col)
    { return true; }

    /**
     * This will return the user at the specified row...
     * @param row
     * @return 
     */
    public Reserva getReservaAt(int row) {
        return reservas.get(row);
    }
    
    public String getColumnName(int index){

    	return columnas[index];
    	
    }
    
    private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/RestServer").build();
	}

}
