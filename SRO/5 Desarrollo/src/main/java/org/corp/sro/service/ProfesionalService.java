package org.corp.sro.service;

import java.util.List;

import org.corp.sro.dao.IProfesionalDao;
import org.corp.sro.domain.Profesional;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ProfesionalService implements IProfesionalService{

	private IProfesionalDao ProfesionalDAO;

	public IProfesionalDao getProfesionalDAO() {
		return ProfesionalDAO;
	}

	public void setProfesionalDAO(IProfesionalDao ProfesionalDAO) {
		this.ProfesionalDAO = ProfesionalDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void addProfesional(Profesional Profesional) {
		switch(Profesional.getTipo().charAt(0)){
			case 'C': Profesional.setColegio("C"+Profesional.getColegio());
				break;
			case 'A': Profesional.setColegio("A"+Profesional.getColegio());
				break;
			case 'I': Profesional.setColegio("I"+Profesional.getColegio());
		}
		getProfesionalDAO().addProfesional(Profesional);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateProfesional(Profesional Profesional) {
		switch(Profesional.getTipo().charAt(0)){
			case 'C': Profesional.setColegio("C"+Profesional.getColegio());
				break;
			case 'A': Profesional.setColegio("A"+Profesional.getColegio());
				break;
			case 'I': Profesional.setColegio("I"+Profesional.getColegio());
		}
		getProfesionalDAO().updateProfesional(Profesional);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteProfesional(Profesional Profesional) {
		getProfesionalDAO().deleteProfesional(Profesional);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteProfesionalLogico(Profesional Profesional) {
		Profesional.setEliminacion(false);
		getProfesionalDAO().updateProfesional(Profesional);
			
		
	}

	@Override
	@Transactional(readOnly = false)
	public Profesional getProfesionalById(int id) {
		return getProfesionalDAO().getProfesionalById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Profesional> getProfesionals() {
		return getProfesionalDAO().getProfesionals();
	}

	@Override
	@Transactional(readOnly = false)
	public List<Profesional> getAnestesiologos() {
		return getProfesionalDAO().getAnestesiologos();
	}

	@Override
	@Transactional(readOnly = false)
	public List<Profesional> getCirujanos() {
		return getProfesionalDAO().getCirujanos();
	}

	@Override
	@Transactional(readOnly = false)
	public List<Profesional> getInstrumentistas() {
		return getProfesionalDAO().getInstrumentistas();
	}
}
