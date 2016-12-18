package org.group.model;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
/**
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table(name = "habitacion")**/

@XmlRootElement
public class Habitacion implements java.io.Serializable {

	private Integer habitacionID;
	private int planta;
	private String tipoHabitacion;
	private Set<HabitacionReserva> habitacionReservas = new HashSet<HabitacionReserva>(0);

	public Habitacion() {
	}

	public Habitacion(Integer ID, int planta, String tipoHabitacion) {
		this.habitacionID = ID;
		this.planta = planta;
		this.tipoHabitacion = tipoHabitacion;
	}

//	@Id
//	@GeneratedValue(strategy = IDENTITY)
//	@Column(name = "HABITACION_ID", unique = true, nullable = false)
	public Integer getHabitacionID() {
		return this.habitacionID;
	}

	public void setHabitacionID(Integer habitacionID) {
		this.habitacionID = habitacionID;
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

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.habitacion")
	public Set<HabitacionReserva> getHabitacionReservas() {
		return this.habitacionReservas;
	}
	public void setHabitacionReservas(Set<HabitacionReserva> habitacionReservas) {
		this.habitacionReservas = habitacionReservas;
	}

}