package org.corp.sro.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.corp.sro.domain.Reserva;

public class ReservaDao implements IReservaDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addReserva(Reserva Reserva) {
		getSessionFactory().getCurrentSession().save(Reserva);
	}

	@Override
	public void updateReserva(Reserva Reserva) {
		getSessionFactory().getCurrentSession().update(Reserva);
	}

	@Override
	public void deleteReserva(Reserva Reserva) {
		getSessionFactory().getCurrentSession().delete(Reserva);
	}

	@Override
	public Reserva getReservaById(int id) {
		List<Reserva> list=getSessionFactory().getCurrentSession()
				.createQuery("from Reserva where id=? and eliminacion=1")
				.setParameter(0, id).list();
		Hibernate.initialize(list.get(0).getPaciente());
		Hibernate.initialize(list.get(0).getProcedimiento());
		Hibernate.initialize(list.get(0).getSala());
		Hibernate.initialize(list.get(0).getReservas());
		Hibernate.initialize(list.get(0).getProfesionalByProfesionalIdProfesional());
		Hibernate.initialize(list.get(0).getProfesionalByProfesionalIdProfesional1());
		Hibernate.initialize(list.get(0).getProfesionalByProfesionalIdProfesional2());
		
		return (Reserva)list.get(0);
	}

	@Override
	public List<Reserva> getReservas() {
		List<Reserva> list=getSessionFactory().getCurrentSession()
				.createQuery("from Reserva where eliminacion=1 order by Sala_idSala").list();
		
		for(Reserva l:list){
			Hibernate.initialize(l.getPaciente());
			Hibernate.initialize(l.getProcedimiento());
			Hibernate.initialize(l.getSala());
			Hibernate.initialize(l.getReservas());
			Hibernate.initialize(l.getProfesionalByProfesionalIdProfesional());
			Hibernate.initialize(l.getProfesionalByProfesionalIdProfesional1());
			Hibernate.initialize(l.getProfesionalByProfesionalIdProfesional2());
		}
		return list;
	}

	@Override
	public List<Reserva> getReservasByDay(Date fecha) {
		List<Reserva> list=getSessionFactory().getCurrentSession()
				.createQuery("from Reserva where DATE(FechaIni)=? and eliminacion=1 order by Sala_idSala")
				.setParameter(0, fecha).list();
		
		for(Reserva l:list){
			Hibernate.initialize(l.getPaciente());
			Hibernate.initialize(l.getProcedimiento());
			Hibernate.initialize(l.getSala());
			Hibernate.initialize(l.getReservas());
			Hibernate.initialize(l.getProfesionalByProfesionalIdProfesional());
			Hibernate.initialize(l.getProfesionalByProfesionalIdProfesional1());
			Hibernate.initialize(l.getProfesionalByProfesionalIdProfesional2());
		}
		return list;
	}

	@Override
	public List<Reserva> getReservasVigente() {
		List<Reserva> list=getSessionFactory().getCurrentSession()
				.createQuery("from Reserva where eliminacion=1 and vigente=1 order by Sala_idSala").list();
		
		for(Reserva l:list){
			Hibernate.initialize(l.getPaciente());
			Hibernate.initialize(l.getProcedimiento());
			Hibernate.initialize(l.getSala());
			Hibernate.initialize(l.getReservas());
			Hibernate.initialize(l.getProfesionalByProfesionalIdProfesional());
			Hibernate.initialize(l.getProfesionalByProfesionalIdProfesional1());
			Hibernate.initialize(l.getProfesionalByProfesionalIdProfesional2());
		}
		return list;
	}
}
