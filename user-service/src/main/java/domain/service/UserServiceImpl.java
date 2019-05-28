package domain.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.model.Users;

@Dependent
public class UserServiceImpl implements UserService {
	
	@PersistenceContext(unitName="UsersPU")
	private EntityManager em;
	
	
	@Override
	public List<Users> getAll() { 
		return em.createQuery("FROM Users", Users.class).getResultList();
	}
	
	
	@Override
	public boolean addUsers() {
		Users u1 = new Users("Tommy","Peletta","Pigeon");
		Users u2 = new Users("Guillaume","Conte","GuiGui","guillaume.conte@uni.ch",5,3);
		Users u3 = new Users("Adrien","Chabert","BG");
		em.persist(u1);
		em.persist(u2);
		em.persist(u3);
		return true;
	}
	
	@Override
	public Long create(Users us) {
		
		if (em.contains(us)) {
			throw new IllegalArgumentException("User already exists");
		}
		em.merge(us);
		// Sync the transaction to get the newly generated id
		em.flush();
		
		return us.getId();
	}
	
	@Override
	public Optional<Users> getById(long id) {
		List<Users> user = em.createQuery("SELECT a FROM Users a WHERE a.id = :id", Users.class).setParameter("id",id).getResultList();
		if(user.size() > 0) {
			return Optional.of(user.get(0));
		}
		return Optional.empty();
	}
	
	@Override
	public List<Users> getById2(String id) {
		long idlong = Long.parseLong(id);
		return em.createQuery("SELECT a FROM Users a WHERE a.id "
				+ "= :id", Users.class).setParameter("id",idlong).getResultList();
	}
	
	@Override
	public Optional<Users> getByNames(String name, String surname) {
		List<Users> user = em.createQuery("SELECT a FROM Users a WHERE a.name = :name AND a.surname = :surname ", Users.class).setParameter("name",name).setParameter("surname",surname).getResultList();
		if(user.size() > 0) {
			return Optional.of(user.get(0));
		}
		return Optional.empty();
	}
	
	@Override
	public Optional<Users> getByNames(String username) {
		List<Users> user = em.createQuery("SELECT a FROM Users a WHERE a.username = :username", Users.class).setParameter("username",username).getResultList();
		if(user.size() > 0) {
			return Optional.of(user.get(0));
		}
		return Optional.empty();
	}


	@Override
	public String removeUser(String str_id) {
		long id =Long.parseLong(str_id);
		Optional<Users> popt = getById(id);
			if (!popt.isEmpty()) {
				Users user = popt.get();
				em.remove(em.contains(user) ? user : em.merge(user));
				return "Deleted User "+ user.toString();
			} else {
				return "Some form of error occurred. Could not delete " + str_id;
			}
	}


	@Override
	public boolean modifyUser(String strid,String name,String surname,String username,String email,int report) {
		long id =Long.parseLong(strid);
		Optional<Users> popt = getById(id);
		if(popt.isEmpty()) {
			return false;
		}
		Users u = popt.get();
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


	@Override
	public void updateUser(Users us) {
		Query query = em.createQuery(
				"UPDATE Users a SET a.name = :name , a.surname = :surname , a.username = :username, a.email = :email, a.report = :report, a.grade = :grade " +
				 "WHERE a.id = :id");
		query.setParameter("id", us.getId()).setParameter("name", us.getName()).setParameter("surname", us.getSurname()).setParameter("username", us.getUsername()).setParameter("email", us.getEmail()).setParameter("report", us.getReport()).setParameter("grade", us.getGrade()).executeUpdate();
		
	}
	
	
	
}
