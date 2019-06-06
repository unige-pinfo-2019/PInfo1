package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
		em.persist(Messenger1);
		em.persist(Messenger2);
		em.persist(Messenger3);

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
		Messenger Messenger4 = new Messenger("Hellow","1235","1234");

		em.persist(Messenger1);
		em.persist(Messenger2);
		em.persist(Messenger3);
		em.persist(Messenger4);

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
		assertEquals(2,size2);
	}


	@Test
	void getInfoTest() {
		Messenger Messenger1 = new Messenger("Hello","12341","123423");
		Messenger Messenger2 = new Messenger("Bonjour","12341","12333");
		Messenger Messenger3 = new Messenger("Hi","12340","12342");
		Messenger Messenger4 = new Messenger("Hello","12341","12342");

		em.persist(Messenger1);
		em.persist(Messenger2);
		em.persist(Messenger3);
		em.persist(Messenger4);
		List<Object> objects = Messengerserviceimpl.getInfo("12342");
		int size2 = objects.size();
		assertEquals(2,size2);
	}

	@Test
	void seenMessageTest() {
		Messenger Messenger4 = new Messenger("Hellow","3234","3235");
		em.persist(Messenger4);
		List<Messenger> messengers = Messengerserviceimpl.getMessenger("3234", "3235");
		Messenger messageToChange = messengers.get(0);
		messageToChange.setSeenReceive(false);
		Messengerserviceimpl.seenMessage(messageToChange);
		boolean answer = false;
		List<Messenger> messengers2 = Messengerserviceimpl.getMessenger("3234", "3235");
		for (Messenger m : messengers2) {
			if (m.getMsg() == messageToChange.getMsg()){
				answer = m.getSeenReceive();
			}
		}
	assertEquals(false,answer);
	}

}
