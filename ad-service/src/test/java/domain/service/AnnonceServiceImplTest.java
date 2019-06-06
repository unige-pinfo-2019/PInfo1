package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	
	@InjectMocks
	private AnnonceServiceImpl annonceserviceimpl;
	
	

	private int initDataStore() {
		em.clear();
		List<Annonce> annonces;
		Annonce annonce1 = new Annonce("1234", "étagère", "mobilier", "neuf","");
		Annonce annonce2 = new Annonce("1234", "chaise", "mobilier", "neuf","");
		Annonce annonce3 = new Annonce("1235", "velo", "vehicule", "neuf","");
		annonceserviceimpl.addAnnonce(annonce1);
		annonceserviceimpl.addAnnonce(annonce2);
		annonceserviceimpl.addAnnonce(annonce3);
		annonces = annonceserviceimpl.getAnnonce("1234");
		int size = annonces.size();
		return size;
	}
	
	private int initDataStore2() {
		em.clear();
		List<Annonce> annonces;
		Annonce annonce1 = new Annonce("1234","étagère","mobilier","neuf","");
		Annonce annonce2 = new Annonce("1234","chaise","mobilier","neuf","");
		Annonce annonce3 = new Annonce("123","vtt","velo","neuf","");
		Annonce annonce4 = new Annonce("123","sofa","mobilier","neuf","");
		Annonce annonce5 = new Annonce("1235","velo","velo","neuf","");
		Annonce annonce6 = new Annonce("1235","magazine","livre","neuf","");
		annonceserviceimpl.addAnnonce(annonce1);
		annonceserviceimpl.addAnnonce(annonce2);
		annonceserviceimpl.addAnnonce(annonce3);
		annonceserviceimpl.addAnnonce(annonce4);
		annonceserviceimpl.addAnnonce(annonce5);
		annonceserviceimpl.addAnnonce(annonce6);
		annonces = annonceserviceimpl.getAll();
		int size = annonces.size();
		return size;
	}
	
	private int initDataStore3() {
		em.clear();
		List<Annonce> annonces;
		Annonce annonce1 = new Annonce("1234","étagère","mobilier","neuf","");
		Annonce annonce2 = new Annonce("1234","chaise","mobilier","neuf","");
		Annonce annonce3 = new Annonce("123","vtt","velo","neuf","");
		Annonce annonce4 = new Annonce("123","sofa","mobilier","neuf","");
		Annonce annonce5 = new Annonce("1235","velo","velo","neuf","");
		Annonce annonce6 = new Annonce("1235","magazine","livre","neuf","");
		annonceserviceimpl.addAnnonce(annonce1);
		annonceserviceimpl.addAnnonce(annonce2);
		annonceserviceimpl.addAnnonce(annonce3);
		annonceserviceimpl.addAnnonce(annonce4);
		annonceserviceimpl.addAnnonce(annonce5);
		annonceserviceimpl.addAnnonce(annonce6);
		annonces = annonceserviceimpl.getAll();
		int size = annonces.size();
		return size;
	}
	
	private int initDataStore4() {
		em.clear();
		List<Annonce> annonces;
		Annonce annonce1 = new Annonce("1234","étagère","mobilier","neuf","");
		Annonce annonce2 = new Annonce("1234","chaise","mobilier","neuf","");
		Annonce annonce3 = new Annonce("123","vtt","velo","neuf","");
		Annonce annonce4 = new Annonce("123","sofa","mobilier","neuf","");
		Annonce annonce5 = new Annonce("1235","velo","velo","neuf","");
		Annonce annonce6 = new Annonce("1235","magazine","livre","neuf","");
		annonceserviceimpl.addAnnonce(annonce1);
		annonceserviceimpl.addAnnonce(annonce2);
		annonceserviceimpl.addAnnonce(annonce3);
		annonceserviceimpl.addAnnonce(annonce4);
		annonceserviceimpl.addAnnonce(annonce5);
		annonceserviceimpl.addAnnonce(annonce6);
		annonces = annonceserviceimpl.getAll();
		int size = annonces.size();
		return size;
	}
	
	@Test
	void getAnnonceTest() {
		int size = initDataStore();
		assertEquals(size, annonceserviceimpl.getAnnonce("1234").size());
	}
	
	@Test
	void allAnnonceTest() {
		int size = initDataStore2();
		assertEquals(size, annonceserviceimpl.getAll().size());
	}
	
	@Test
	void addAnnonceTest(){
		int size = initDataStore3();
		Annonce annonce = new Annonce("1236","Le seigneur des anneaux","livre","neuf","");
		annonceserviceimpl.addAnnonce(annonce);
		assertEquals(size+1, annonceserviceimpl.getAll().size());
	}
	
//	@Test 
//	void removeAnnonceTest() {
//		Annonce annonce = new Annonce("1234","1236","Le seigneur des anneaux","livre",3,"");
//		Annonce annonce2 = new Annonce("1235","1236","Le pianiste","livre",3,"");
//		annonceserviceimpl.addAnnonce(annonce);
//		annonceserviceimpl.addAnnonce(annonce2);
//		annonceserviceimpl.removeAnnonce(annonce2);
//		assertEquals(5, annonce2.getState());
//	}
//	
//	@Test 
//	void updateAnnonceTest() {
//		initDataStore4();
//		Annonce annonce = new Annonce("1234","1236","Le seigneur des anneaux","livre",3,"");
//		Annonce annonce2 = new Annonce("1235","1236","Le pianiste","livre",3,"");
//		annonceserviceimpl.addAnnonce(annonce);
//		annonceserviceimpl.addAnnonce(annonce2);
//		Annonce annonce3 = new Annonce("1235","1236", "un autre dvd","livre",3,"");
//		annonceserviceimpl.updateAnnonce(annonce3);
//		assertEquals("un autre dvd",annonce2.getName());
//	}	
	
	@Test
	void modelTest() {
		Annonce annonce = new Annonce("1236","Le seigneur des anneaux","livre","neuf","");
		annonce.setUsrId("1234");
		String newId = UUID.randomUUID().toString();
		annonce.setId(newId);
		annonce.setDescription("un livre");
		annonce.setCategory("mobilier");
		annonce.setState("use");
		assertEquals("1234",annonce.getUsrId());
		assertEquals(newId,annonce.getId());
		assertEquals("mobilier",annonce.getCategory());
		assertEquals("use",annonce.getState());
		assertEquals("un livre",annonce.getDescription());
		assertEquals(annonce.toString(),"Annonce [id = "+ newId +  " userId = " + "1234" + " name=" + "Le seigneur des anneaux" + ", category=" + "mobilier"
				+ ", state=" + "use" + "]");
	}
	
}

