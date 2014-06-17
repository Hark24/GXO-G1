package org.corp.sro.service;

import java.util.Date;
import java.util.List;

import org.corp.sro.dao.ISalaDao;
import org.corp.sro.domain.Sala;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class SalaService implements ISalaService{

	private ISalaDao SalaDAO;

	public ISalaDao getSalaDAO() {
		return SalaDAO;
	}

	public void setSalaDAO(ISalaDao SalaDAO) {
		this.SalaDAO = SalaDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void addSala(Sala Sala) {
		Sala.setCodSala(Sala.getDescripcion());
		getSalaDAO().addSala(Sala);
		updateSala(Sala);
	}
		


	@Override
	@Transactional(readOnly = false)
	public void updateSala(Sala Sala) {
		switch(Sala.getDescripcion().charAt(0)){
			case 'Q': Sala.setCodSala("Q"+Sala.getIdSala());
				break;
			case 'E': Sala.setCodSala("EQ"+Sala.getIdSala());
				break;
		}
		getSalaDAO().updateSala(Sala);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteSala(Sala Sala) {
		getSalaDAO().deleteSala(Sala);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteSalaLogico(Sala Sala) {
		Sala.setEliminacion(false);
		getSalaDAO().updateSala(Sala);
			
		
	}

	@Override
	@Transactional(readOnly = false)
	public Sala getSalaById(int id) {
		return getSalaDAO().getSalaById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Sala> getSalas() {
		return getSalaDAO().getSalas();
	}

	@Override
	@Transactional(readOnly = false)
	public void updateEstado() {
		System.out.println(new Date());
		getSalaDAO().updateEstado(new Date());
		System.out.println(new Date());
	}
}
