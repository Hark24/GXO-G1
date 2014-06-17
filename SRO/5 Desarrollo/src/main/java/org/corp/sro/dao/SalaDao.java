package org.corp.sro.dao;

import java.util.Date;
import java.util.List;

import org.corp.sro.domain.Sala;
import org.hibernate.SessionFactory;

public class SalaDao implements ISalaDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addSala(Sala Sala) {
		getSessionFactory().getCurrentSession().save(Sala);
	}

	@Override
	public void updateSala(Sala Sala) {
		getSessionFactory().getCurrentSession().update(Sala);
	}

	@Override
	public void deleteSala(Sala Sala) {
		getSessionFactory().getCurrentSession().delete(Sala);
	}

	@Override
	public Sala getSalaById(int id) {
		List<Sala> list=getSessionFactory().getCurrentSession()
				.createQuery("from Sala where id=? and eliminacion=1")
				.setParameter(0, id).list();
		return (Sala)list.get(0);
	}

	@Override
	public List<Sala> getSalas() {
		List<Sala> list=getSessionFactory().getCurrentSession()
				.createQuery("from Sala where eliminacion=1").list();
		return list;
	}

	@Override
	public void updateEstado(Date fecha) {			
		getSessionFactory().getCurrentSession()
		.getNamedQuery("callActualizarEstadoSalas")
		.setParameter("fecha", fecha);
	}
}
