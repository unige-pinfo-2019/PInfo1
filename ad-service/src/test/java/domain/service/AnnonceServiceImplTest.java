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
		Annonce annonce1 = new Annonce("1234", "étagère", "mobilier", 1,"");
		Annonce annonce2 = new Annonce("1234", "chaise", "mobilier", 1,"");
		Annonce annonce3 = new Annonce("1235", "velo", "vehicule", 1,"");
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
		Annonce annonce1 = new Annonce("1234","étagère","mobilier",1,"");
		Annonce annonce2 = new Annonce("1234","chaise","mobilier",2,"");
		Annonce annonce3 = new Annonce("123","vtt","velo",5,"");
		Annonce annonce4 = new Annonce("123","sofa","mobilier",4,"");
		Annonce annonce5 = new Annonce("1235","velo","velo",3,"");
		Annonce annonce6 = new Annonce("1235","magazine","livre",3,"");
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
		Annonce annonce1 = new Annonce("1234","étagère","mobilier",1,"");
		Annonce annonce2 = new Annonce("1234","chaise","mobilier",2,"");
		Annonce annonce3 = new Annonce("123","vtt","velo",5,"");
		Annonce annonce4 = new Annonce("123","sofa","mobilier",4,"");
		Annonce annonce5 = new Annonce("1235","velo","velo",3,"");
		Annonce annonce6 = new Annonce("1235","magazine","livre",3,"");
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
		Annonce annonce1 = new Annonce("1234","étagère","mobilier",1,"");
		Annonce annonce2 = new Annonce("1234","chaise","mobilier",2,"");
		Annonce annonce3 = new Annonce("123","vtt","velo",5,"");
		Annonce annonce4 = new Annonce("123","sofa","mobilier",4,"");
		Annonce annonce5 = new Annonce("1235","velo","velo",3,"");
		Annonce annonce6 = new Annonce("1235","magazine","livre",3,"");
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
	
//	private void initDataStore5() {
//		em.clear();
//		Annonce annonce1 = new Annonce("1234","étagère","mobilier",1);
//		Annonce annonce2 = new Annonce("1234","chaise","mobilier",2);
//		Annonce annonce3 = new Annonce("123","vtt","velo",5);
//		Annonce annonce4 = new Annonce("123","sofa","mobilier",4);
//		Annonce annonce5 = new Annonce("1235","velo","velo",3);
//		Annonce annonce6 = new Annonce("1235","magazine","livre",3);
//		annonceserviceimpl.addAnnonce(annonce1);
//		annonceserviceimpl.addAnnonce(annonce2);
//		annonceserviceimpl.addAnnonce(annonce3);
//		annonceserviceimpl.addAnnonce(annonce4);
//		annonceserviceimpl.addAnnonce(annonce5);
//		annonceserviceimpl.addAnnonce(annonce6);
//	}
	
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
		Annonce annonce = new Annonce("1236","Le seigneur des anneaux","livre",3,"");
		annonceserviceimpl.addAnnonce(annonce);
		assertEquals(size+1, annonceserviceimpl.getAll().size());
	}
	
//	@Test 
//	void removeAnnonceTest() {
//		int size = initDataStore4();
//		Annonce annonce = new Annonce("1236","Le seigneur des anneaux","livre",3,"");
//		Annonce annonce2 = new Annonce("1236","Le pianiste","livre",3,"");
//		annonceserviceimpl.addAnnonce(annonce);
//		annonceserviceimpl.addAnnonce(annonce2);
//		String id = annonce2.getId();
//		annonceserviceimpl.removeAnnonce(id);
//		assertEquals(size+1, annonceserviceimpl.getAll().size());
//	}
	
//	@Test 
//	void updateAnnonceTest() {
//		initDataStore4();
//		Annonce annonce = new Annonce("1236","Le seigneur des anneaux","livre",3,"");
//		Annonce annonce2 = new Annonce("1236","Le pianiste","livre",3,"");
//		annonceserviceimpl.addAnnonce(annonce);
//		annonceserviceimpl.addAnnonce(annonce2);
//		String id = annonce2.getId();
//		annonceserviceimpl.updateAnnonce(id,"name","Le liseur");
//		annonce2.setName("Le liseur");
//		annonceserviceimpl.updateAnnonce(id,"category","magazine");
//		annonce2.setCategory("magazine");
//		annonceserviceimpl.updateAnnonce(id,"state","5");
//		annonce2.setState(5);
//		annonceserviceimpl.updateAnnonce(id,"error","error");
//		assertEquals("Le liseur",annonce2.getName());
//		assertEquals("magazine",annonce2.getCategory());
//		assertEquals(5,annonce2.getState());
//	}	
	
	@Test
	void modelTest() {
		Annonce annonce = new Annonce("1236","Le seigneur des anneaux","livre",3,"");
		annonce.setUsrId("1234");
		String newId = UUID.randomUUID().toString();
		annonce.setId(newId);
		assertEquals("1234",annonce.getUsrId());
		assertEquals(annonce.getId(),newId);
		assertEquals(annonce.toString(),"Annonce [id = "+ newId +  " userId = " + "1234" + " name=" + "Le seigneur des anneaux" + ", category=" + "livre"
				+ ", state=" + 3 + "]");
	}
	
}

