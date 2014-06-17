package org.corp.sro.dao;

import java.util.List;

import org.corp.sro.domain.Paciente;
import org.hibernate.SessionFactory;

public class PacienteDao implements IPacienteDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addPaciente(Paciente Paciente) {
		getSessionFactory().getCurrentSession().save(Paciente);
	}

	@Override
	public void updatePaciente(Paciente Paciente) {
		getSessionFactory().getCurrentSession().update(Paciente);
	}

	@Override
	public void deletePaciente(Paciente Paciente) {
		getSessionFactory().getCurrentSession().delete(Paciente);
	}

	@Override
	public Paciente getPacienteById(int id) {
		List<Paciente> list=getSessionFactory().getCurrentSession()
				.createQuery("from Paciente where id=? and eliminacion=1")
				.setParameter(0, id).list();
		return (Paciente)list.get(0);
	}

	@Override
	public List<Paciente> getPacientes() {
		List<Paciente> list=getSessionFactory().getCurrentSession()
				.createQuery("from Paciente where eliminacion=1").list();
		return list;
	}
}
