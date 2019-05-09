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
import main.java.domain.model.StatistiquesGeneral;
import main.java.domain.model.StatistiquesUser;
import main.java.domain.service.StatistiquesServiceImpl;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class StatistiquesServiceImplTest {
	
	@Spy
	@PersistenceContext(unitName = "StatistiquesPUTest")
	EntityManager em;
	
	@InjectMocks
	private StatistiquesServiceImpl statsServiceimpl;
		
	@Test
	void ToStringTest() {
		StatistiquesGeneral stats = new StatistiquesGeneral("velo", 0, "cours", 1000, "mobilite", 150);
		String s = "Statistiques [vues de l'item velo = 0, vues de la categorie associee mobilite = 150, mot general le + courant = cours avec 1000 vues]" ;
		assertEquals(s, stats.toString());
	}	
	
	private List<StatistiquesGeneral> getStatistiques() {
		List<StatistiquesGeneral> it = new ArrayList<>();
		long numberOfCpty = Math.round((Math.random() * 10));
		for (int i = 0; i < numberOfCpty; i++) {
			it.add(createStatistiques());
		}
		return it;
	}


	private StatistiquesGeneral createStatistiques() {
		StatistiquesGeneral i = new StatistiquesGeneral();
		i.setItemId(UUID.randomUUID().toString());
		i.setnClicsItem((int)Math.random()*100);
		i.setMot(UUID.randomUUID().toString());
		i.setnClicsMot((int)Math.random()*100);
		i.setCategorie(UUID.randomUUID().toString());
		i.setnClicsCategorie((int)Math.random()*100);
		return i;
	}
	
	
}

