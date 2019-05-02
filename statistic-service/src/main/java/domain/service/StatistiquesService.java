package domain.service;

import java.util.List;
import domain.model.Statistiques;

public interface StatistiquesService {
	
	public List<Statistiques> getAll();

	public boolean addStatistiques(Statistiques stats);

	public Statistiques getUserStats(String name);
	
	public Statistiques getItemStats(String name);


}