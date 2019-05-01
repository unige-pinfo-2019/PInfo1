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

import domain.model.User;
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
	
	
	@Test 
	void addUsersTest() {
		int size = initDataStore();
		Userserviceimpl.addUsers();
		int size2 = Userserviceimpl.getAll().size();
		assertEquals(size+3,size2);
	}
	
	@Test 
	void deleteUsersTest() {
		int size = initDataStore();
		Userserviceimpl.addUsers();
		Userserviceimpl.removeUser("1");
		int size2 = Userserviceimpl.getAll().size();
		assertEquals(size+3-1,size2);
	}
	
	@Test 
	void modifyUsersTest() {
		User User1 = new User(1,"jo","lo","pd","jo.lo@uni.ch",3);
		Userserviceimpl.addUsers();
		Userserviceimpl.modifyUser("1","jo","lo","pd","jo.lo@uni.ch",3);
		assertEquals(User1, Userserviceimpl.getById(1).get());
	}
	
	
	@Test
	void getAllTest(){
		int size = initDataStore();
		assertEquals(size, Userserviceimpl.getAll().size());
		
	}
	
		
	@Test
	void ToStringTest() {
		User User1 = new User(1,"jo","lo","pd","jo.lo@uni.ch",3);
		User1.setId(1);
		String s = "User [id=1, name=jo, surname=lo, username=pd, email=jo.lo@uni.ch, report=3]";
		assertEquals(s, User1.toString());
	}
	
	private int initDataStore() {
		int size = Userserviceimpl.getAll().size();
		List<User> it = getUsers();	
		for (User c : it) {
			em.persist(c);
		}
		return size + it.size();
	}
	
	
	private List<User> getUsers() {

		List<User> it = new ArrayList<>();
		long numberOfCpty = Math.round((Math.random() * 10));
		for (int i = 0; i < numberOfCpty; i++) {
			it.add(createUser());
		}
		return it;
	}


	private User createUser(){
		User i = new User();
		i.setName(UUID.randomUUID().toString());
		i.setSurname(UUID.randomUUID().toString());
		i.setUsername(UUID.randomUUID().toString());
		i.setReport(0 + (int)(Math.random() * ((10 - 0) + 0)));
		return i;
	}
	
	
}

