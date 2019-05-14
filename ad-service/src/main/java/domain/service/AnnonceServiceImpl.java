package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


import domain.model.Annonce;

@ApplicationScoped
@Transactional
@Default

public class AnnonceServiceImpl implements AnnonceService {

	@PersistenceContext(unitName="AnnoncesPU")
	private EntityManager em;


	@Override
	public List<Annonce> getAll() {
		return em.createQuery("FROM Annonce", Annonce.class).getResultList();
	}


	@Override
	public void addAnnonce(Annonce annonce) {
		em.persist(annonce);
	}

	@Override
	public void removeAnnonce(String wantedid) {
		Query query = em.createQuery("DELETE FROM Annonce c WHERE c.id = :p ");
		query.setParameter("p", wantedid).executeUpdate();
	}

	@Override
	public int updateAnnonce(String wantedid, String field, String change) {
		switch (field) {
			case "name":
				Query query = em.createQuery(
					      "UPDATE Annonce c SET c." + field + " = :change " +
					      "WHERE c.id = :wantedid");
					  query.setParameter("wantedid", wantedid).setParameter("change",  change).executeUpdate();
					  return 0;
			case "category":
				Query query2 = em.createQuery(
						"UPDATE Annonce c SET c." + field + " = :change " +
					      "WHERE c.id = :wantedid");
					  query2.setParameter("wantedid", wantedid).setParameter("change",  change).executeUpdate();
					  return 0;
			case "state":
				Query query3 = em.createQuery(
						"UPDATE Annonce c SET c." + field + " = :change " +
					      "WHERE c.id = :wantedid");
					  query3.setParameter("wantedid", wantedid).setParameter("change",  Integer.parseInt(change)).executeUpdate();
					  return 0;
			case "description":
				Query query4 = em.createQuery(
					      "UPDATE Annonce c SET c." + field + " = :change " +
					      "WHERE c.id = :wantedid");
					  query4.setParameter("wantedid", wantedid).setParameter("change",  change).executeUpdate();
					  return 0;
			default:
						return 1;
		}
	}



	@Override
	public List<Annonce> getAnnonce(String usrID) {
		List<Annonce> annonces;
		annonces = em.createQuery(	"SELECT a FROM Annonce AS a"
				+ 	" WHERE a.usrId = :userid"
				, Annonce.class).setParameter("userid", usrID).getResultList();
		return annonces;
	}



}
