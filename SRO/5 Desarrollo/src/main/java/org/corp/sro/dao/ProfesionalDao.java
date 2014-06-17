package org.corp.sro.dao;

import java.util.List;

import org.corp.sro.domain.Profesional;
import org.hibernate.SessionFactory;

public class ProfesionalDao implements IProfesionalDao{
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addProfesional(Profesional Profesional) {
		getSessionFactory().getCurrentSession().save(Profesional);
	}

	@Override
	public void updateProfesional(Profesional Profesional) {
		getSessionFactory().getCurrentSession().update(Profesional);
	}

	@Override
	public void deleteProfesional(Profesional Profesional) {
		getSessionFactory().getCurrentSession().delete(Profesional);
	}

	@Override
	public Profesional getProfesionalById(int id) {
		List<Profesional> list=getSessionFactory().getCurrentSession()
				.createQuery("from Profesional where id=? and eliminacion=1")
				.setParameter(0, id).list();
		return (Profesional)list.get(0);
	}

	@Override
	public List<Profesional> getProfesionals() {
		List<Profesional> list=getSessionFactory().getCurrentSession()
				.createQuery("from Profesional where eliminacion=1").list();
		return list;
	}

	@Override
	public List<Profesional> getAnestesiologos() {
		List<Profesional> list=getSessionFactory().getCurrentSession()
				.createQuery("from Profesional where eliminacion=1 and tipo='Anestesiologo'").list();
		return list;
	}

	@Override
	public List<Profesional> getCirujanos() {
		List<Profesional> list=getSessionFactory().getCurrentSession()
				.createQuery("from Profesional where eliminacion=1 and tipo='Cirujano'").list();
		return list;
	}

	@Override
	public List<Profesional> getInstrumentistas() {
		List<Profesional> list=getSessionFactory().getCurrentSession()
				.createQuery("from Profesional where eliminacion=1 and tipo='Instrumentista'").list();
		return list;
	}
}
