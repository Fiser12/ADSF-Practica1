package hibernate.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class HabitacionReservaId implements java.io.Serializable {

	private Habitacion habitacion;
    private Reserva reserva;

	@ManyToOne
	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	@ManyToOne
	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

		HabitacionReservaId that = (HabitacionReservaId) o;

        if (habitacion != null ? !habitacion.equals(that.habitacion) : that.habitacion != null) return false;
        if (reserva != null ? !reserva.equals(that.reserva) : that.reserva != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (habitacion != null ? habitacion.hashCode() : 0);
        result = 31 * result + (reserva != null ? reserva.hashCode() : 0);
        return result;
    }
    
}