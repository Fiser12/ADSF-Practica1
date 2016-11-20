package org.group.model;

import java.util.Date;
/**
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "habitacion_reserva")
@AssociationOverrides({
		@AssociationOverride(name = "pk.habitacion", joinColumns = @JoinColumn(name = "HABITACION_ID")),
		@AssociationOverride(name = "pk.reserva", joinColumns = @JoinColumn(name = "RESERVA_ID")) })**/
public class HabitacionReserva implements java.io.Serializable {

	private HabitacionReservaId pk = new HabitacionReservaId();
	private Date startDate;
	private Date endDate;
	private String createdBy;

	public HabitacionReserva() {
	}

//	@EmbeddedId
	public HabitacionReservaId getPk() {
		return pk;
	}

	public void setPk(HabitacionReservaId pk) {
		this.pk = pk;
	}

//	@Transient
	public Habitacion getHabitacion() {
		return getPk().getHabitacion();
	}

	public void setHabitacion(Habitacion habitacion) {
		getPk().setHabitacion(habitacion);
	}

//	@Transient
	public Reserva getReserva() {
		return getPk().getReserva();
	}

	public void setReserva(Reserva reserva) {
		getPk().setReserva(reserva);
	}

//	@Temporal(TemporalType.DATE)
//	@Column(name = "CREATED_DATE", nullable = false, length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

//	@Temporal(TemporalType.DATE)
//	@Column(name = "END_DATE", nullable = false, length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

//	@Column(name = "CREATED_BY", nullable = false, length = 10)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		HabitacionReserva that = (HabitacionReserva) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}