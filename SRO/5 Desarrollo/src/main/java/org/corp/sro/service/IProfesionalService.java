package org.corp.sro.service;

import java.util.List;

import org.corp.sro.domain.Profesional;

public interface IProfesionalService {

	public void addProfesional(Profesional Profesional);

	public void updateProfesional(Profesional Profesional);
	
	public void deleteProfesional(Profesional Profesional);
	
	public void deleteProfesionalLogico(Profesional Profesional);
	
	public Profesional getProfesionalById(int id);

	public List<Profesional> getProfesionals();
	
	public List<Profesional> getAnestesiologos();
	
	public List<Profesional> getCirujanos();
	
	public List<Profesional> getInstrumentistas();
}
