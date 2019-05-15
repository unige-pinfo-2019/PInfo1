package main.java.domain.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.NoResultException;
import javax.persistence.EntityExistsException;

import main.java.domain.model.StatisticItem;
import main.java.domain.model.StatisticItem.Categorie;
import main.java.domain.model.StatisticUser;


@ApplicationScoped
@Transactional
@Default

public class StatisticServiceImpl implements StatisticService {

	@PersistenceContext(unitName="StatisticPU")
	private EntityManager em;
	
	
	@Override
	public StatisticItem getItemStats(String itemId) throws NoResultException {		//retourne les stats générales de l'item sélectionné
		return em.createQuery(	"SELECT s FROM StatisticItem s WHERE s.itemId = :id", StatisticItem.class).setParameter("id", itemId).getSingleResult() ;
	}

	@Override
	public StatisticUser getUserStats(String usrId) throws NoResultException {		//retourne les stats de l'utilisateur donné pour l'item sélectionné
		return em.createQuery(	"SELECT s FROM StatisticUser s WHERE s.userId = :usrid", StatisticUser.class).setParameter("usrid", usrId).getSingleResult();
	}
	
	@Override
	public List<StatisticUser> getAllUser() {
		return em.createQuery("SELECT s FROM StatisticUser s", StatisticUser.class).getResultList();
	}
	
	@Override
	public List<StatisticItem> getAllItem() {
		return em.createQuery("SELECT s FROM StatisticItem s", StatisticItem.class).getResultList();
	}

	@Override
	public void addUserStats(StatisticUser stats) throws EntityExistsException {
		em.persist(stats);
	}
	
	@Override
	public void addItemStats(StatisticItem stats) throws EntityExistsException {
		em.persist(stats);
	}
	
	@Override
	public void setUserStats(String usrId, Categorie categorie, long n) {
		String nClicsCategorie = null ;
		switch (categorie) {
		case LIVRES:
			nClicsCategorie = "nClicsLivres" ;
			break;
		case MOBILITE:
			nClicsCategorie = "nClicsMobilite" ;
			break;
		case MOBILIER:
			nClicsCategorie = "nClicsMobilier" ;
			break;
		case ELECTRONIQUE:
			nClicsCategorie = "nClicsElectronique" ;
			break;
		case NOTES:
			nClicsCategorie = "nClicsNotes" ;
			break;
		default:
			break;
		}
		Query q = em.createQuery(	"UPDATE StatisticUser SET :nclicscategorie = :nclics WHERE userId = :usrid") ;
		q.setParameter("nclicscategorie", nClicsCategorie).setParameter("nclics", n).setParameter("usrid", usrId).executeUpdate();
	}

	@Override
	public void setItemStats(String itemId, long n) {
		em.createQuery("UPDATE StatisticItem SET nClicsItem = :nclics WHERE itemId = :itemid").setParameter("nclics", n).setParameter("itemid", itemId).executeUpdate();
	}
	
	
	@Override
	public void clickOnItem(String usrId, String itemId, Categorie categorie) {		//événement de clic sur un item par l'utilisateur donné : incrémente le nombre de clics par cet utilisateur sur l'item sélectionné ainsi que sur la catégorie correspondante
		incrementItem(itemId);
		incrementUser(usrId, categorie);
	}
	
	@Override
	public void clickOnItem(String itemId) {		//événement de clic sur un item par un utilisateur quelconque : incrémente le nombre de clics sur l'item sélectionné
		incrementItem(itemId);
	}
	
	/*
	@Override
	public void research(String usrId, String word) {		//événement de recherche d'un mot par l'utilisateur donné
		Long maxClics = em.createQuery(	"SELECT MAX(nClicsMot) FROM StatisticUser", Long.class).getSingleResult() ;
		Long maxClicsGen = em.createQuery(	"SELECT MAX(nClicsMot) FROM StatisticItem", Long.class).getSingleResult() ;
		
		em.createQuery(	"UPDATE StatisticUser SET nClicsMot = nClicsMot+1 WHERE userId = :usrid AND mot = :word").setParameter("usrid", usrId).setParameter("word", word).executeUpdate() ;
		em.createQuery(	"UPDATE StatisticItem SET nClicsMot = nClicsMot+1 WHERE mot = :word").setParameter("word", word).executeUpdate() ;
		Query q = em.createQuery(	"SELECT nClicsMot FROM StatisticUser WHERE userId = :usrid AND mot = :word", Long.class) ;
		Query q2 = em.createQuery(	"SELECT nClicsMot FROM StatisticItem WHERE mot = :word", Long.class) ;
		
		Long nClics = (Long) q.setParameter("usrid", usrId).setParameter("word", word).getSingleResult();
		Long nClicsGen = (Long) q2.setParameter("word", word).getSingleResult();
		
		if (nClics > maxClics)
			em.createQuery(	"UPDATE StatisticUser SET mot = (SELECT mot FROM StatisticUser WHERE nClicsMot = :nclics) WHERE userId = :userId").setParameter("nclics", nClics).setParameter("userId", usrId).executeUpdate();
		if (nClicsGen > maxClicsGen)
			em.createQuery(	"UPDATE StatisticItem SET mot = (SELECT mot FROM StatisticItem WHERE nClicsMot = :nclicsgen)").setParameter("nclicsgen", nClicsGen).executeUpdate() ;
		
	}
	
	@Override
	public void research(String word) {		//événement de recherche d'un mot par un utilisateur quelconque
		Long maxClicsGen = em.createQuery(	"SELECT MAX(nClicsMot) FROM StatisticItem", Long.class).getSingleResult() ;
		
		em.createQuery(	"UPDATE StatisticItem SET nClicsMot = nClicsMot+1 WHERE mot = :word").setParameter("word", word).executeUpdate() ;
		Query q = em.createQuery(	"SELECT nClicsMot FROM StatisticItem WHERE mot = :word", Long.class) ;
		
		Long nClicsGen = (Long) q.setParameter("word", word).getSingleResult();
		
		if (nClicsGen > maxClicsGen)
			em.createQuery(	"UPDATE StatisticItem SET mot = (SELECT mot FROM StatisticItem WHERE nClicsMot = :nclicsgen)").setParameter("nclicsgen", nClicsGen).executeUpdate() ;
		
	}
	*/

	@Override
	public void removeUserStats(String usrId) {
		em.createQuery(	"DELETE FROM StatisticUser WHERE userId = :usrid AND itemId = :itemid").setParameter("usrid", usrId).executeUpdate();
	}

	@Override
	public void removeItemStats(String itemId) {
		em.createQuery(	"DELETE FROM StatisticItem WHERE itemId = :itemid").setParameter("itemid", itemId).executeUpdate();
	}

	@Override
	public void incrementUser(String userId, Categorie categorie) {
		String nClicsCategorie = null ;
		switch (categorie) {
		case LIVRES:
			nClicsCategorie = "nClicsLivres" ;
			break;
		case MOBILITE:
			nClicsCategorie = "nClicsMobilite" ;
			break;
		case MOBILIER:
			nClicsCategorie = "nClicsMobilier" ;
			break;
		case ELECTRONIQUE:
			nClicsCategorie = "nClicsElectronique" ;
			break;
		case NOTES:
			nClicsCategorie = "nClicsNotes" ;
			break;
		default:
			break;
		}
		Query q = em.createQuery(	"UPDATE StatisticUser SET :nclicscategorie = :nclicscategorie+1 WHERE userId = :usrid") ;
		q.setParameter("nclicscategorie", nClicsCategorie).setParameter("usrid", userId).executeUpdate();
	}
	
	@Override
	public void incrementItem(String itemId) {
		Query q = em.createQuery(	"UPDATE StatisticItem SET nClicsItem = nClicsItem+1 WHERE itemId = :itemid") ;
		q.setParameter("itemid", itemId).executeUpdate();	
	}

	@Override
	public List<Categorie> getHighlight(String usrId, int n) throws NoResultException {		//retourne les n catégories les + recherchées par cet utilisateur, triées par ordre croissant de nb de recherches
		return getCategories(usrId, n, true) ;
	}
	
	@Override
	public List<Categorie> getHighlights(int n) throws NoResultException {		//retourne les n catégories les + recherchées de façon générale
		return getCategories("", n, false) ;
	}
	
	private List<Categorie> getCategories(String usrId, int n, boolean isGeneral) throws NoResultException {
		String[] cols = {"nClicsMobilite", "nClicsMobilier", "nClicsElectronique", "nClicsNotes", "nClicsLivres"} ;
		HashMap<Long, String> mapCols = new HashMap<Long, String> () ;	//pour mapper une catégorie à son nb de clics
		long[] nClics = new long[cols.length] ;
		for (int i = 0 ; i < cols.length ; i++) {
			Query q ;
			if (isGeneral) {
				q = em.createQuery(" SELECT :col FROM StatisticUser WHERE userId = :usrid", Long.class) ;
				q.setParameter("usrid", usrId) ;
			}
			else {
				q = em.createQuery(" SELECT SUM(:col) FROM StatisticUser", Long.class) ;
			}
			nClics[i] = (long)q.setParameter("col", cols[i]).getSingleResult() ;
			mapCols.put(nClics[i], cols[i]) ;
		}
		
		Arrays.sort(nClics);
		List<Categorie> categories = new ArrayList<Categorie> () ;
		for (int i = cols.length-n ; i < cols.length ; i++) {
			int ind = Arrays.asList(cols).indexOf(mapCols.get(nClics[i])) ;
			categories.add(Categorie.values()[ind]) ;
		}
		return categories ;
	}


}
