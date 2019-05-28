package domain.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import domain.model.StatisticItem;
import domain.model.StatisticUser;
import domain.model.Categorie;



@ApplicationScoped
@Transactional
@Default
public class StatisticServiceImpls  implements StatisticService {

	@PersistenceContext(unitName="StatisticPU")
	private EntityManager em;

	private final static String livresLab = "nClicsLivres", mobiliteLab = "nClicsMobilite", mobilierLab = "nClicsMobilier", electroniqueLab = "nClicsElectronique", notesLab = "nClicsNotes" ;

	
	@Override
	public StatisticItem getItemStats(String itemId) {		//retourne les stats générales de l'item sélectionné
		return em.createQuery(	"SELECT s FROM StatisticItem s WHERE s.itemId = :id", StatisticItem.class).setParameter("id", itemId).getSingleResult() ;
	}

	@Override
	public StatisticUser getUserStats(String usrId) {		//ret ourne les stats de l'utilisateur donné pour l'item sélectionné
		return em.createQuery(	"SELECT s FROM StatisticUser s WHERE s.userId = :usrid", StatisticUser.class).setParameter("usrid", usrId).getSingleResult();
	}
	
	@Override
	public List<StatisticUser> getAllUser() {
		return em.createQuery("SELECT s FROM StatisticUser s", StatisticUser.class).getResultList();
	}
	
	@Override
	public List<StatisticItem> getAllItem() {
		return em.createQuery("SELECT s FROM StatisticItem s ORDER BY s.category", StatisticItem.class).getResultList();
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
		Query q = em.createQuery(	"UPDATE StatisticUser SET " + nClicsCategorie + " = :nclics WHERE userId = :usrid") ;
		q.setParameter("nclics", n).setParameter("usrid", usrId).executeUpdate();
	}

	@Override
	public void setItemStats(String itemId, long n) {
		em.createQuery("UPDATE StatisticItem SET nClicsItem = :nclics WHERE itemId = :itemid").setParameter("nclics", n).setParameter("itemid", itemId).executeUpdate();
	}

	@Override
	public void removeUserStats(String usrId) {
		em.createQuery(	"DELETE FROM StatisticUser WHERE userId = :usrid").setParameter("usrid", usrId).executeUpdate();
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
			q.setParameter("usrid", userId).executeUpdate();
			break;
		case MOBILITE:
			q = em.createQuery(	"UPDATE StatisticUser SET nClicsMobilite = nClicsMobilite+1 WHERE userId = :usrid") ;
			q.setParameter("usrid", userId).executeUpdate();
			break;
		case MOBILIER:
			q = em.createQuery(	"UPDATE StatisticUser SET nClicsMobilier = nClicsMobilier+1 WHERE userId = :usrid") ;
			q.setParameter("usrid", userId).executeUpdate();
			break;
		case ELECTRONIQUE:
			q = em.createQuery(	"UPDATE StatisticUser SET nClicsElectronique = nClicsElectronique+1 WHERE userId = :usrid") ;
			q.setParameter("usrid", userId).executeUpdate();
			break;
		case NOTES:
			q = em.createQuery(	"UPDATE StatisticUser SET nClicsNotes = nClicsNotes+1 WHERE userId = :usrid") ;
			q.setParameter("usrid", userId).executeUpdate();
			break;
		default:
			break;
		}
	}
	
	@Override
	public void incrementItem(String itemId) {
		Query q = em.createQuery(	"UPDATE StatisticItem SET nClicsItem = nClicsItem+1 WHERE itemId = :itemid") ;
		q.setParameter("itemid", itemId).executeUpdate();	
	}

	@Override
	public TreeMap<Categorie, Long> getUserHighlights(String usrId, int n) {		//retourne les n catégories les + recherchées par cet utilisateur, triées par ordre croissant de nb de recherches
		return getCategories(usrId, n, false) ;
	}
	
	@Override
	public TreeMap<Categorie, Long> getCategoryHighlights(int n) {		//retourne les n catégories les + recherchées de façon générale
		return getCategories("", n, true) ;
	}
	

	private TreeMap<Categorie, Long> getCategories(String usrId, int n, boolean isGeneral) {
		String[] cols = {mobiliteLab, mobilierLab, electroniqueLab, notesLab, livresLab} ;
		List<Categorie> categories = new ArrayList<> () ;
		long[] nClics = new long[cols.length] ;
		for (int i = 0 ; i < cols.length ; i++) {
			Query q = null ;
			if (!isGeneral) {
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
					break;
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
				if (q != null) {
					nClics[i] = (long)q.setParameter("usrid", usrId).getSingleResult() ;
				}
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
		
		TreeMap<Categorie, Long> map = new TreeMap<> () ;
		for (int i = 0 ; i < cols.length ; i++)
			map.put(categories.get(i), nClics[i]) ;
		
		TreeMap<Categorie, Long> highlights = new TreeMap<> (new Comparator<Categorie> () {		//pour trier les catégories par ordre croissant de nb de recherches

			@Override
			public int compare(Categorie c1, Categorie c2) {
				return map.get(c1).compareTo(map.get(c2)) > 0 ? -1 : 1 ;
			}
			
		}) ;
		for (int i = 0 ; i < cols.length ; i++)
			highlights.put(categories.get(i), nClics[i]) ;
		
		Iterator<Categorie> it = highlights.keySet().iterator() ;
		int k = 0 ;
		while (it.hasNext()) {
			it.next();
			if (k >= n)
				it.remove();
			k++;
		}
		
		return highlights;
	}

	@Override
	public TreeMap<String, Long> getCategoryItemHighlights(Categorie categorie, int n) {		//retourne les n items les + recherchés dans cette catégorie
		return getItems(categorie, n, false) ;
	}

	@Override
	public TreeMap<String, Long> getItemHighlights(int n) {		//retourne les n items les + recherchés de façon générale
		return getItems(null, n, true) ;
	}
	
	private TreeMap<String, Long> getItems(Categorie categorie, int n, boolean isGeneral) {
		List<StatisticItem> items = null ;
		if (isGeneral)
			items = em.createQuery("SELECT a FROM StatisticItem a ORDER BY a.nClicsItem DESC", StatisticItem.class).setMaxResults(n).getResultList();
		else {
			Query q = em.createQuery("SELECT a FROM StatisticItem a WHERE a.category = :categorie ORDER BY a.nClicsItem DESC", StatisticItem.class);
			items = q.setParameter("categorie", categorie).setMaxResults(n).getResultList();
		}
		
		TreeMap<String, Long> map = new TreeMap<> () ;
		for (int i = 0 ; i < items.size() ; i++)
			map.put(items.get(i).getItemId(), items.get(i).getnClicsItem()) ;
		
		TreeMap<String, Long> highlights = new TreeMap<> (new Comparator<String> () {		//pour trier les items par ordre croissant de nb de recherches

			@Override
			public int compare(String s1, String s2) {
				return map.get(s1).compareTo(map.get(s2)) > 0 ? -1 : 1 ;
			}
			
		}) ;
		for (int i = 0 ; i < items.size() ; i++)
			highlights.put(items.get(i).getItemId(), items.get(i).getnClicsItem()) ;
		
		return highlights;
	}
	
	


}
