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
	public void removeAnnonce(Annonce annonce) {
		Query query = em.createQuery(
				"UPDATE Annonce a SET a.state = :sold" +
				 " WHERE a.id = :wantedid");
		query.setParameter("wantedid", annonce.getId()).setParameter("sold", "true").executeUpdate();
	}

	@Override
	public int updateAnnonce(Annonce annonce) {
		Query query = em.createQuery(
				"UPDATE Annonce a SET a.name = :name , a.category = :category , a.state = :state, a.desc = :description " +
				 "WHERE a.id = :wantedid");
		query.setParameter("wantedid", annonce.getId()).setParameter("name",  annonce.getName()).setParameter("category", annonce.getCategory()).setParameter("state", annonce.getState()).setParameter("description", annonce.getDescription()).executeUpdate();
		return 0;
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
