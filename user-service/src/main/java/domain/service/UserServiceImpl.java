package domain.service;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import api.msg.UserProducer;
import domain.model.Users;

@Dependent
public class UserServiceImpl implements UserService {
	
	String whereId = "WHERE a.id = :id";

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
		if (!em.contains(us)) {
		userproducer.sendUserbyid(us.getId(), "adduser");
		em.persist(us);
		return us.getId();
		}else {
			if (us.getName() == null) {
				Query query = em.createQuery(
						"UPDATE Users a SET a.name = :name, a.surname = :surname, a.email = :email "+
						whereId);
				query.setParameter("id", us.getId()).setParameter("email", us.getEmail()).setParameter("name", us.getName()).setParameter("surname", us.getSurname()).executeUpdate();
				return "update user";
			}
		}
		return "user already exist";
		}

	@Override
	public Users getByIdUser(String id) {
		List<Users> users = em.createQuery("SELECT a FROM Users a WHERE a.id = :id", Users.class).setParameter("id",id).getResultList();
		if (!(users.isEmpty())) {
			return users.get(0);
		}else {
			return new Users("0000","",0);
		}
	}


	@Override
	public String incrementReport(String id,String idreport) {
		Users u = getByIdUser(id);
		if (u.getId().equals("0000")) {
			return "user not found";
		}else {
			String report = "";
			report = u.getUserReport() + idreport + " ";
			if (!(u.getUserReport().contains(idreport))){
				Query query = em.createQuery(
						"UPDATE Users a SET a.userReport = :report , a.report = :nbreport " +
							 whereId);
				query.setParameter("id", id).setParameter("report",report).setParameter("nbreport", u.getReport() + 1).executeUpdate();
				return "incremented report";
			}else {
				return "user has already report";
			}
		}
	}

	@Override
	public String updateImage(Users us) {
		Users u = getByIdUser(us.getId());
		if (!(u.getId().contentEquals("0000"))) {
			Query query = em.createQuery(
					"UPDATE Users a SET a.image = :image " +
					whereId);
			query.setParameter("id", us.getId()).setParameter("image",us.getImage()).executeUpdate();
			return "Image changed";
		} else {
			em.persist(us);
			return "user added and image updated";
		}
	}




}
