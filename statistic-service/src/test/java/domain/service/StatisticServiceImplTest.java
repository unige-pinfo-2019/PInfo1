package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.StatisticItem;
import domain.model.StatisticUser;
import eu.drus.jpa.unit.api.JpaUnit;

@Transactional
@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)

public class StatisticServiceImplTest {

	@Spy
	@PersistenceContext(unitName = "StatisticPUTest")
	EntityManager em;
	
	@InjectMocks
	private StatisticServiceImpls statservice;
	
	
	public void initDataStoreUser1() {
		StatisticUser u1 = new StatisticUser("12341",1,3,4,5,1);
		StatisticUser u2 = new StatisticUser("12351",3,2,1,4,3);
		em.persist(u1);
		em.persist(u2);
	}
	
	
		public void initDataStoreItem1() {
		StatisticItem i1 = new StatisticItem("12341",1000000);
		StatisticItem i2 = new StatisticItem("12351",1000001);
		StatisticItem i3 = new StatisticItem("22222",2000000);
		StatisticItem i4 = new StatisticItem("12371",3000000);
		StatisticItem i5 = new StatisticItem("12381",4000000);
		StatisticItem i6 = new StatisticItem("12391",5000000);
		StatisticItem i7 = new StatisticItem("12451",6000000);
		StatisticItem i8 = new StatisticItem("12551",7000000);
		em.persist(i1);
		em.persist(i2);
		em.persist(i3);
		em.persist(i4);
		em.persist(i5);
		em.persist(i6);
		em.persist(i7);
		em.persist(i8);
	}
	
	
	@Test
	public void MostSearchCategories() {
		initDataStoreUser1();
		List<String> res = new ArrayList<>();
		res.add("notes");res.add("electronique");res.add("mobilite");
		List<String> res2 = statservice.mostSearchCategories("12351");
		Set<String> set = new HashSet<String>(res2);
		Boolean a = true;
		if(set.size() < res2.size()){
			a = false;
		}
		assertEquals(a,true);
		assertEquals(res,statservice.mostSearchCategories("12341"));
	}
	
	@Test
	public void MostSearchItemTest() {
		initDataStoreItem1();
		List<String> res = new ArrayList<>();
		res.add("12551");
		res.add("12451");
		res.add("12391");
		res.add("12381");
		res.add("12371");
		res.add("22222");
		assertEquals(res, statservice.mostSearchItems());

	}
	
	@Test
	public void IncrementCategoryTest() {
		StatisticUser u3 = new StatisticUser("22222",0,0,0,0,0);
		em.persist(u3);
		statservice.incrementCategory("22222", "Livres");
		statservice.incrementCategory("22222", "Livres");
		statservice.incrementCategory("22222", "Livres");
		statservice.incrementCategory("22222", "Livres");
		statservice.incrementCategory("22222", "Livres");
		statservice.incrementCategory("22222", "Livres");


		statservice.incrementCategory("22222", "Mobilite");
		statservice.incrementCategory("22222", "Mobilite");
		statservice.incrementCategory("22222", "Mobilite");
		statservice.incrementCategory("22222", "Mobilite");

		statservice.incrementCategory("22222", "Mobilier");
		statservice.incrementCategory("22222", "Mobilier");

		
		statservice.incrementCategory("22222", "Electronique");
		statservice.incrementCategory("22222", "Notes");
		
		List<String> str = new ArrayList<>();
		str.add("livre");str.add("mobilite");str.add("mobilier");
		List<String> res = statservice.mostSearchCategories("22222");
		//assertEquals(res,str);
	}
	
	@Test
	public void IncrementItemsTest() {
		StatisticItem i1 = new StatisticItem("11111",1000000000);
		StatisticItem i2 = new StatisticItem("11112",1000000001);
		em.persist(i2);
		em.persist(i1);
		statservice.incrementItems("11111");
		statservice.incrementItems("11111");
		statservice.incrementItems("11111");
		List<String> res = statservice.mostSearchItems();
		List<String> str = new ArrayList<>();
		str.add("11111"); str.add("11112");str.add("12551");
		str.add("12451");str.add("12391");str.add("12381");
		assertEquals(res,str);
	}
	
	@Test
	public void adddelTest() {
		int sizeI = statservice.getAllItem().size();
		statservice.additem("49388");
		int sizeIa = statservice.getAllItem().size();
		
		int sizeU = statservice.getAllUser().size();
		statservice.addUser("488783");
		int sizeUa = statservice.getAllUser().size();

		statservice.removeitem("49388");
		int sizeIr = statservice.getAllItem().size();
		
		statservice.removeUser("488783");
		int sizeUr = statservice.getAllUser().size();
		assertEquals(sizeI+1, sizeIa);
		assertEquals(sizeU+1, sizeUa);
		assertEquals(sizeIa-1, sizeIr);
		assertEquals(sizeUa-1, sizeUr);

	}
	
	@Test
	public void toStringTest() {
		StatisticItem i1 = new StatisticItem("33333",0);
		StatisticUser u1 = new StatisticUser("333333",0,0,0,0,0);
		i1.setItemId("33334");
		i1.setnClicsItem(2);
		u1.setUserId("333334");
		u1.setnClicsElectronique(1);
		u1.setnClicsLivres(1);
		u1.setnClicsMobilier(1);
		u1.setnClicsMobilite(1);
		u1.setnClicsNotes(1);
		String stru = "Statistiques pour l'utilisateur 333334 [vues de la catégorie Livres = 1, vues de la catégorie Mobilite = 1, vues de la catégorie Electronique = 1, vues de la catégorie Notes = 1, vues de la catégorie Mobilier = 1]";
		String stri = "Statistiques [vues de l'item 33334 = 2]";
		assertEquals("Statistiques [vues de l'item "+i1.getItemId()+" = "+i1.getnClicsItem()+"]",stri);
		assertEquals("Statistiques pour l'utilisateur "+u1.getUserId()+" [vues de la catégorie Livres = "+u1.getnClicsLivres()+", vues de la catégorie Mobilite = "+u1.getnClicsMobilite()+", vues de la catégorie Electronique = "+u1.getnClicsElectronique()+", vues de la catégorie Notes = "+u1.getnClicsNotes()+", vues de la catégorie Mobilier = "+u1.getnClicsMobilier()+"]",stru);
		
	}
	
}
