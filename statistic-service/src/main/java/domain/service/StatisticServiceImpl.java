package main.java.domain.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import main.java.domain.model.Categorie;
import main.java.domain.model.StatisticItem;
import main.java.domain.model.StatisticUser;


@ApplicationScoped
@Transactional
@Default

public class StatisticServiceImpl implements StatisticService {

	@PersistenceContext(unitName="StatisticPU")
	private EntityManager em;
	
	private final static String livresLab = "nClicsLivres", mobiliteLab = "nClicsMobilite", mobilierLab = "nClicsMobilier", electroniqueLab = "nClicsElectronique", notesLab = "nClicsNotes" ;
	
	@Override
	public StatisticItem getItemStats(String itemId) {		//retourne les stats générales de l'item sélectionné
		return em.createQuery(	"SELECT s FROM StatisticItem s WHERE s.itemId = :id", StatisticItem.class).setParameter("id", itemId).getSingleResult() ;
	}

	@Override
	public StatisticUser getUserStats(String usrId) {		//retourne les stats de l'utilisateur donné pour l'item sélectionné
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
	public void addUserStats(StatisticUser stats) {
		em.persist(stats);
	}
	
	@Override
	public void addItemStats(StatisticItem stats) {
		em.persist(stats);
	}
	
	@Override
	public void setUserStats(String usrId, Categorie categorie, long n) {
		String nClicsCategorie = null ;
		switch (categorie) {
		case LIVRES:
			nClicsCategorie = livresLab ;
			break;
		case MOBILITE:
			nClicsCategorie = mobiliteLab ;
			break;
		case MOBILIER:
			nClicsCategorie = mobilierLab ;
			break;
		case ELECTRONIQUE:
			nClicsCategorie = electroniqueLab ;
			break;
		case NOTES:
			nClicsCategorie = notesLab ;
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
		Query q = null ;
		switch (categorie) {
		case LIVRES:
			q = em.createQuery(	"UPDATE StatisticUser SET nClicsLivres = nClicsLivres+1 WHERE userId = :usrid") ;
			break;
		case MOBILITE:
			q = em.createQuery(	"UPDATE StatisticUser SET nClicsMobilite = nClicsMobilite+1 WHERE userId = :usrid") ;
			break;
		case MOBILIER:
			q = em.createQuery(	"UPDATE StatisticUser SET nClicsMobilier = nClicsMobilier+1 WHERE userId = :usrid") ;
			break;
		case ELECTRONIQUE:
			q = em.createQuery(	"UPDATE StatisticUser SET nClicsElectronique = nClicsElectronique+1 WHERE userId = :usrid") ;
			break;
		case NOTES:
			q = em.createQuery(	"UPDATE StatisticUser SET nClicsNotes = nClicsNotes+1 WHERE userId = :usrid") ;
			break;
		default:
			break;
		}
		q.setParameter("usrid", userId).executeUpdate();
	}
	
	@Override
	public void incrementItem(String itemId) {
		Query q = em.createQuery(	"UPDATE StatisticItem SET nClicsItem = nClicsItem+1 WHERE itemId = :itemid") ;
		q.setParameter("itemid", itemId).executeUpdate();	
	}

	@Override
	public List<Categorie> getUserHighlights(String usrId, int n) {		//retourne les n catégories les + recherchées par cet utilisateur, triées par ordre croissant de nb de recherches
		return getCategories(usrId, n, true) ;
	}
	
	@Override
	public List<Categorie> getHighlights(int n) {		//retourne les n catégories les + recherchées de façon générale
		return getCategories("", n, false) ;
	}
	
	
	
	private List<Categorie> getCategories(String usrId, int n, boolean isGeneral) {
		String[] cols = {mobiliteLab, mobilierLab, electroniqueLab, notesLab, livresLab} ;
		List<Categorie> categories = new ArrayList<> () ;
		long[] nClics = new long[cols.length] ;
		for (int i = 0 ; i < cols.length ; i++) {
			Query q = null ;
			if (isGeneral) {
				switch(cols[i]) {
				case mobiliteLab:
					q = em.createQuery(" SELECT nClicsMobilite FROM StatisticUser WHERE userId = :usrid", Long.class) ;
					categories.add(Categorie.MOBILITE) ;
					break;
				case mobilierLab:
					q = em.createQuery(" SELECT nClicsMobilier FROM StatisticUser WHERE userId = :usrid", Long.class) ;
					categories.add(Categorie.MOBILIER) ;
					break;
				case electroniqueLab:
					q = em.createQuery(" SELECT nClicsElectronique FROM StatisticUser WHERE userId = :usrid", Long.class) ;
					categories.add(Categorie.ELECTRONIQUE) ;
				case notesLab:
					q = em.createQuery(" SELECT nClicsNotes FROM StatisticUser WHERE userId = :usrid", Long.class) ;
					categories.add(Categorie.NOTES) ;
					break;
				case livresLab:
					q = em.createQuery(" SELECT nClicsLivres FROM StatisticUser WHERE userId = :usrid", Long.class) ;
					categories.add(Categorie.LIVRES) ;
					break;
				default:
					break;
				}
				nClics[i] = (long)q.setParameter("usrid", usrId).getSingleResult() ;
			}
			else {
				switch(cols[i]) {
				case mobiliteLab:
					q = em.createQuery(" SELECT SUM(nClicsMobilite) FROM StatisticUser", Long.class) ;
					categories.add(Categorie.MOBILITE) ;
					break;
				case mobilierLab:
					q = em.createQuery(" SELECT SUM(nClicsMobilier) FROM StatisticUser", Long.class) ;
					categories.add(Categorie.MOBILIER) ;
					break;
				case electroniqueLab:
					q = em.createQuery(" SELECT SUM(nClicsElectronique) FROM StatisticUser", Long.class) ;
					categories.add(Categorie.ELECTRONIQUE) ;
					break;
				case notesLab:
					q = em.createQuery(" SELECT SUM(nClicsNotes) FROM StatisticUser", Long.class) ;
					categories.add(Categorie.NOTES) ;
					break;
				case livresLab:
					q = em.createQuery(" SELECT SUM(nClicsLivres) FROM StatisticUser", Long.class) ;
					categories.add(Categorie.LIVRES) ;
					break;
				default:
					break;
				}
				nClics[i] = (long)q.getSingleResult() ;
			}
		}
		
		//tri décroissant du nb de clics et réarrangement des catégories
		for (int i = 0 ; i < cols.length ; i++)
			for (int j = i+1 ; j < cols.length ; j++)
				if (nClics[i] < nClics[j]) {
					long tmp = nClics[i];
					nClics[i] = nClics[j] ;
					nClics[j] = tmp ;
					Categorie tmpC = categories.get(i) ;
					categories.set(i, categories.get(j)) ;
					categories.set(j, tmpC) ;
				}
		
		List<Categorie> highlights = new ArrayList<> () ;
		for (int i = 0 ; i < n ; i++) {
			highlights.add(categories.get(i)) ;
		}
		return highlights ;
	}

	@Override
	public List<Categorie> getItemHighlight(Categorie categorie, int n) {
		return null;
	}

	@Override
	public List<Categorie> getItemHighlights(int n) {
		return null;
	}


}
