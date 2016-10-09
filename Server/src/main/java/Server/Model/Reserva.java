package Server.Model;

import java.sql.Date;

/**
 * Created by Fiser on 9/10/16.
 */
public class Reserva implements java.io.Serializable {
    private String ID;
    private String nombre;
    private String apellidos;
    private String tipoReserva;
    private int numeroPersonas;
    private int telefono;
    private Date startTime;
    private Date endTime;
    private double precio;
    private int pagado;

    public Reserva() {

    }

    public Reserva(String ID, String nombre, String apellidos, String tipoReserva, int numeroPersonas, int telefono, Date startTime, Date endTime, double precio, int pagado) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tipoReserva = tipoReserva;
        this.numeroPersonas = numeroPersonas;
        this.telefono = telefono;
        this.startTime = startTime;
        this.endTime = endTime;
        this.precio = precio;
        this.pagado = pagado;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getPagado() {
        return pagado;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }
}
