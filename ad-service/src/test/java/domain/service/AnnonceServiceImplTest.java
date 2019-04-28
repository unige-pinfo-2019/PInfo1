package domain.service;

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

import domain.model.Annonce;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class AnnonceServiceImplTest {
	
	@Spy
	@PersistenceContext(unitName = "AnnoncesPUTest")
	EntityManager em;
	//FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
	
	@InjectMocks
	private AnnonceServiceImpl annonceserviceimpl;
	
	
//	@Test 
//	void addAnnoncesTest() {
//		int size = initDataStore();
//		annonceserviceimpl.addAnnonces();
//		int size2 = annonceserviceimpl.getHighlight("tom").size();
//		assertEquals(size+4,size2);
//		
//	}
//	
//	@Test
//	void getHighlightTest(){
//		int size = initDataStore();
//		assertEquals(size, annonceserviceimpl.getHighlight("tom").size());
//		
//	}
	
//	@Test
//	void getAllTest(){
//		int size = initDataStore();
//		assertEquals(size, annonceserviceimpl.getAll().size());
//		
//	}
	
	
//	@Test
//	void getBySearchTest() {
//		initDataStore2();
//		Annonce Annonce1 = new Annonce("étagère","mobilier",1);
//		Annonce Annonce2 = new Annonce("chaise","mobilier",2);
//		Annonce Annonce3 = new Annonce("vtt","velo",5);
//		Annonce Annonce4 = new Annonce("sofa","mobilier",4);
//		Annonce Annonce5 = new Annonce("velo","velo",3);
//		Annonce Annonce6 = new Annonce("magazine","livre", 3);
//		List <Annonce> testvelo = new ArrayList<Annonce>();List <Annonce> testcat = new ArrayList<Annonce>(); List<Annonce> testpri =  new ArrayList<Annonce>();
//		testvelo.add(Annonce5);
//		testcat.add(Annonce1);testcat.add(Annonce2);testcat.add(Annonce4);
//		testpri.add(Annonce1);testpri.add(Annonce2);testpri.add(Annonce3);testpri.add(Annonce4);testpri.add(Annonce5);testpri.add(Annonce6);
//		assertEquals(testvelo, annonceserviceimpl.getBySearch("velo", "all", 1,1));
//		assertEquals(testcat, annonceserviceimpl.getBySearch("", "mobilier", 1,1));
//		assertEquals(testpri, annonceserviceimpl.getBySearch("", "all", 1,1));
//	}
		
	@Test
	void ToStringTest() {
		Annonce Annonce1 = new Annonce("velo","velo",1);
		Annonce1.setId(1);
		String s = "Annonce [id = 1 userId = 0 name=velo, category=velo, state=1]";
		assertEquals(s, Annonce1.toString());
	}
	
//	private int initDataStore() {
//		int size = annonceserviceimpl.getHighlight("tom").size();
//		List<Annonce> it = getAnnonces();	
//		for (Annonce c : it) {
//			em.persist(c);
//		}
//		return size + it.size();
//	}
	
	
	private void initDataStore2() {
		em.clear();
		Annonce Annonce1 = new Annonce("étagère","mobilier",1);
		Annonce Annonce2 = new Annonce("chaise","mobilier",2);
		Annonce Annonce3 = new Annonce("vtt","velo",5);
		Annonce Annonce4 = new Annonce("sofa","mobilier",4);
		Annonce Annonce5 = new Annonce("velo","velo",3);
		Annonce Annonce6 = new Annonce("magazine","livre", 3);
		em.persist(Annonce1);
		em.persist(Annonce2);
		em.persist(Annonce3);
		em.persist(Annonce4);
		em.persist(Annonce5);
		em.persist(Annonce6);
	}
	
	private List<Annonce> getAnnonces() {
		List<Annonce> it = new ArrayList<>();
		long numberOfCpty = Math.round((Math.random() * 10));
		for (int i = 0; i < numberOfCpty; i++) {
			it.add(createAnnonce());
		}
		return it;
	}


	private Annonce createAnnonce(){
		Annonce i = new Annonce();
		i.setName(UUID.randomUUID().toString());
		i.setCategory(UUID.randomUUID().toString());
		i.setState(1 + (int)(Math.random() * ((5 - 1) + 1)));
		return i;
	}
	
	
}

