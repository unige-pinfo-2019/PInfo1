package main.java.domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import main.java.domain.model.StatisticGeneral;
import main.java.domain.model.StatisticUser;


@ApplicationScoped
@Transactional
@Default

public class StatisticServiceImpl implements StatisticService {

	@PersistenceContext(unitName="StatisticPU")
	private EntityManager em;
	
	
	@Override
	public StatisticGeneral getGeneralStats(String itemId) {		//retourne les stats générales de l'item sélectionné
		return em.createQuery(	"SELECT s FROM StatisticGeneral s WHERE s.itemId = :id", StatisticGeneral.class).setParameter("id", itemId).getSingleResult() ;
	}
	
	@Override
	public List<StatisticUser> getItemStats(String itemId) {		//retourne les stats de l'item sélectionné pour chaque utilisateur
		return em.createQuery(	"SELECT s FROM StatisticUser s WHERE s.itemId = :itemid", StatisticUser.class).setParameter("itemid", itemId).getResultList();
	}

	@Override
	public StatisticUser getUserStats(String usrId, String itemId) {		//retourne les stats de l'utilisateur donné pour l'item sélectionné
		return em.createQuery(	"SELECT s FROM StatisticUser s WHERE s.userId = :usrid AND s.itemId = :itemid", StatisticUser.class).setParameter("usrid", usrId).setParameter("itemid", itemId).getSingleResult();
	}
	
	@Override
	public List<StatisticUser> getAllUser() {
		return em.createQuery("SELECT s FROM StatisticUser s", StatisticUser.class).getResultList();
	}
	
	@Override
	public List<StatisticGeneral> getAllGeneral() {
		return em.createQuery("SELECT s FROM StatisticGeneral s", StatisticGeneral.class).getResultList();
	}

	@Override
	public void addUserStats(StatisticUser stats) {
		em.persist(stats);
	}
	
	@Override
	public void addGeneralStats(StatisticGeneral stats) {
		em.persist(stats);
	}
	
	
	@Override
	public void clickOnItem(String usrId, String itemId) {		//événement de clic sur un item par l'utilisateur donné : incrémente le nombre de clics par cet utilisateur sur l'item sélectionné ainsi que sur la catégorie correspondante
		Query q = em.createQuery(	"UPDATE StatisticUser SET nClicsItem = nClicsItem+1, nClicsCategorie = nClicsCategorie+1 WHERE itemId :itemid AND userId = :usrid") ;
		Query q2 = em.createQuery(	"UPDATE StatisticGeneral SET nClicsItem = nClicsItem+1 WHERE itemId = :itemid") ;
		Query q3 = em.createQuery(	"UPDATE StatisticGeneral SET nClicsCategorie = nClicsCategorie+1 WHERE categorie = (SELECT categorie FROM StatisticGeneral WHERE itemId = :itemid)") ;
		q.setParameter("itemid", itemId).setParameter("usrid", usrId).executeUpdate();
		q2.setParameter("itemid", itemId).executeUpdate();
		q3.setParameter("itemid", itemId).executeUpdate();
	}
	
	@Override
	public void clickOnItem(String itemId) {		//événement de clic sur un item par un utilisateur quelconque : incrémente le nombre de clics sur l'item sélectionné ainsi que sur la catégorie correspondante
		Query q = em.createQuery(	"UPDATE StatisticGeneral SET nClicsItem = nClicsItem+1, nClicsCategorie = nClicsCategorie+1 WHERE itemId = :itemid") ;
		q.setParameter("itemid", itemId).executeUpdate();
	}
	
	@Override
	public void research(String usrId, String word) {		//événement de recherche d'un mot par l'utilisateur donné
		Long maxClics = em.createQuery(	"SELECT MAX(nClicsMot) FROM StatisticUser", Long.class).getSingleResult() ;
		Long maxClicsGen = em.createQuery(	"SELECT MAX(nClicsMot) FROM StatisticGeneral", Long.class).getSingleResult() ;
		
		em.createQuery(	"UPDATE StatisticUser SET nClicsMot = nClicsMot+1 WHERE userId = :usrid AND mot = :word").setParameter("usrid", usrId).setParameter("word", word).executeUpdate() ;
		em.createQuery(	"UPDATE StatisticGeneral SET nClicsMot = nClicsMot+1 WHERE mot = :word").setParameter("word", word).executeUpdate() ;
		Query q = em.createQuery(	"SELECT nClicsMot FROM StatisticUser WHERE userId = :usrid AND mot = :word)", Long.class) ;
		Query q2 = em.createQuery(	"SELECT nClicsMot FROM StatisticGeneral WHERE mot = :word)", Long.class) ;
		
		Long nClics = (Long) q.setParameter("usrid", usrId).setParameter("word", word).getSingleResult();
		Long nClicsGen = (Long) q2.setParameter("word", word).getSingleResult();
		
		if (nClics > maxClics)
			em.createQuery(	"UPDATE StatisticUser SET mot = (SELECT mot FROM StatisticUser WHERE nClicsMot = :nclics) WHERE userId = :userid").setParameter("nclics", nClics).setParameter("userid", usrId).executeUpdate();
		if (nClicsGen > maxClicsGen)
			em.createQuery(	"UPDATE StatisticGeneral SET mot = (SELECT mot FROM StatisticGeneral WHERE nClicsMot = :nclicsgen)").setParameter("nclicsgen", nClicsGen).executeUpdate() ;
		
	}
	
	@Override
	public void research(String word) {		//événement de recherche d'un mot par un utilisateur quelconque
		Long maxClicsGen = em.createQuery(	"SELECT MAX(nClicsMot) FROM StatisticGeneral", Long.class).getSingleResult() ;
		
		em.createQuery(	"UPDATE StatisticGeneral SET nClicsMot = nClicsMot+1 WHERE mot = :word").setParameter("word", word).executeUpdate() ;
		Query q = em.createQuery(	"SELECT nClicsMot FROM StatisticGeneral WHERE mot = :word)", Long.class) ;
		
		Long nClicsGen = (Long) q.setParameter("word", word).getSingleResult();
		
		if (nClicsGen > maxClicsGen)
			em.createQuery(	"UPDATE StatisticGeneral SET mot = (SELECT mot FROM StatisticGeneral WHERE nClicsMot = :nclicsgen)").setParameter("nclicsgen", nClicsGen).executeUpdate() ;
		
	}


	@Override
	public void removeUserStats(String usrId, String itemId) {
		em.createQuery(	"DELETE FROM StatisticUser WHERE UserID = :usrid AND ItemID = :itemid").setParameter("usrid", usrId).setParameter("itemid", itemId).executeUpdate();
	}

	@Override
	public void removeGeneralStats(String itemId) {
		em.createQuery(	"DELETE FROM StatisticGeneral WHERE ItemID = :itemid").setParameter("itemid", itemId).executeUpdate();
	}

	

}
