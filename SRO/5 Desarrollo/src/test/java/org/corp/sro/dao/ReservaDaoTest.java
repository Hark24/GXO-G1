package org.corp.sro.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.corp.sro.domain.Paciente;
import org.corp.sro.domain.Procedimiento;
import org.corp.sro.domain.Profesional;
import org.corp.sro.domain.Reserva;
import org.corp.sro.domain.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class ReservaDaoTest extends  AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private IReservaDao ReservaDAO;
	@Autowired
	private IProfesionalDao ProfesionalDAO;
	@Autowired
	private IPacienteDao PacienteDAO;
	@Autowired
	private IProcedimientoDao ProcedimientoDAO;
	@Autowired
	private ISalaDao SalaDAO;
	
	@Autowired
	private Sala sala;
	@Autowired
	private Profesional instrumentista;
	@Autowired
	private Paciente paciente;
	@Autowired
	private Procedimiento procedimiento;
	@Autowired
	private Set<Profesional> profesionals;
	
	private Reserva r1;
	
	@Before
	public void init(){
		java.util.Date fecha = new Date();
		instrumentista.setColegio("123645E");
		instrumentista.setNombres("Maximo");
		instrumentista.setApellidos("Ramos Alegre");
		instrumentista.setTipo("Cirujano");
		instrumentista.setEliminacion(true);
		
		procedimiento.setCie("Y85-Y98-V90.1");
		procedimiento.setNombre("Cesarea");
		procedimiento.setEliminacion(true);
		
		sala.setEliminacion(true);
		
		paciente.setHistClinica("ABc123-Torres");
		paciente.setNombres("Dennis");
		paciente.setApellidos("Torres Wong");
		paciente.setEdad(23);
		paciente.setFechNac(fecha);
		paciente.setEliminacion(true);
		
		PacienteDAO.addPaciente(paciente);
		SalaDAO.addSala(sala);
		ProcedimientoDAO.addProcedimiento(procedimiento);
		ProfesionalDAO.addProfesional(instrumentista);
		
		profesionals.add(instrumentista);

		r1=new Reserva(sala,instrumentista,instrumentista,instrumentista,procedimiento,paciente,
				fecha,fecha,fecha,fecha,true,true);
	}
	
	@Test
	public void verificarInsercion(){
		int size=ReservaDAO.getReservas().size();
		ReservaDAO.addReserva(r1);
		assertEquals(size+1,ReservaDAO.getReservas().size());
	}
}
