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
	
	private int initDataStore5() {
		em.clear();
		List<Annonce> annonces;
		Annonce annonce1 = new Annonce("1234","étagère","mobilier","neuf","");
		Annonce annonce2 = new Annonce("1234","chaise","mobilier","neuf","");
		annonceserviceimpl.addAnnonce(annonce1);
		annonceserviceimpl.addAnnonce(annonce2);
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
	
	@Test 
	void removeAnnonceTest() {
		initDataStore4();
		List<Annonce> annonces = annonceserviceimpl.getAll();
		Annonce annonceToChange = annonces.get(0);
		annonceserviceimpl.removeAnnonce(annonceToChange);
		String answer = "false";
		List<Annonce> annonces2 = annonceserviceimpl.getAll();
		for (Annonce a : annonces2) {
			if (a.getId() == annonceToChange.getId()) {
				answer = a.getSold();
			}
		}
		// Ici souci
		assertEquals(annonceToChange.getSold(), answer);
	}
	
	@Test 
	void updateAnnonceTest() {
		initDataStore5();
		List<Annonce> annonces = annonceserviceimpl.getAll();
		Annonce annonceToChange = annonces.get(0);
		Annonce annonce3 = new Annonce(annonceToChange.getId(),"1234","velo","mobilier","neuf","");
		annonceserviceimpl.updateAnnonce(annonce3);
		String answer = "étagère";
		List<Annonce> annonces2 = annonceserviceimpl.getAll();
		for (Annonce a : annonces2) {
			if (a.getId() == annonceToChange.getId()) {
				answer = a.getName();
			}
		}
		// Ici souci
		assertEquals(annonceToChange.getName(),answer);
	}	
	
	@Test
	void modelTest() {
		Annonce annonce = new Annonce("1236","Le seigneur des anneaux","livre","neuf","");
		annonce.setUsrId("1234");
		String newId = UUID.randomUUID().toString();
		annonce.setId(newId);
		annonce.setName("un livre");
		annonce.setDescription("un livre");
		annonce.setCategory("mobilier");
		annonce.setState("use");
		annonce.setSold("true");
		assertEquals("1234",annonce.getUsrId());
		assertEquals(newId,annonce.getId());
		assertEquals("mobilier",annonce.getCategory());
		assertEquals("use",annonce.getState());
		assertEquals("un livre",annonce.getDescription());
		assertEquals("true",annonce.getSold());
		assertEquals(annonce.toString(),"Annonce [id = "+ newId +  " userId = " + "1234" + " name=" + "un livre" + ", category=" + "mobilier"
				+ ", state=" + "use" + "]");
	}
	
}

