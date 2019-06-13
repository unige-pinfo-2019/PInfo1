package domain.service;

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
	
	private final static String WHEREID = "WHERE a.id = :id" ;


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
								WHEREID);
				query.setParameter("id", us.getId()).setParameter("email", us.getEmail()).setParameter("name", us.getName()).setParameter("surname", us.getSurname()).executeUpdate();
				return "update user";
			}
		}
		return "user already exists";
	}

	@Override
	public Optional<Users> getById(String id) {
		List<Users> user = em.createQuery("SELECT a FROM Users a " + WHEREID, Users.class).setParameter("id",id).getResultList();
		if(!user.isEmpty()) {
			return Optional.of(user.get(0));
		}
		return Optional.empty();
	}

	@Override
	public Users getByIdUser(String id) {
		List<Users> users = em.createQuery("SELECT a FROM Users a " + WHEREID, Users.class).setParameter("id",id).getResultList();
		if (!(users.isEmpty())) {
			return users.get(0);
		}else {
			return new Users("0000","",0);
		}
	}


	@SuppressWarnings("unused")
	@Override
	public String incrementReport(String id,String idreport) {
		Users u = getByIdUser(id);
		if (u.getId().equals("0000")) {
			return "user not found";
		}
		else {
			String report = "";
			report = u.getUserReport() + idreport + " ";
			if (!(u.getUserReport().contains(idreport))){
				Query query = em.createQuery(
						"UPDATE Users a SET a.userReport = :report , a.report = :nbreport " +
							 "WHERE a.id = :id");
				query.setParameter("id", id).setParameter("report",report).setParameter("nbreport", u.getReport() + 1).executeUpdate();
				return "incremented report";
			}else {
				return "user has already report";
			}
		}
	}

	@Override
	public String updateImage(String id, String image) {
		Users u = getByIdUser(id);
		if (u != null) {
			Query query = em.createQuery(
					"UPDATE Users a SET a.image = :image " +
					WHEREID);
			query.setParameter("id", id).setParameter("image",image).executeUpdate();
			return "incremented report";
		}
		else {
			Users u1 = new Users(id, image, 0);
			em.persist(u1);
			return "user added and image updated";
		}
	}




}
