package main.java.domain.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import main.java.domain.model.StatistiquesGeneral;
import main.java.domain.model.StatistiquesUser;


@ApplicationScoped
@Transactional
@Default

public class StatistiquesServiceImpl implements StatistiquesService {

	@PersistenceContext(unitName="StatistiquesPU")
	private EntityManager em;
	
	@Override
	public List<StatistiquesUser> getUserStats(String usrId) {		//retourne les stats de l'utilisateur donne
		return em.createQuery(	"SELECT s FROM StatistiquesUser s WHERE s.User_Id = :id", StatistiquesUser.class).setParameter("id", usrId).getResultList() ;
	}
	
	@Override
	public Optional<StatistiquesGeneral> getGeneralStats(String itemId) {		//retourne les stats générales de l'item selectionne
		List<StatistiquesGeneral> stats = em.createQuery(	"SELECT s FROM StatistiquesGeneral s WHERE s.Item_ID = :id", StatistiquesGeneral.class).setParameter("id", itemId).getResultList() ;
		 if (stats.size() > 0)
			return Optional.of(stats.get(0)) ;		//Optional car 'item_id' est la cle primaire de la table 'StatistiquesGeneral'
		else
			return Optional.empty();
	}
	
	@Override
	public List<StatistiquesUser> getAllUser() {
		return em.createQuery("SELECT * FROM StatistiquesUser", StatistiquesUser.class).getResultList();
	}
	
	@Override
	public List<StatistiquesGeneral> getAllGeneral() {
		return em.createQuery("SELECT * FROM StatistiquesGeneral", StatistiquesGeneral.class).getResultList();
	}

	@Override
	public void addUserStats(StatistiquesUser stats) {
		em.persist(stats);
		System.out.println("inserted") ;
	}
	
	@Override
	public void addGeneralStats(StatistiquesGeneral stats) {
		em.persist(stats);
		System.out.println("inserted") ;
	}
	
	
	@Override
	public void clickOnItem(String usrId, String itemId) {		//evenement de clic sur un item par l'utilisateur donne : incremente le nombre de clics par cet utilisateur sur l'item selectionne ainsi que sur la catégorie correspondante
		Query q = em.createQuery(	"UPDATE StatistiquesUser SET nClics_Item = nClics_Item+1, nClics_Categorie = nClics_Categorie+1 WHERE Item_ID :itemid AND User_ID = :usrid", StatistiquesUser.class) ;
		Query q2 = em.createQuery(	"UPDATE StatistiquesGeneral SET nClics_Item = nClics_Item+1 WHERE Item_ID = :itemid", StatistiquesGeneral.class) ;
		Query q3 = em.createQuery(	"UPDATE StatistiquesGeneral SET nClics_Categorie = nClics_Categorie+1 WHERE Categorie = (SELECT Categorie FROM StatistiquesGeneral WHERE Item_ID = :itemid)", StatistiquesGeneral.class) ;
		q.setParameter("itemid", itemId).setParameter("usrid", usrId).executeUpdate();
		q2.setParameter("itemid", itemId).executeUpdate();
		q3.setParameter("itemid", itemId).executeUpdate();
	}
	
	@Override
	public void clickOnItem(String itemId) {		//evenement de clic sur un item par un utilisateur quelconque : incremente le nombre de clics sur l'item selectionne ainsi que sur la categorie correspondante
		Query q = em.createQuery(	"UPDATE StatistiquesGeneral SET nClics_Item = nClics_Item+1, nClics_Categorie = nClics_Categorie+1 WHERE Item_ID = :itemid", StatistiquesGeneral.class) ;
		q.setParameter("itemid", itemId).executeUpdate();
	}
	
	@Override
	public void research(String usrId, String word) {		//evenement de recherche d'un mot par l'utilisateur donne
		Query q = em.createQuery(	"UPDATE StatistiquesUser SET nClics_Mot = nClics_Mot+1 WHERE User_ID = :usrid AND Mot = :mot", StatistiquesUser.class) ;
		Query q2 = em.createQuery(	"UPDATE StatistiquesGeneral SET nClics_Mot = nClics_Mot+1 WHERE Mot = :mot", StatistiquesGeneral.class) ;
		Query q3 = em.createQuery(	"SELECT nClics_Mot FROM StatistiquesUser WHERE User_ID = :usrid AND Mot = :mot)", Long.class) ;
		Query q4 = em.createQuery(	"SELECT nClics_Mot FROM StatistiquesGeneral WHERE Mot = :mot)", Long.class) ;
		
		q.setParameter("usrid", usrId).setParameter("mot", word).executeUpdate();
		q2.setParameter("mot", word).executeUpdate();
		Long nClics = (Long) q3.setParameter("usrid", usrId).setParameter("mot", word).getSingleResult();
		Long nClicsGen = (Long) q4.setParameter("mot", word).getSingleResult();
		Long maxClics = em.createQuery(	"SELECT MAX(nClics_Mot) FROM StatistiquesUser", Long.class).getSingleResult() ;
		Long maxClicsGen = em.createQuery(	"SELECT MAX(nClics_Mot) FROM StatistiquesGeneral", Long.class).getSingleResult() ;
		
		if (nClics > maxClics)
			em.createQuery(	"UPDATE StatistiquesUser SET Mot = (SELECT Mot FROM StatistiquesUser WHERE nClics_Mot = :nclics)", Long.class).setParameter("nclics", nClics).executeUpdate();
		if (nClicsGen > maxClicsGen)
			em.createQuery(	"UPDATE StatistiquesGeneral SET Mot = (SELECT Mot FROM StatistiquesGeneral WHERE nClics_Mot = :nclicsgen)", Long.class).setParameter("nclicsgen", nClicsGen).executeUpdate() ;
		
	}
	
	@Override
	public void research(String word) {		//evenement de recherche d'un mot
		Query q2 = em.createQuery(	"UPDATE StatistiquesGeneral SET nClics_Mot = nClics_Mot+1 WHERE Mot = :mot", StatistiquesGeneral.class) ;
		Query q4 = em.createQuery(	"SELECT nClics_Mot FROM StatistiquesGeneral WHERE Mot = :mot)", Long.class) ;
		
		q2.setParameter("mot", word).executeUpdate();
		Long nClicsGen = (Long) q4.setParameter("mot", word).getSingleResult();
		Long maxClicsGen = em.createQuery(	"SELECT MAX(nClics_Mot) FROM StatistiquesGeneral", Long.class).getSingleResult() ;
		
		if (nClicsGen > maxClicsGen)
			em.createQuery(	"UPDATE StatistiquesGeneral SET Mot = (SELECT Mot FROM StatistiquesGeneral WHERE nClics_Mot = :nclicsgen)", Long.class).setParameter("nclicsgen", nClicsGen).executeUpdate() ;
		
	}

	@Override
	public List<StatistiquesUser> getItemStats(String itemId) {		//retourne les stats de l'item selectionne pour chaque utilisateur
		return em.createQuery(	"SELECT s FROM StatistiquesUser s WHERE s.Item_ID = " + itemId, StatistiquesUser.class).setParameter("id", itemId).getResultList();
	}

	@Override
	public Optional<StatistiquesUser> getUserStats(String usrId, String itemId) {		//retourne les stats de l'utilisateur donne pour l'item selectionne
		List<StatistiquesUser> stats = em.createQuery(	"SELECT s FROM StatistiquesUser s WHERE s.User_ID = :usrid AND s.Item_ID = :itemid", StatistiquesUser.class).setParameter("usrid", usrId).setParameter("itemid", itemId).getResultList();
		if (stats.size() > 0)
			return Optional.of(stats.get(0)) ;		//Optional car 'usr_id' et 'item_id' constituent la cle primaire composite de la table 'StatistiquesUser'
		else
			return Optional.empty();
	}

	@Override
	public void removeUserStats(String usrId, String itemId) {
		em.createQuery(	"DELETE FROM StatistiquesUser WHERE UserID = :usrid AND ItemID = :itemid", StatistiquesUser.class).setParameter("usrid", usrId).setParameter("itemid", itemId).executeUpdate();
		System.out.println("deleted") ;
	}

	@Override
	public void removeGeneralStats(String itemId) {
		em.createQuery(	"DELETE FROM StatistiquesGeneral WHERE ItemID = :itemid", StatistiquesGeneral.class).setParameter("itemid", itemId).executeUpdate();
		System.out.println("deleted") ;
	}

	

}
