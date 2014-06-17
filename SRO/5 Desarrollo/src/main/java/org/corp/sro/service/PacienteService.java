package org.corp.sro.service;

import java.util.Date;
import java.util.List;

import org.corp.sro.dao.IPacienteDao;
import org.corp.sro.domain.Paciente;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class PacienteService implements IPacienteService{

	private IPacienteDao PacienteDAO;

	public IPacienteDao getPacienteDAO() {
		return PacienteDAO;
	}

	public void setPacienteDAO(IPacienteDao PacienteDAO) {
		this.PacienteDAO = PacienteDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void addPaciente(Paciente Paciente) {
		Date f=new Date();
		int edad=f.getYear()-Paciente.getFechNac().getYear();
		Paciente.setEdad(edad);
		getPacienteDAO().addPaciente(Paciente);
	}
		


	@Override
	@Transactional(readOnly = false)
	public void updatePaciente(Paciente Paciente) {
		Date f=new Date();
		int edad=f.getYear()-Paciente.getFechNac().getYear();
		Paciente.setEdad(edad);
		getPacienteDAO().updatePaciente(Paciente);
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePaciente(Paciente Paciente) {
		getPacienteDAO().deletePaciente(Paciente);
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePacienteLogico(Paciente Paciente) {
		Paciente.setEliminacion(false);
		getPacienteDAO().updatePaciente(Paciente);
			
		
	}

	@Override
	@Transactional(readOnly = false)
	public Paciente getPacienteById(int id) {
		return getPacienteDAO().getPacienteById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Paciente> getPacientes() {
		return getPacienteDAO().getPacientes();
	}
}
