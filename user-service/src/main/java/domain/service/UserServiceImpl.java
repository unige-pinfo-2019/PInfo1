package domain.service;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		User u2 = new User("Guillaume","Conte","GuiGui",5);
		User u3 = new User("Adrien","Chabert","BG");

		em.persist(u1);
		em.persist(u2);
		em.persist(u3);
		return true;
	}
	
}
