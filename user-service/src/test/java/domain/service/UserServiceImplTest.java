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
	
	@Test 
	void getAllTest() {
		int size = initDataStore();
		List<Users> users = Userserviceimpl.getAll();
		assertEquals(users.size(),size);
	}
	
//	@Test 
//	void createTest() {
//		em.clear();
//		int size = Userserviceimpl.getAll().size();
//		Users user1 = new Users("1237","",0,"");
//		Userserviceimpl.create(user1);
//		List<Users> users = Userserviceimpl.getAll();
//		assertEquals(size+1,users.size());
//	}
	

	
}

