package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import eu.drus.jpa.unit.api.JpaUnit;
import domain.model.Categorie;
import domain.model.StatisticItem;
import domain.model.StatisticUser;
import domain.service.StatisticServiceImpls;

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
		StatisticUser stats1 = new StatisticUser("u123", 1, 2, 3, 4, 5, 6) ;
		StatisticUser stats2 = new StatisticUser("u124", 3, 2, 0, 1, 1, 2) ;
		StatisticUser stats3 = new StatisticUser("u125", 3, 2, 1, 3, 1, 1) ;
		StatisticUser stats4 = new StatisticUser("u126", 2, 1, 3, 2, 0, 1) ;
		StatisticUser stats5 = new StatisticUser("u127", 2, 1, 0, 2, 1, 1) ;
		StatisticUser stats6 = new StatisticUser("u128", 3, 3, 1, 0, 2, 2) ;
		em.merge(stats1);
		em.merge(stats2);
		em.merge(stats3);
		em.merge(stats4);
		em.merge(stats5);
		em.merge(stats6);
		usersSize = 6;
		StatisticItem istats1 = new StatisticItem("i123", 10, Categorie.ELECTRONIQUE);
		StatisticItem istats2 = new StatisticItem("i124", 20, Categorie.ELECTRONIQUE);
		StatisticItem istats3 = new StatisticItem("i125", 15, Categorie.LIVRE);
		StatisticItem istats4 = new StatisticItem("i126", 10, Categorie.MOBILIER);
		StatisticItem istats5 = new StatisticItem("i127", 12, Categorie.ELECTRONIQUE);
		StatisticItem istats6 = new StatisticItem("i128", 18, Categorie.LIVRE);
		StatisticItem istats7 = new StatisticItem("i129", 9, Categorie.COURS);
		em.merge(istats1);
		em.merge(istats2);
		em.merge(istats3);
		em.merge(istats4);
		em.merge(istats5);
		em.merge(istats6);
		em.merge(istats7);
		itemsSize = 7;
	}
	
	
	@Test
	void getAllUserTest() {
		assertEquals(usersSize+1, statsServiceImpl.getAllUser().size());
	}
	
	@Test
	void getAllItemTest() {
		assertEquals(itemsSize+1, statsServiceImpl.getAllItem().size());
	}
	
	@Test
	void addUserTest() {
		usersSize++;
		try {
			StatisticUser stats = new StatisticUser("u129", 2, 1, 3, 0, 3,1) ;
			statsServiceImpl.addUserStats(stats);
			assertEquals(usersSize, statsServiceImpl.getAllUser().size());
			statsServiceImpl.addUserStats(stats);
		}
		catch(IllegalArgumentException exc) {
			assertEquals("User with id u129 already exists", exc.getMessage());
		}
	}
	
	@Test
	void addItemTest() {
		itemsSize++;
		try {
			StatisticItem stats = new StatisticItem("i130", 25) ;
			statsServiceImpl.addItemStats(stats);
			assertEquals(itemsSize, statsServiceImpl.getAllItem().size());
			statsServiceImpl.addItemStats(stats);
		}
		catch(IllegalArgumentException exc) {
			assertEquals("Item with id i130 already exists", exc.getMessage());
		}
	}
	
	@Test
	void getUserTest() {
		StatisticUser stats = statsServiceImpl.getUserStats("u125");
		String s = "Statistiques pour l'utilisateur u125 [vues de la catégorie Livre = 3, vues de la catégorie Mobilite = 2, vues de la catégorie Electronique = 1, vues de la catégorie Cours = 3, vues de la catégorie Mobilier = 1, vues de la catégorie Autre = 1]" ;
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
		itemsSize--;
		statsServiceImpl.removeItemStats("i127");
		assertEquals(itemsSize, statsServiceImpl.getAllItem().size());
	}
	
	@Test
	void incrementUserTest() {
		statsServiceImpl.incrementUser("u126", Categorie.MOBILITE);
		statsServiceImpl.incrementUser("u126", Categorie.MOBILIER);
		statsServiceImpl.incrementUser("u126", Categorie.ELECTRONIQUE);
		statsServiceImpl.incrementUser("u126", Categorie.COURS);
		statsServiceImpl.incrementUser("u126", Categorie.LIVRE);
		statsServiceImpl.incrementUser("u126", Categorie.AUTRE);
		StatisticUser stats = statsServiceImpl.getUserStats("u126");
		stats.setnClicsMobilite(stats.getnClicsMobilite()+1);
		stats.setnClicsMobilier(stats.getnClicsMobilier()+1);
		stats.setnClicsElectronique(stats.getnClicsElectronique()+1);
		stats.setnClicsNotes(stats.getnClicsNotes()+1);
		stats.setnClicsLivres(stats.getnClicsLivres()+1);
		stats.setnClicsAutre(stats.getnClicsAutre()+1);
		assertEquals(2, stats.getnClicsMobilite());
		assertEquals(1, stats.getnClicsMobilier());
		assertEquals(4, stats.getnClicsElectronique());
		assertEquals(3, stats.getnClicsNotes());
		assertEquals(3, stats.getnClicsLivres());
		assertEquals(2, stats.getnClicsAutre());
	}
	
	@Test
	void incrementItemTest() {
		statsServiceImpl.incrementItem("i125") ;
		StatisticItem stats = statsServiceImpl.getItemStats("i125");
		stats.setnClicsItem(stats.getnClicsItem()+1);
		assertEquals(16, stats.getnClicsItem());
	}
	
	@Test
	void getUserHighlightsTest() {
		try {
			SortedMap<Categorie, Long> categories = statsServiceImpl.getUserHighlights("u123", 3) ;
			assertEquals("AUTRE - 6\nMOBILIER - 5\nCOURS - 4\n", toStreamMapCategorie(categories)) ;
			statsServiceImpl.getUserHighlights("u123", 0) ;
		}
		catch(IllegalArgumentException exc) {
			assertEquals("invalid number of categories", exc.getMessage());
		}
		try {
			statsServiceImpl.getUserHighlights("u123", 8) ;
		}
		catch(IllegalArgumentException exc) {
			assertEquals("invalid number of categories", exc.getMessage());
		}
	}
	
	@Test
	void getCategoryHighlightsTest() {
		try {
			SortedMap<Categorie, Long> categories = statsServiceImpl.getCategoryHighlights(3) ;
			assertEquals("LIVRE - 16\nAUTRE - 14\nMOBILIER - 13\n", toStreamMapCategorie(categories));
			statsServiceImpl.getCategoryHighlights(7) ;
		}
		catch(IllegalArgumentException exc) {
			assertEquals("invalid number of categories", exc.getMessage());
		}
		try {
			statsServiceImpl.getCategoryHighlights(-1) ;
		}
		catch(IllegalArgumentException exc) {
			assertEquals("invalid number of categories", exc.getMessage());
		}
	}
	
	@Test
	void getCategoryItemHighlightsTest() {
		try {
			SortedMap<String, Long> items = statsServiceImpl.getCategoryItemHighlights(Categorie.ELECTRONIQUE, 2);
			assertEquals("i124 - 20\ni127 - 12\n", toStreamMapItem(items));
			statsServiceImpl.getCategoryItemHighlights(Categorie.ELECTRONIQUE, 0);
		}
		catch(IllegalArgumentException exc) {
			assertEquals("invalid number of items", exc.getMessage());
		}
	}
	
	@Test
	void getItemHighlightsTest() {
		try {
			SortedMap<String, Long> items = statsServiceImpl.getItemHighlights(3);
			assertEquals("i130 - 25\ni124 - 20\ni128 - 18\n", toStreamMapItem(items));
			statsServiceImpl.getItemHighlights(-1);
		}
		catch(IllegalArgumentException exc) {
			assertEquals("invalid number of items", exc.getMessage());
		}
	}
	
	@Test
	void toStringTest() {
		StatisticUser stats1 = new StatisticUser("u140", 5, 1, 0, 0, 5, 1);
		StatisticItem stats2 = new StatisticItem("i140", 150, Categorie.LIVRE);
		String s1 = "Statistiques pour l'utilisateur " + stats1.getUserId() + " [vues de la catégorie Livre = " + stats1.getnClicsLivres() + ", vues de la catégorie Mobilite = " + stats1.getnClicsMobilite() + ", vues de la catégorie Electronique = " + stats1.getnClicsElectronique() +
				", vues de la catégorie Cours = " + stats1.getnClicsNotes() + ", vues de la catégorie Mobilier = " + stats1.getnClicsMobilier() + ", vues de la catégorie Autre = " + stats1.getnClicsAutre() + "]" ;
		String s2 = "Statistiques [vues de l'item " + stats2.getItemId() + " = " + stats2.getnClicsItem() + ", catégorie correspondante = " + stats2.getCategory() + "]" ;
		assertEquals(s1, stats1.toString());
		assertEquals(s2, stats2.toString());
	}
	
	@Test
	void lookupTest() {
		String[] categories = {"LIVRE", "MOBILITE", "ELECTRONIQUE", "COURS", "MOBILIER", "AUTRE"} ;
		for (String s : categories) {
			Categorie cat = Categorie.lookup(s) ;
			assertEquals(s, cat.toString()) ;
		}
		assertEquals(null, Categorie.lookup("random"));
	}
	
	@Test
	void equalsNameTest() {
		Categorie cat = Categorie.LIVRE ;
		assertEquals(true, cat.equalsName("LIVRE")) ;
	}
	
	@Test
	void modelTest() {
		StatisticUser stats1 = new StatisticUser(1, 1, 1, 1, 1, 1) ;
		StatisticItem stats2 = new StatisticItem(10, Categorie.LIVRE) ;
		StatisticItem stats3 = new StatisticItem(10) ;
		stats1.setUserId("u141");
		stats2.setItemId("i141");
		stats2.setCategory(Categorie.AUTRE);
		stats3.setItemId("i142");
		assertEquals("u141", stats1.getUserId());
		assertEquals("i141", stats2.getItemId());
		assertEquals(Categorie.AUTRE, stats2.getCategory());
		assertEquals("i142", stats3.getItemId());
	}
	
	private static String toStreamMapCategorie(SortedMap<Categorie, Long> map) {
		StringBuilder strBld = new StringBuilder() ;
		Iterator<Entry<Categorie, Long>> it = map.entrySet().iterator() ;
		while (it.hasNext()) {
			Entry<Categorie, Long> entry = it.next() ;
			strBld.append(entry.getKey().toString() + " - " + entry.getValue() + "\n") ;
		}
		return strBld.toString() ;
	}
	
	private static String toStreamMapItem(SortedMap<String, Long> map) {
		StringBuilder strBld = new StringBuilder() ;
		Iterator<Entry<String, Long>> it = map.entrySet().iterator() ;
		while (it.hasNext()) {
			Entry<String, Long> entry = it.next() ;
			strBld.append(entry.getKey() + " - " + entry.getValue() + "\n") ;
		}
		return strBld.toString() ;
	}
	
}
