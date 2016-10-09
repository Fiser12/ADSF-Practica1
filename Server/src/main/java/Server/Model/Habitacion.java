package Server.Model;

/**
 * Created by Fiser on 9/10/16.
 */
public class Habitacion implements java.io.Serializable {
    private String ID;
    private int planta;
    private String tipoHabitacion;

    public Habitacion() {
    }

    public Habitacion(String ID, int planta, String tipoHabitacion) {
        this.ID = ID;
        this.planta = planta;
        this.tipoHabitacion = tipoHabitacion;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }
}
