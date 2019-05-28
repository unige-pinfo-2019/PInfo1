package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.StatisticItem;
import domain.model.StatisticUser;
import eu.drus.jpa.unit.api.JpaUnit;
import api.StatisticRestService;
import domain.model.Categorie;

@Transactional
@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)

public class StatisticServiceImplTest {

	@Spy
	@PersistenceContext(unitName = "StatisticPUTest")
	EntityManager em;
	
	@InjectMocks
	private StatisticServiceImpls statsServiceImpl;
	
	private static int usersSize, itemsSize ;
	
	
	@BeforeEach
	void initStats() {
		em.clear();
		StatisticUser stats1 = new StatisticUser("u123", 1, 2, 3, 4, 5) ;
		StatisticUser stats2 = new StatisticUser("u124", 3, 2, 0, 1, 1) ;
		StatisticUser stats3 = new StatisticUser("u125", 3, 2, 1, 3, 1) ;
		StatisticUser stats4 = new StatisticUser("u126", 2, 1, 3, 2, 0) ;
		StatisticUser stats5 = new StatisticUser("u127", 2, 1, 0, 2, 1) ;
		StatisticUser stats6 = new StatisticUser("u128", 3, 3, 1, 0, 2) ;
		em.merge(stats1);
		em.merge(stats2);
		em.merge(stats3);
		em.merge(stats4);
		em.merge(stats5);
		em.merge(stats6);
		usersSize = 17;
		StatisticItem istats1 = new StatisticItem("i123", 10, Categorie.ELECTRONIQUE);
		StatisticItem istats2 = new StatisticItem("i124", 20, Categorie.ELECTRONIQUE);
		StatisticItem istats3 = new StatisticItem("i125", 15, Categorie.LIVRES);
		StatisticItem istats4 = new StatisticItem("i126", 10, Categorie.MOBILIER);
		StatisticItem istats5 = new StatisticItem("i127", 12, Categorie.ELECTRONIQUE);
		StatisticItem istats6 = new StatisticItem("i128", 18, Categorie.LIVRES);
		StatisticItem istats7 = new StatisticItem("i129", 9, Categorie.NOTES);
		em.merge(istats1);
		em.merge(istats2);
		em.merge(istats3);
		em.merge(istats4);
		em.merge(istats5);
		em.merge(istats6);
		em.merge(istats7);
		itemsSize = 17;
	}
	
	
	
	
	@Test
	void addUserTest() {
		int usersSize = statsServiceImpl.getAllUser().size();
		StatisticUser stats = new StatisticUser("u129", 2, 1, 3, 0, 3) ;
		statsServiceImpl.addUserStats(stats);
		assertEquals(usersSize+1, statsServiceImpl.getAllUser().size());
	}
	
	@Test
	void addItemTest() {
		int size = statsServiceImpl.getAllItem().size();
		StatisticItem stats = new StatisticItem("i130", 25, Categorie.MOBILIER) ;
		statsServiceImpl.addItemStats(stats);
		assertEquals(size+1, statsServiceImpl.getAllItem().size());
	}
	
	@Test
	void getUserTest() {
		StatisticUser stats = statsServiceImpl.getUserStats("u125");
		String s = "Statistiques pour l'utilisateur u125 [vues de la catégorie Livres = 3, vues de la catégorie Mobilite = 2, vues de la catégorie Electronique = 1, vues de la catégorie Notes = 3, vues de la catégorie Mobilier = 1]" ;
		assertEquals(s, stats.toString());
	}
	
	@Test
	void getItemTest() {
		StatisticItem stats = statsServiceImpl.getItemStats("i127");
		String s = "Statistiques [vues de l'item i127 = 12, catégorie correspondante = ELECTRONIQUE]" ;
		assertEquals(s, stats.toString());
	}
	
	@Test
	void removeUserTest() {
		int usersSize = statsServiceImpl.getAllUser().size();
		statsServiceImpl.removeUserStats("u124");
		assertEquals(usersSize-1, statsServiceImpl.getAllUser().size());
	}
	
	@Test
	void removeItemTest() {
		int size = statsServiceImpl.getAllItem().size();
		statsServiceImpl.removeItemStats("i127");
		assertEquals(size - 1, statsServiceImpl.getAllItem().size());
	}
	
	@Test
	void updateUserTest() {
		StatisticUser stats = statsServiceImpl.getUserStats("u125");
		statsServiceImpl.setUserStats("u125", Categorie.ELECTRONIQUE, 5);
		stats.setnClicsElectronique(5);
		assertEquals(stats.getnClicsElectronique(), 5);
	}
	
	@Test
	void updateItemTest() {
		StatisticItem stats = statsServiceImpl.getItemStats("i126");
		statsServiceImpl.setItemStats("i126", 30);
		stats.setnClicsItem(30);
		assertEquals(stats.getnClicsItem(), 30);
	}
	
	@Test
	void clickOnItemByUser() {
		StatisticUser stats1 = statsServiceImpl.getUserStats("u124");
		StatisticItem stats2 = statsServiceImpl.getItemStats("i128");
		statsServiceImpl.clickOnItemByUser("u124", "i128");
		stats1.setnClicsMobilier(6);
		stats2.setnClicsItem(11);
		assertEquals(stats1.getnClicsMobilier(), 6);
		assertEquals(stats2.getnClicsItem(), 11);
	}
	
	@Test
	void clickOnItem() {
		StatisticItem stats = new StatisticItem("i126", 150);
		statsServiceImpl.clickOnItem("i126");
		stats.setnClicsItem(151);
		assertEquals(stats.getnClicsItem(), 151);
	}
	
	@Test
	void getUserHighlightsTest() {
		TreeMap<Categorie, Long> categories = statsServiceImpl.getUserHighlights("u123", 3) ;
		assertEquals(StatisticRestService.toStreamTreeMapCategorie(categories), "MOBILIER - 5\nNOTES - 4\nELECTRONIQUE - 3\n");
	}
	/*
	@Test
	void getCategoryHighlightsTest() {
		TreeMap<Categorie, Long> categories = statsServiceImpl.getCategoryHighlights(3) ;
		assertEquals(StatisticRestService.toStreamTreeMapCategorie(categories), "MOBILIER - 22\nLIVRES - 20\nMOBILITE - 19\n");
	}
	*/
	@Test
	void getCategoryItemHighlightsTest() {
		TreeMap<String, Long> items = statsServiceImpl.getCategoryItemHighlights(Categorie.ELECTRONIQUE, 2);
		assertEquals(StatisticRestService.toStreamTreeMapItem(items), "i124 - 20\ni127 - 12\n");
	}
	
	@Test
	void getItemHighlightsTest() {
		TreeMap<String, Long> items = statsServiceImpl.getItemHighlights(3);
		assertEquals(StatisticRestService.toStreamTreeMapItem(items), "i130 - 25\ni124 - 20\ni128 - 18\n");
	}
	
	
	@Test
	void toStringTest() {
		StatisticUser stats1 = new StatisticUser("u140", 5, 1, 0, 1, 5);
		StatisticItem stats2 = new StatisticItem("i140", 150, Categorie.LIVRES);
		String s1 = "Statistiques pour l'utilisateur u140 [vues de la catégorie Livres = 5, vues de la catégorie Mobilite = 1, vues de la catégorie Electronique = 0, vues de la catégorie Notes = 1, vues de la catégorie Mobilier = 5]" ;
		String s2 = "Statistiques [vues de l'item i140 = 150, catégorie correspondante = LIVRES]" ;
		assertEquals(s1, stats1.toString());
		assertEquals(s2, stats2.toString());
	}
	
	
	private List<StatisticItem> getRandomStats(int n) {
		List<StatisticItem> it = new ArrayList<>();
		long numberOfCpty = Math.round((Math.random() * n));
		for (int i = 0; i < numberOfCpty; i++) {
			it.add(createRandom());
		}
		return it;
	}


	private StatisticItem createRandom() {
		StatisticItem i = new StatisticItem();
		i.setItemId(UUID.randomUUID().toString());
		i.setnClicsItem((int)Math.random()*100);
		return i;
	}
	
}
