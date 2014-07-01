package org.corp.sro.dao;

import java.util.List;

import org.corp.sro.domain.Procedimiento;

public interface IProcedimientoDao {

	public void addProcedimiento(Procedimiento Procedimiento);
	
	public void updateProcedimiento(Procedimiento Procedimiento);
	
	public void deleteProcedimiento(Procedimiento Procedimiento);
	
	public Procedimiento getProcedimientoById(int id);

	public List<Procedimiento> getProcedimientos();
}
