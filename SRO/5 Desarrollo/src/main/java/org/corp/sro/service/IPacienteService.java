package org.corp.sro.service;

import java.util.List;

import org.corp.sro.domain.Paciente;

public interface IPacienteService {

	public void addPaciente(Paciente Paciente);

	public void updatePaciente(Paciente Paciente);
	
	public void deletePaciente(Paciente Paciente);
	
	public void deletePacienteLogico(Paciente Paciente);
	
	public Paciente getPacienteById(int id);

	public List<Paciente> getPacientes();
}
