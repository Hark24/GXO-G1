package org.corp.sro.service;

import java.util.Date;
import java.util.List;

import org.corp.sro.dao.IReservaDao;
import org.corp.sro.domain.Reserva;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ReservaService implements IReservaService{

	private IReservaDao ReservaDAO;

	public IReservaDao getReservaDAO() {
		return ReservaDAO;
	}

	public void setReservaDAO(IReservaDao ReservaDAO) {
		this.ReservaDAO = ReservaDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void addReserva(Reserva Reserva) {
		Date f=new Date();
		Reserva.setFechAviso(f);
		getReservaDAO().addReserva(Reserva);
	}
		


	@Override
	@Transactional(readOnly = false)
	public void updateReserva(Reserva Reserva) {
		getReservaDAO().updateReserva(Reserva);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteReserva(Reserva Reserva) {
		getReservaDAO().deleteReserva(Reserva);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteReservaLogico(Reserva Reserva) {
		Reserva.setEliminacion(false);
		getReservaDAO().updateReserva(Reserva);
			
		
	}

	@Override
	@Transactional(readOnly = false)
	public Reserva getReservaById(int id) {
		return getReservaDAO().getReservaById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Reserva> getReservas() {
		return getReservaDAO().getReservas();
	}

	@Override
	@Transactional(readOnly = false)
	public List<Reserva> getReservasByDay(Date fecha) {
		return getReservaDAO().getReservasByDay(fecha);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Reserva> getReservasVigente() {
		return getReservaDAO().getReservasVigente();
	}
}
