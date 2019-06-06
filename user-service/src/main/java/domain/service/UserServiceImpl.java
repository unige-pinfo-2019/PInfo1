package domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import api.msg.UserProducer;
import domain.model.Users;

@Dependent
public class UserServiceImpl implements UserService {
	
	@PersistenceContext(unitName="UsersPU")
	private EntityManager em;
	
	@Inject
	private UserProducer userproducer;
	
	
	@Override
	public List<Users> getAll() { 
		return em.createQuery("FROM Users", Users.class).getResultList();
	}
	
		
	@Override
	public String create(Users us) {
		userproducer.sendUserbyid(us.getId(), "adduser");
		em.persist(us);
		return us.getId();
	}
	
	@Override
	public Optional<Users> getById(String id) {
		List<Users> user = em.createQuery("SELECT a FROM Users a WHERE a.id = :id", Users.class).setParameter("id",id).getResultList();
		if(user.size() > 0) {
			return Optional.of(user.get(0));
		}
		return Optional.empty();
	}
	
	public Users getByIdUser(String id) {
		return em.createQuery("SELECT a FROM Users a WHERE a.id = :id", Users.class).setParameter("id",id).getResultList().get(0);
	}
	

	@Override
	public String incrementReport(String id,String idreport) {
		Optional<Users> u = getById(id);
		if (!u.isEmpty()) {
			Users u1 = getByIdUser(id);
			if (!u1.getUserReport().contains(idreport)){
			Query query = em.createQuery(
					"UPDATE Users a SET a.report = a.report+1 " +
					 "WHERE a.id = :id");
			query.setParameter("id", id).executeUpdate();
			return "incremented report";
			}else {
				return "user has already report";
			}
		} else {
			String rep = "";
			rep = rep + idreport;
			Users u1 = new Users(id, "", 1, rep );
			String res = create(u1);
			return res;
		}
	}
	
	@Override
	public String updateImage(String id, String image) {
		Optional<Users> u = getById(id);
		if (!u.isEmpty()) {
			Query query = em.createQuery(
					"UPDATE Users a SET a.report = a.report+1 " +
					"WHERE a.id = :id");
			query.setParameter("id", id).executeUpdate();
			return "incremented report";
		} else {
			Users u1 = new Users(id, image, 0);
			String res = create(u1);
			return res;
		}
	}


	
	
}
