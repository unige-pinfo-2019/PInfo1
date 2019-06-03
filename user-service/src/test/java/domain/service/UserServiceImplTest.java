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
	
	
	@Test 
	void addUsersTest() {
		int size = initDataStore();
		Userserviceimpl.addUsers();
		int size2 = Userserviceimpl.getAll().size();
		assertEquals(size+3,size2);
	}
	
	@Test 
	void createTest() {
		int size = Userserviceimpl.getAll().size();
		Users User1 = new Users(111111,"jo","lo","fl","jo.lo@uni.ch",3,5);
		Userserviceimpl.create(User1);
		int size2 = Userserviceimpl.getAll().size();
		assertEquals(size+1,size2);
	}
	
	@Test 
	void updateUserTest() {
		Users User1 = new Users("fie" , "fiwb", "doen");
		em.persist(User1);
		User1.setEmail("jl@gmail.com");
		Userserviceimpl.updateUser(User1);
		assertEquals(User1, Userserviceimpl.getById(User1.getId()).get());
	}
	
	@Test 
	void deleteUsersTest() {
		int size = initDataStore();
		Userserviceimpl.addUsers();
		Userserviceimpl.removeUser("1");
		int size2 = Userserviceimpl.getAll().size();
		assertEquals(size+3-1,size2);
		assertEquals("Some form of error occurred. Could not delete 1000000000000",Userserviceimpl.removeUser("1000000000000"));
	}
	
	@Test 
	void modifyUsersTest() {
		Users User1 = new Users(1,"jo","lo","pd","jo.lo@uni.ch",3,0);
		Users User2 = new Users(2,"jo","lo","pd",null,0,0);
		Userserviceimpl.addUsers();
		Userserviceimpl.modifyUser("1","jo","lo","pd","jo.lo@uni.ch",3);
		Userserviceimpl.modifyUser("1000","jo","lo","pd","jo.lo@uni.ch",3);
		Userserviceimpl.modifyUser("2","jo","lo","pd","",0);
		assertEquals(User1, Userserviceimpl.getByNames("jo","lo").get());
		assertEquals(User1, Userserviceimpl.getByNames("pd").get());
		assertEquals(User1, Userserviceimpl.getById(1).get());
		assertEquals(User1, Userserviceimpl.getById2("1").get(0));


		assertEquals(Optional.empty(),Userserviceimpl.getByNames("fnwof", "fjiq"));
		assertEquals(Optional.empty(),Userserviceimpl.getByNames("fnwof"));
		//assertEquals(User1, Userserviceimpl.getById(1).get());
		//assertEquals(User2, Userserviceimpl.getById(2).get());
	}

	
	
	
	@Test
	void getAllTest(){
		int size = initDataStore();
		assertEquals(size, Userserviceimpl.getAll().size());
		
	}
	
		
	@Test
	void ToStringTest() {
		Users User1 = new Users(1,"jo","lo","pd","jo.lo@uni.ch",3,4);
		User1.setId(1);
		String s = "User [id=1, name=jo, surname=lo, username=pd, email=jo.lo@uni.ch, grade=4, report=3]";
		assertEquals(s, User1.toString());
	}
	
	private int initDataStore() {
		int size = Userserviceimpl.getAll().size();
		List<Users> it = getUsers();	
		for (Users c : it) {
			em.persist(c);
		}
		return size + it.size();
	}
	
	
	private List<Users> getUsers() {

		List<Users> it = new ArrayList<>();
		long numberOfCpty = Math.round((Math.random() * 10));
		for (int i = 0; i < numberOfCpty; i++) {
			it.add(createUser());
		}
		return it;
	}


	private Users createUser(){
		Users i = new Users();
		i.setName(UUID.randomUUID().toString());
		i.setSurname(UUID.randomUUID().toString());
		i.setUsername(UUID.randomUUID().toString());
		i.setReport(0 + (int)(Math.random() * ((10 - 0) + 0)));
		return i;
	}
	
	
}

