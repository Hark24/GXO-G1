package org.corp.sro.dao;

import java.util.Date;
import java.util.List;

import org.corp.sro.domain.Sala;

public interface ISalaDao {

	public void addSala(Sala Sala);
	
	public void updateSala(Sala Sala);
	
	public void updateEstado(Date fecha);
	
	public void deleteSala(Sala Sala);
	
	public Sala getSalaById(int id);

	public List<Sala> getSalas();
}
