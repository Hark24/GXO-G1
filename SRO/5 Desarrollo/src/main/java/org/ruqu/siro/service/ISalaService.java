package org.ruqu.siro.service;

import java.util.Date;
import java.util.List;

import org.ruqu.siro.domain.Sala;

public interface ISalaService {

	public void addSala(Sala Sala);

	public void updateSala(Sala Sala);
	
	public void updateEstado();
	
	public void deleteSala(Sala Sala);
	
	public void deleteSalaLogico(Sala Sala);
	
	public Sala getSalaById(int id);

	public List<Sala> getSalas();
}
