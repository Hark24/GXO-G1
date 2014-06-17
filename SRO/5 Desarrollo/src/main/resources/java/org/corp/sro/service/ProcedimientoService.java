package org.corp.sro.service;

import java.util.List;

import org.corp.sro.dao.IProcedimientoDao;
import org.corp.sro.domain.Procedimiento;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ProcedimientoService implements IProcedimientoService{

	private IProcedimientoDao ProcedimientoDAO;

	public IProcedimientoDao getProcedimientoDAO() {
		return ProcedimientoDAO;
	}

	public void setProcedimientoDAO(IProcedimientoDao ProcedimientoDAO) {
		this.ProcedimientoDAO = ProcedimientoDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void addProcedimiento(Procedimiento Procedimiento) {
		getProcedimientoDAO().addProcedimiento(Procedimiento);
	}
		


	@Override
	@Transactional(readOnly = false)
	public void updateProcedimiento(Procedimiento Procedimiento) {
		getProcedimientoDAO().updateProcedimiento(Procedimiento);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteProcedimiento(Procedimiento Procedimiento) {
		getProcedimientoDAO().deleteProcedimiento(Procedimiento);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteProcedimientoLogico(Procedimiento Procedimiento) {
		Procedimiento.setEliminacion(false);
		getProcedimientoDAO().updateProcedimiento(Procedimiento);
			
		
	}

	@Override
	@Transactional(readOnly = false)
	public Procedimiento getProcedimientoById(int id) {
		return getProcedimientoDAO().getProcedimientoById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Procedimiento> getProcedimientos() {
		return getProcedimientoDAO().getProcedimientos();
	}
}
