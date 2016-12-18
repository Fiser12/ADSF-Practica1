package org.view;


import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.group.model.Habitacion;

public class HabitacionReservaTableItemModel extends AbstractTableModel {

	private static final long serialVersionUID = 7161436789543183207L;
	private List<Habitacion> habitaciones;
	private String[] columnas = {"Numero de Habitacion","Planta","Tipo de habitación"};
	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
    public HabitacionReservaTableItemModel(List<Habitacion> habitaciones) {

        this.habitaciones = new ArrayList<Habitacion>(habitaciones);

    }
    public void addRow(Habitacion habitacion) {
    	habitaciones.add(habitacion);
	}
	public void removeRow(int selectedRow) {
		habitaciones.remove(selectedRow);
	}
	public int getRowCount() {
        return habitaciones.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        Habitacion habitacion = habitaciones.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = habitacion.getHabitacionID();
                break;
            case 1:
                value = habitacion.getPlanta();
                break;
            case 2:
                value = habitacion.getTipoHabitacion();
                break;
        }
        return value;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return Integer.class;
        case 1:
            return Integer.class;
        case 2:
            return String.class;
        default:
        	return String.class;
        }
    }
    public boolean isCellEditable(int row, int col)
    { 
   		return false;
    }

    /**
     * This will return the user at the specified row...
     * @param row
     * @return 
     */
    public Habitacion getReservaAt(int row) {
        return habitaciones.get(row);
    }
    
    public String getColumnName(int index){

    	return columnas[index];
    	
    }  
}
