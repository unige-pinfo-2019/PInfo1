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
import main.java.api.StatisticRestService;
import main.java.domain.model.Categorie;
import main.java.domain.model.StatisticItem;
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
		
	private int initUserStats() {
		em.clear();
		List<StatisticUser> listStats ;
		StatisticUser stats1 = new StatisticUser("u123", 1, 2, 3, 4, 5) ;
		StatisticUser stats2 = new StatisticUser("u124", 3, 2, 0, 1, 1) ;
		StatisticUser stats3 = new StatisticUser("u125", 3, 2, 1, 3, 1) ;
		StatisticUser stats4 = new StatisticUser("u126", 2, 1, 3, 2, 0) ;
		StatisticUser stats5 = new StatisticUser("u127", 2, 1, 0, 2, 1) ;
		StatisticUser stats6 = new StatisticUser("u128", 3, 3, 1, 0, 2) ;
		statsServiceImpl.addUserStats(stats1);
		statsServiceImpl.addUserStats(stats2);
		statsServiceImpl.addUserStats(stats3);
		statsServiceImpl.addUserStats(stats4);
		statsServiceImpl.addUserStats(stats5);
		statsServiceImpl.addUserStats(stats6);
		listStats = statsServiceImpl.getAllUser();
		return listStats.size();
	}
	
	private int initItemStats() {
		em.clear();
		List<StatisticItem> listStats ;
		StatisticItem stats1 = new StatisticItem("i123", 10);
		StatisticItem stats2 = new StatisticItem("i124", 20);
		StatisticItem stats3 = new StatisticItem("i125", 15);
		StatisticItem stats4 = new StatisticItem("i126", 10);
		StatisticItem stats5 = new StatisticItem("i127", 12);
		StatisticItem stats6 = new StatisticItem("i128", 18);
		StatisticItem stats7 = new StatisticItem("i129", 9);
		statsServiceImpl.addItemStats(stats1);
		statsServiceImpl.addItemStats(stats2);
		statsServiceImpl.addItemStats(stats3);
		statsServiceImpl.addItemStats(stats4);
		statsServiceImpl.addItemStats(stats5);
		statsServiceImpl.addItemStats(stats6);
		statsServiceImpl.addItemStats(stats7);
		listStats = statsServiceImpl.getAllItem();
		return listStats.size();
	}
	
	@Test
	void addUserTest() {
		int size = initUserStats();
		StatisticUser stats = new StatisticUser("u129", 2, 1, 3, 0, 3) ;
		statsServiceImpl.addUserStats(stats);
		assertEquals(size+1, statsServiceImpl.getAllUser());
	}
	
	@Test
	void addItemTest() {
		int size = initItemStats();
		StatisticItem stats = new StatisticItem("i130", 25) ;
		statsServiceImpl.addItemStats(stats);
		assertEquals(size+1, statsServiceImpl.getAllItem());
	}
	
	@Test
	void getAllTest() {
		int size1 = initUserStats();
		int size2 = initItemStats();
		assertEquals(size1, statsServiceImpl.getAllUser());
		assertEquals(size2, statsServiceImpl.getAllItem());
	}
	
	@Test
	void getUserTest() {
		em.clear();
		statsServiceImpl.addUserStats(new StatisticUser("u123", 1, 2, 3, 4, 5));
		StatisticUser stats = statsServiceImpl.getUserStats("u123");
		String s = "Statistiques pour l'utilisateur u123 [vues de la catégorie Livres = 1, vues de la catégorie Mobilite = 2, vues de la catégorie Electronique = 3, vues de la catégorie Notes = 4, vues de la catégorie Mobilier = 5]" ;
		assertEquals(s, stats.toString());
	}
	
	@Test
	void getItemTest() {
		em.clear();
		statsServiceImpl.addItemStats(new StatisticItem("i123", 10));
		StatisticItem stats = statsServiceImpl.getItemStats("i123");
		String s = "Statistiques [vues de l'item i123 = 10]" ;
		assertEquals(s, stats.toString());
	}
	
	@Test
	void removeUserTest() {
		int size = initUserStats();
		statsServiceImpl.removeUserStats("u124");
		assertEquals(size-1, statsServiceImpl.getAllUser());
	}
	
	@Test
	void removeItemTest() {
		int size = initItemStats();
		statsServiceImpl.removeItemStats("i127");
		assertEquals(size-1, statsServiceImpl.getAllItem());
	}
	
	@Test
	void updateUserTest() {
		int size = initUserStats();
		StatisticUser stats = statsServiceImpl.getUserStats("u125");
		statsServiceImpl.setUserStats("u125", Categorie.ELECTRONIQUE, 5);
		stats.setnClicsElectronique(5);
		assertEquals(stats.getnClicsElectronique(), 5);
	}
	
	@Test
	void updateItemTest() {
		int size = initItemStats();
		StatisticItem stats = statsServiceImpl.getItemStats("i126");
		statsServiceImpl.setItemStats("i126", 30);
		stats.setnClicsItem(30);
		assertEquals(stats.getnClicsItem(), 30);
	}
	
	@Test
	void clickOnItemByUser() {
		em.clear();
		StatisticUser stats1 = new StatisticUser("u123", 1, 2, 3, 4, 5) ;
		statsServiceImpl.addUserStats(stats1);
		StatisticItem stats2 = new StatisticItem("i123", 10);
		statsServiceImpl.addItemStats(stats2);
		statsServiceImpl.clickOnItem("u123", "i123", Categorie.MOBILIER);
		stats1.setnClicsMobilier(6);
		stats2.setnClicsItem(11);
		assertEquals(stats1.getnClicsMobilier(), 6);
		assertEquals(stats1.getnClicsMobilier(), 11);
	}
	
	@Test
	void clickOnItem() {
		em.clear();
		StatisticItem stats = new StatisticItem("i123", 150);
		statsServiceImpl.addItemStats(stats);
		statsServiceImpl.clickOnItem("i123");
		stats.setnClicsItem(151);
		assertEquals(stats.getnClicsItem(), 151);
	}
	
	@Test
	void getHighlightTest() {
		em.clear();
		StatisticUser stats = new StatisticUser("u123", 5, 2, 4, 1, 3) ;
		statsServiceImpl.addUserStats(stats);
		List<Categorie> categories = statsServiceImpl.getHighlight("u123", 3) ;
		assertEquals(StatisticRestService.toStreamCategorie(categories), "LIVRES\nELECTRONIQUE\nMOBILIER");
	}
	
	@Test
	void getHighlightsTest() {
		initUserStats() ;
		List<Categorie> categories = statsServiceImpl.getHighlights(3) ;
		assertEquals(StatisticRestService.toStreamCategorie(categories), "LIVRES\nNOTES\nMOBILITE");
	}
	
	
	@Test
	void toStringTest() {
		StatisticUser stats1 = new StatisticUser("u123", 5, 1, 0, 1, 5);
		StatisticItem stats2 = new StatisticItem("i123", 150);
		String s1 = "Statistiques pour l'utilisateur u123 [vues de la catégorie Livres = 5, vues de la catégorie Mobilite = 1, vues de la catégorie Electronique = 0, vues de la catégorie Notes = 1, vues de la catégorie Mobilier = 5]" ;
		String s2 = "Statistiques [vues de l'item i123 = 150]" ;
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

