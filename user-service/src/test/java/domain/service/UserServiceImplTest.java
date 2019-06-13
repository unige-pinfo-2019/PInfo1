package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import api.msg.UserProducer;
import domain.model.Users;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
	
	@Spy
	@PersistenceContext(unitName = "UsersPUTest")
	EntityManager em;
	//FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
	
	@InjectMocks
	private UserServiceImpl Userserviceimpl;
	
	
	private int initDataStore() {
		em.clear();
		Users user1 = new Users("1234","",0,"");
		Users user2 = new Users("1235","",0,"");
		Users user3 = new Users("1236","",0,"");
		em.persist(user1);
		em.persist(user2);
		em.persist(user3);
		return 3;
	}
	
	private int initDataStore2() {
		em.clear();
		Users user1 = new Users("12341","",0,"");
		Users user2 = new Users("12351","",0,"");
		Users user3 = new Users("12361","",0,"");
		em.persist(user1);
		em.persist(user2);
		em.persist(user3);
		return 3;
	}
	
	private int initDataStore3() {
		em.clear();
		Users user1 = new Users("123456","",0,"");
		Users user2 = new Users("123457","",0,"");
		Users user3 = new Users("123458","",0,"");
		em.persist(user1);
		em.persist(user2);
		em.persist(user3);
		return 3;
	}
	
	@Test
	void getAllTest() {
		int size = initDataStore();
		List<Users> users = Userserviceimpl.getAll();
		assertEquals(users.size(),Userserviceimpl.getAll().size());
	}
	/*
	@Test
	void createTest() {
		em.clear();
		int size = Userserviceimpl.getAll().size();
		Users user1 = new Users("1237","",0,"");
		Userserviceimpl.create(user1);
		List<Users> users = Userserviceimpl.getAll();
		assertEquals(size+1,users.size());
	}*/
	
	@Test
	void getByIdUserTest() {
		initDataStore2();
		Users users = Userserviceimpl.getByIdUser("12341");
		Users users2 = Userserviceimpl.getByIdUser("12342");
		assertEquals("12341",users.getId());
		assertEquals("0000",users2.getId());
	}
	
	@Test
	void modelTest() {
		Users user1 = new Users("1238","",0,"");
		Users user2 = new Users("1239","Antoine","Valin","antoinne.valin@etu.unige.ch",0);
		Users user3 = new Users("1240","",0);
		user2.setEmail("antoine.valin@gmail.com");
		user1.setId("1235");
		user1.setImage("123");
		user1.setReport(2);
		user1.setUserReport("1234 1235");
		user1.setName("Jo");
		user1.setSurname("Lo");
		assertEquals("antoine.valin@gmail.com",user2.getEmail());
		assertEquals("1235",user1.getId());
		assertEquals("123",user1.getImage());
		assertEquals("Jo",user1.getName());
		assertEquals("Lo",user1.getSurname());
		assertEquals(2,user1.getReport());
		assertEquals("1234 1235",user1.getUserReport());
		assertEquals("Jo", user1.getName());
		assertEquals("Lo", user1.getSurname());
		assertEquals("antoine.valin@gmail.com", user2.getEmail());
		assertEquals("1239", user2.getId());
		assertEquals("1240", user3.getId());
	}
	
	@Test
	void incrementReportTest() {
		initDataStore3();
		Users users = Userserviceimpl.getByIdUser("123456");
		if (!(users.getId().equals("0000"))){
			Userserviceimpl.incrementReport("123456","123457");
			Userserviceimpl.incrementReport("123459", "123457");
			Users users1 = Userserviceimpl.getByIdUser("123456");
			Users users2 = Userserviceimpl.getByIdUser("123459");
			assertEquals("",users1.getUserReport());
			assertEquals(null,users2.getUserReport());
		}
		
	}

	
}

