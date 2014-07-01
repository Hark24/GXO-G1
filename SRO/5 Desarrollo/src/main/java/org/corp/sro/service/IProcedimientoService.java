package org.corp.sro.service;

import java.util.List;

import org.corp.sro.domain.Procedimiento;

public interface IProcedimientoService {

	public void addProcedimiento(Procedimiento Procedimiento);

	public void updateProcedimiento(Procedimiento Procedimiento);
	
	public void deleteProcedimiento(Procedimiento Procedimiento);
	
	public void deleteProcedimientoLogico(Procedimiento Procedimiento);
	
	public Procedimiento getProcedimientoById(int id);

	public List<Procedimiento> getProcedimientos();
}
