package test.java.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import eu.drus.jpa.unit.api.JpaUnit;
import main.java.domain.model.StatisticGeneral;
import main.java.domain.model.StatisticGeneral.Categorie;
import main.java.domain.model.StatisticUser;
import main.java.domain.service.StatisticServiceImpl;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class StatisticServiceImplTest {
	
	@Spy
	@PersistenceContext(unitName = "StatisticPUTest")
	EntityManager em;
	
	@InjectMocks
	private StatisticServiceImpl statsServiceImpl;
		
	@Test
	void ToStringTest() {
		StatisticGeneral stats = new StatisticGeneral("i123", 0, "cours", 1000, Categorie.Mobilite, 1500);
		StatisticUser stats2 = new StatisticUser("u123", "i123", 0, "cours", 100, Categorie.Mobilite, 150);
		String s1 = "Statistiques [vues de l'item i123 = 0, vues de la catégorie associée Mobilite = 1500, mot général recherché le + courant = cours, avec 1000 vues]" ;
		String s2 = "Statistiques pour l'utilisateur u123 [vues de l'item i123 = 0, vues de la catégorie associée Mobilite = 150, mot recherché le + courant = cours, avec 100 vues]" ;
		assertEquals(s1, stats.toString());
		assertEquals(s2, stats2.toString());
	}	
	
	@Test
	private List<StatisticGeneral> getStatistiques() {
		List<StatisticGeneral> it = new ArrayList<>();
		long numberOfCpty = Math.round((Math.random() * 10));
		for (int i = 0; i < numberOfCpty; i++) {
			it.add(createGeneralStats());
		}
		return it;
	}

	@Test
	private StatisticGeneral createRandomStats() {
		StatisticGeneral i = new StatisticGeneral();
		i.setItemId(UUID.randomUUID().toString());
		i.setnClicsItem((int)Math.random()*100);
		i.setMot(UUID.randomUUID().toString());
		i.setnClicsMot((int)Math.random()*100);
		i.setCategorie(Categorie.values()[(int)Math.random()*4]);
		i.setnClicsCategorie((int)Math.random()*100);
		return i;
	}
	
	@Test
	private StatisticUser createUserStats() {
		StatisticUser stats = new StatisticUser("u123", "i123", 1, "cours", 100, Categorie.Livres, 150);
		statsServiceImpl.addUserStats(stats);
		return stats;
	}
	
	@Test
	private StatisticGeneral createGeneralStats() {
		StatisticGeneral stats = new StatisticGeneral("i123", 10, "cours", 1000, Categorie.Livres, 1500);
		statsServiceImpl.addGeneralStats(stats);
		return stats;
	}
	
}

