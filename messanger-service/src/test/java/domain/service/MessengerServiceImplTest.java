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

import domain.model.Messenger;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class MessengerServiceImplTest {
	
	@Spy
	@PersistenceContext(unitName = "MessengersPUTest")
	EntityManager em;
	//FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
	
	@InjectMocks
	private MessengerServiceImpl Messengerserviceimpl;
	
	

//	private int initDataStore() {
//		em.clear();
//		List<Messenger> Messengers;
//		Messenger Messenger1 = new Messenger("1234", "étagère", "mobilier", 1,"");
//		Messenger Messenger2 = new Messenger("1234", "chaise", "mobilier", 1,"");
//		Messenger Messenger3 = new Messenger("1235", "velo", "vehicule", 1,"");
//		Messengerserviceimpl.addMessenger(Messenger1);
//		Messengerserviceimpl.addMessenger(Messenger2);
//		Messengerserviceimpl.addMessenger(Messenger3);
//		Messengers = Messengerserviceimpl.getMessenger("1234");
//		int size = Messengers.size();
//		return size;
//	}
//	
//	private int initDataStore2() {
//		em.clear();
//		List<Messenger> Messengers;
//		Messenger Messenger1 = new Messenger("1234","étagère","mobilier",1,"");
//		Messenger Messenger2 = new Messenger("1234","chaise","mobilier",2,"");
//		Messenger Messenger3 = new Messenger("123","vtt","velo",5,"");
//		Messenger Messenger4 = new Messenger("123","sofa","mobilier",4,"");
//		Messenger Messenger5 = new Messenger("1235","velo","velo",3,"");
//		Messenger Messenger6 = new Messenger("1235","magazine","livre",3,"");
//		Messengerserviceimpl.addMessenger(Messenger1);
//		Messengerserviceimpl.addMessenger(Messenger2);
//		Messengerserviceimpl.addMessenger(Messenger3);
//		Messengerserviceimpl.addMessenger(Messenger4);
//		Messengerserviceimpl.addMessenger(Messenger5);
//		Messengerserviceimpl.addMessenger(Messenger6);
//		Messengers = Messengerserviceimpl.getAll();
//		int size = Messengers.size();
//		return size;
//	}
//	
//	private int initDataStore3() {
//		em.clear();
//		List<Messenger> Messengers;
//		Messenger Messenger1 = new Messenger("1234","étagère","mobilier",1,"");
//		Messenger Messenger2 = new Messenger("1234","chaise","mobilier",2,"");
//		Messenger Messenger3 = new Messenger("123","vtt","velo",5,"");
//		Messenger Messenger4 = new Messenger("123","sofa","mobilier",4,"");
//		Messenger Messenger5 = new Messenger("1235","velo","velo",3,"");
//		Messenger Messenger6 = new Messenger("1235","magazine","livre",3,"");
//		Messengerserviceimpl.addMessenger(Messenger1);
//		Messengerserviceimpl.addMessenger(Messenger2);
//		Messengerserviceimpl.addMessenger(Messenger3);
//		Messengerserviceimpl.addMessenger(Messenger4);
//		Messengerserviceimpl.addMessenger(Messenger5);
//		Messengerserviceimpl.addMessenger(Messenger6);
//		Messengers = Messengerserviceimpl.getAll();
//		int size = Messengers.size();
//		return size;
//	}
	
//	private int initDataStore4() {
//		em.clear();
//		List<Messenger> Messengers;
//		Messenger Messenger1 = new Messenger("1234","étagère","mobilier",1,"");
//		Messenger Messenger2 = new Messenger("1234","chaise","mobilier",2,"");
//		Messenger Messenger3 = new Messenger("123","vtt","velo",5,"");
//		Messenger Messenger4 = new Messenger("123","sofa","mobilier",4,"");
//		Messenger Messenger5 = new Messenger("1235","velo","velo",3,"");
//		Messenger Messenger6 = new Messenger("1235","magazine","livre",3,"");
//		Messengerserviceimpl.addMessenger(Messenger1);
//		Messengerserviceimpl.addMessenger(Messenger2);
//		Messengerserviceimpl.addMessenger(Messenger3);
//		Messengerserviceimpl.addMessenger(Messenger4);
//		Messengerserviceimpl.addMessenger(Messenger5);
//		Messengerserviceimpl.addMessenger(Messenger6);
//		Messengers = Messengerserviceimpl.getAll();
//		int size = Messengers.size();
//		return size;
//	}
	
//	private void initDataStore5() {
//		em.clear();
//		Messenger Messenger1 = new Messenger("1234","étagère","mobilier",1);
//		Messenger Messenger2 = new Messenger("1234","chaise","mobilier",2);
//		Messenger Messenger3 = new Messenger("123","vtt","velo",5);
//		Messenger Messenger4 = new Messenger("123","sofa","mobilier",4);
//		Messenger Messenger5 = new Messenger("1235","velo","velo",3);
//		Messenger Messenger6 = new Messenger("1235","magazine","livre",3);
//		Messengerserviceimpl.addMessenger(Messenger1);
//		Messengerserviceimpl.addMessenger(Messenger2);
//		Messengerserviceimpl.addMessenger(Messenger3);
//		Messengerserviceimpl.addMessenger(Messenger4);
//		Messengerserviceimpl.addMessenger(Messenger5);
//		Messengerserviceimpl.addMessenger(Messenger6);
//	}
	
//	@Test
//	void getMessengerTest() {
//		int size = initDataStore();
//		assertEquals(size, Messengerserviceimpl.getMessenger("1234").size());
//	}
//	
//	@Test
//	void allMessengerTest() {
//		int size = initDataStore2();
//		assertEquals(size, Messengerserviceimpl.getAll().size());
//	}
//	
//	@Test
//	void addMessengerTest(){
//		int size = initDataStore3();
//		Messenger Messenger = new Messenger("1236","Le seigneur des anneaux","livre",3,"");
//		Messengerserviceimpl.addMessenger(Messenger);
//		assertEquals(size+1, Messengerserviceimpl.getAll().size());
//	}
	
//	@Test 
//	void removeMessengerTest() {
//		int size = initDataStore4();
//		Messenger Messenger = new Messenger("1236","Le seigneur des anneaux","livre",3,"");
//		Messenger Messenger2 = new Messenger("1236","Le pianiste","livre",3,"");
//		Messengerserviceimpl.addMessenger(Messenger);
//		Messengerserviceimpl.addMessenger(Messenger2);
//		String id = Messenger2.getId();
//		Messengerserviceimpl.removeMessenger(id);
//		assertEquals(size+1, Messengerserviceimpl.getAll().size());
//	}
	
//	@Test 
//	void updateMessengerTest() {
//		initDataStore4();
//		Messenger Messenger = new Messenger("1236","Le seigneur des anneaux","livre",3,"");
//		Messenger Messenger2 = new Messenger("1236","Le pianiste","livre",3,"");
//		Messengerserviceimpl.addMessenger(Messenger);
//		Messengerserviceimpl.addMessenger(Messenger2);
//		String id = Messenger2.getId();
//		Messengerserviceimpl.updateMessenger(id,"name","Le liseur");
//		Messenger2.setName("Le liseur");
//		Messengerserviceimpl.updateMessenger(id,"category","magazine");
//		Messenger2.setCategory("magazine");
//		Messengerserviceimpl.updateMessenger(id,"state","5");
//		Messenger2.setState(5);
//		Messengerserviceimpl.updateMessenger(id,"error","error");
//		assertEquals("Le liseur",Messenger2.getName());
//		assertEquals("magazine",Messenger2.getCategory());
//		assertEquals(5,Messenger2.getState());
//	}	
	
//	@Test
//	void modelTest() {
//		Messenger Messenger = new Messenger("1236","Le seigneur des anneaux","livre",3,"");
//		Messenger.setUsrId("1234");
//		String newId = UUID.randomUUID().toString();
//		Messenger.setId(newId);
//		assertEquals("1234",Messenger.getUsrId());
//		assertEquals(Messenger.getId(),newId);
//		assertEquals(Messenger.toString(),"Messenger [id = "+ newId +  " userId = " + "1234" + " name=" + "Le seigneur des anneaux" + ", category=" + "livre"
//				+ ", state=" + 3 + "]");
//	}
	
}

