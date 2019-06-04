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
	
	@InjectMocks
	private MessengerServiceImpl Messengerserviceimpl;
	
	@Test
	void modelTest() {
		Messenger messenger = new Messenger("Hello","1234","1235");
		messenger.setMsg("Bonjour");
		messenger.setSendId("1236");
		messenger.setReceiveId("1237");
		messenger.setDateTime(null);
		boolean bool = true;
		messenger.setSeenReceive(bool);
		assertEquals("Bonjour",messenger.getMsg());
		assertEquals("1236",messenger.getSendId());
		assertEquals("1237",messenger.getReceiveId());
		assertEquals(null,messenger.getDateTime());
		assertEquals(bool,messenger.getSeenReceive());
	}
	

	private int initDataStore() {
		em.clear();
		List<Messenger> Messengers;
		Messenger Messenger1 = new Messenger("Hello","1234","1235");
		Messenger Messenger2 = new Messenger("Bonjour","1235","1236");
		Messenger Messenger3 = new Messenger("Hi","1237","1238");
		Messengerserviceimpl.addMessenger(Messenger1);
		Messengerserviceimpl.addMessenger(Messenger2);
		Messengerserviceimpl.addMessenger(Messenger3);
		Messengers = Messengerserviceimpl.getAll();
		int size = Messengers.size();
		return size;
	}
	
	private int initDataStore2() {
		em.clear();
		List<Messenger> Messengers;
		Messenger Messenger1 = new Messenger("Hello","1234","1235");
		Messenger Messenger2 = new Messenger("Bonjour","1235","1236");
		Messenger Messenger3 = new Messenger("Hi","1237","1238");
		Messenger Messenger4 = new Messenger("Hello","1235","1234");
		Messengerserviceimpl.addMessenger(Messenger1);
		Messengerserviceimpl.addMessenger(Messenger2);
		Messengerserviceimpl.addMessenger(Messenger3);
		Messengerserviceimpl.addMessenger(Messenger4);
		Messengers = Messengerserviceimpl.getAll();
		int size = Messengers.size();
		return size;
	}
	
	private int initDataStore3() {
		em.clear();
		List<Messenger> Messengers;
		Messenger Messenger1 = new Messenger("Hello","1234","1235");
		Messenger Messenger2 = new Messenger("Bonjour","1234","1236");
		Messenger Messenger3 = new Messenger("Hi","1234","1238");
		Messenger Messenger4 = new Messenger("Hello","1235","1234");
		Messenger Messenger5 = new Messenger("Hey","1234","1237");
		Messengerserviceimpl.addMessenger(Messenger1);
		Messengerserviceimpl.addMessenger(Messenger2);
		Messengerserviceimpl.addMessenger(Messenger3);
		Messengerserviceimpl.addMessenger(Messenger4);
		Messengerserviceimpl.addMessenger(Messenger5);
		Messengers = Messengerserviceimpl.getAll();
		int size = Messengers.size();
		return size;
	}
	
	@Test
	void allMessengerTest() {
		int size = initDataStore();
		assertEquals(size, Messengerserviceimpl.getAll().size());
	}
	
	@Test
	void addMessengerTest() {
		int size = initDataStore();
		Messenger messenger = new Messenger("Ho","1234","1236");
		Messengerserviceimpl.addMessenger(messenger);
		List<Messenger> messengers = Messengerserviceimpl.getAll();
		int size2 = messengers.size();
		assertEquals(size+1,size2);
	}
	
	@Test
	void getMessengerTest() {
		initDataStore2();
		List<Messenger> messengers = Messengerserviceimpl.getMessenger("1234", "1235");
		int size2 = messengers.size();
		assertEquals(size2,8);
	}
	
	
	@Test
	void getInfoTest() {
		int size = initDataStore3();
		List<Object> objects = Messengerserviceimpl.getInfo("1234");
		int size2 = objects.size();
		assertEquals(size2,3);
	}
	
}

