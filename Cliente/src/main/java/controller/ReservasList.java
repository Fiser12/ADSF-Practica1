package controller;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import model.xsd.Reserva;

@XmlRootElement(namespace = "de.vogella.xml.jaxb.model")

public class ReservasList {
	private ArrayList<Reserva> reservaList;
	
	// XmLElementWrapper generates a wrapper element around XML representation
		@XmlElementWrapper(name = "reservasList")
		// XmlElement sets the name of the entities
		@XmlElement(name = "reserva")
		public void setReservaList(ArrayList<Reserva> reservaList) {
			this.reservaList = reservaList;
		}
		public ArrayList<Reserva> getReservaList() {
			return reservaList;
		}
}
