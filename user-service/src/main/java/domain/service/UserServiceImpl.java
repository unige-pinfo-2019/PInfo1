package domain.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.model.User;

@Dependent
public class UserServiceImpl implements UserService {
	
	@PersistenceContext(unitName="UsersPU")
	private EntityManager em;
	
	
	@Override
	public List<User> getAll() { 
		return em.createQuery("FROM User", User.class).getResultList();
	}
	
	
	@Override
	public boolean addUsers() {
		User u1 = new User("Tommy","Peletta","Pigeon");
		User u2 = new User("Guillaume","Conte","GuiGui","guillaume.conte@uni.ch",5);
		User u3 = new User("Adrien","Chabert","BG");
		em.persist(u1);
		em.persist(u2);
		em.persist(u3);
		return true;
	}
	
	@Override
	public Optional<User> getById(long id) {
		List<User> user = em.createQuery("SELECT a FROM User a WHERE a.id = "+id, User.class).getResultList();
		if(user.size() > 0) {
			return Optional.of(user.get(0));
		}
		return Optional.empty();
	}
	
	@Override
	public Optional<User> getByNames(String name, String surname) {
		List<User> user = em.createQuery("SELECT a FROM User a WHERE a.name = "+name + 
				"AND a.surname = "+surname, User.class).getResultList();
		if(user.size() > 0) {
			return Optional.of(user.get(0));
		}
		return Optional.empty();
	}
	
	@Override
	public Optional<User> getByNames(String username) {
		List<User> user = em.createQuery("SELECT a FROM User a WHERE a.username = "+username, User.class).getResultList();
		if(user.size() > 0) {
			return Optional.of(user.get(0));
		}
		return Optional.empty();
	}


	@Override
	public String removeUser(String str_id) {
		long id =Long.parseLong(str_id);
		Optional<User> popt = getById(id);
		if(popt.isEmpty()) {
			return "Error. There is no User with ID "+ id;
		}
		User user = popt.get();
		try {
			em.remove(em.contains(user) ? user : em.merge(user));
			return "Deleted User "+ user.toString();	
		} catch(IllegalArgumentException ex) {
			System.out.println(ex.toString());
			return "Some form of error occurred. Could not delete "+ user.toString();
		}
	}


	@Override
	public boolean modifyUser(String str_id,String name,String surname,String username,String email,int report) {
		long id =Long.parseLong(str_id);
		Optional<User> popt = getById(id);
		if(popt.isEmpty()) {
			return false;
		}
		User u = popt.get();
		u.setName(name);
		u.setSurname(surname);
		u.setUsername(username);
		if (email.equals("")) 
			email = null;
		u.setEmail(email);
		u.setReport(report);
		
		em.merge(u);
		return true;
	}
	
}
