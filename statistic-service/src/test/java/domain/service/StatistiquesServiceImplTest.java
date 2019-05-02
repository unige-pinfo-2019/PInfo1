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
import main.java.domain.model.Statistiques;
import main.java.domain.service.StatistiquesServiceImpl;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class StatistiquesServiceImplTest {
	
	@Spy
	@PersistenceContext(unitName = "StatstiquesPUTest")
	EntityManager em;
	
	@InjectMocks
	private StatistiquesServiceImpl statsServiceimpl;
		
	@Test
	void ToStringTest() {
		Statistiques stats1 = new Statistiques("velo","velo",1);
		stats1.setUsrId(1);
		String s = "Annonce [id = 1 userId = 0 name=velo, category=velo, state=1]";
		assertEquals(s, stats1.toString());
	}	
	
	private List<Statistiques> getStatistiques() {
		List<Statistiques> it = new ArrayList<>();
		long numberOfCpty = Math.round((Math.random() * 10));
		for (int i = 0; i < numberOfCpty; i++) {
			it.add(createStatistiques());
		}
		return it;
	}


	private Statistiques createStatistiques() {
		Statistiques i = new Statistiques();
		i.setName(UUID.randomUUID().toString());
		i.setCategory(UUID.randomUUID().toString());
		i.setState(1 + (int)(Math.random() * ((5 - 1) + 1)));
		return i;
	}
	
	
}

