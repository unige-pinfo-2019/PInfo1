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
import main.java.domain.model.StatisticItem;
import main.java.domain.service.StatisticServiceImpl;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class StatisticServiceImplTest {
	
	@Spy
	@PersistenceContext(unitName = "StatisticPUTest")
	EntityManager em;
	
	@InjectMocks
	private StatisticServiceImpl statsServiceimpl;
		
	@Test
	void ToStringTest() {
		StatisticItem stats = new StatisticItem("vélo", (long)150);
		String s = "Statistiques [vues de l'item vélo = 150]" ;
		assertEquals(s, stats.toString());
	}	
	
	private List<StatisticItem> getStatistiques() {
		List<StatisticItem> it = new ArrayList<>();
		long numberOfCpty = Math.round((Math.random() * 10));
		for (int i = 0; i < numberOfCpty; i++) {
			it.add(createStatistiques());
		}
		return it;
	}


	private StatisticItem createStatistiques() {
		StatisticItem i = new StatisticItem();
		i.setItemId(UUID.randomUUID().toString());
		i.setnClicsItem((int)Math.random()*100);
		return i;
	}
	
	
}

