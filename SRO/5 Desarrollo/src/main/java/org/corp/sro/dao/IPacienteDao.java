package org.corp.sro.dao;

import java.util.List;

import org.corp.sro.domain.Paciente;

public interface IPacienteDao {
	
	public void addPaciente(Paciente Paciente);
	
	public void updatePaciente(Paciente Paciente);
	
	public void deletePaciente(Paciente Paciente);
	
	public Paciente getPacienteById(int id);

	public List<Paciente> getPacientes();
}
