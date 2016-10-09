package Server.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "reserva")
public class Reserva implements java.io.Serializable {

	private Integer reservaId;
	private String nombre;
	private String apellidos;
	private String tipoReserva;
	private int numeroPersonas;
	private int telefono;
	private Date startTime;
	private Date endTime;
	private double precio;
	private int pagado;
	private Set<HabitacionReserva> habitacionReservas = new HashSet<HabitacionReserva>(0);

	public Reserva() {

	}

	public Reserva(Integer ID, String nombre, String apellidos, String tipoReserva, int numeroPersonas, int telefono, Date startTime, Date endTime, double precio, int pagado) {
		this.reservaId = ID;
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

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RESERVA_ID", unique = true, nullable = false)
	public Integer getReservaId() {
		return this.reservaId;
	}
	public void setReservaId(Integer reservaId) {
		this.reservaId = reservaId;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.reserva", cascade=CascadeType.ALL)
	public Set<HabitacionReserva> getHabitacionReservas() {
		return this.habitacionReservas;
	}
	public void setHabitacionReservas(Set<HabitacionReserva> habitacionReservas) {
		this.habitacionReservas = habitacionReservas;
	}

}