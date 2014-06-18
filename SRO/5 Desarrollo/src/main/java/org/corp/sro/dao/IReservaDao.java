package org.corp.sro.dao;

import java.util.Date;
import java.util.List;

import org.corp.sro.domain.Reserva;

public interface IReservaDao {

	public void addReserva(Reserva Reserva);
	
	public void updateReserva(Reserva Reserva);
	
	public void deleteReserva(Reserva Reserva);
	
	public Reserva getReservaById(int id);

	public List<Reserva> getReservas();
	
	public List<Reserva> getReservasByDay(Date fecha);
	
	public List<Reserva> getReservasVigente();
}
