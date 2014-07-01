package org.corp.sro.dao;

import java.util.List;

import org.corp.sro.domain.Procedimiento;
import org.hibernate.SessionFactory;

public class ProcedimientoDao implements IProcedimientoDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addProcedimiento(Procedimiento Procedimiento) {
		getSessionFactory().getCurrentSession().save(Procedimiento);
	}

	@Override
	public void updateProcedimiento(Procedimiento Procedimiento) {
		getSessionFactory().getCurrentSession().update(Procedimiento);
	}

	@Override
	public void deleteProcedimiento(Procedimiento Procedimiento) {
		getSessionFactory().getCurrentSession().delete(Procedimiento);
	}

	@Override
	public Procedimiento getProcedimientoById(int id) {
		List<Procedimiento> list=getSessionFactory().getCurrentSession()
				.createQuery("from Procedimiento where id=? and eliminacion=1")
				.setParameter(0, id).list();
		return (Procedimiento)list.get(0);
	}

	@Override
	public List<Procedimiento> getProcedimientos() {
		List<Procedimiento> list=getSessionFactory().getCurrentSession()
				.createQuery("from Procedimiento where eliminacion=1").list();
		return list;
	}
}
