package main.java.domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import main.java.domain.model.Statistiques;


@ApplicationScoped
@Transactional
@Default

public class StatistiquesServiceImpl implements StatistiquesService {

	@PersistenceContext(unitName="StatistiquesPU")
	private EntityManager em;
	
	@Override
	public Statistiques getUserStats(String name) {
		name = name.replace(" ","%");
		TypedQuery<Statistiques> stats = em.createQuery(	"SELECT s FROM Statistiques s WHERE s.usr_id = (SELECT usr_id FROM User a WHERE a.name LIKE '%"+name+")", Statistiques.class);
		return (Statistiques) stats;
	}
	
	@Override
	public List<Statistiques> getAll() { 
		return em.createQuery("FROM Item", Statistiques.class).getResultList();
	}

	@Override
	public boolean addStatistiques(Statistiques stats) {
		em.persist(stats);
		return true;
	}

	@Override
	public Statistiques getItemStats(String name) {
		name = name.replace(" ","%");
		TypedQuery<Statistiques> stats = em.createQuery(	"SELECT s FROM Statistiques s WHERE s.item_id = (SELECT item_id FROM Item a WHERE a.name LIKE '%"+name+")", Statistiques.class);
		return (Statistiques) stats;
	}

	public void incMotGeneral(String mot) {
		TypedQuery<Statistiques> stats = em.createQuery(	"UPDATE (SELECT s FROM Statistiques WHERE s.Mot_General=" + mot + ") SET Inc_General=s.Inc_General+1", Statistiques.class);
	}
	
	

}
