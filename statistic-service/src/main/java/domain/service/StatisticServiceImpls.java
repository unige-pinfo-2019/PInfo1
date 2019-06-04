package domain.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
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

	private static final String LIVRESLAB = "nClicsLivres";
	private static final String MOBILITELAB = "nClicsMobilite";
	private static final String MOBILIERLAB = "nClicsMobilier";
	private static final String ELECTRONIQUELAB = "nClicsElectronique";
	private static final String NOTESLAB = "nClicsNotes";
	private static final String AUTRELAB = "nClicsAutre" ;
	
	
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
	public void removeUserStats(String usrId) {
		em.createQuery(	"DELETE FROM StatisticUser WHERE userId = :usrid").setParameter("usrid", usrId).executeUpdate();
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
			nClicsCategorie = LIVRESLAB ;
			break;
		case MOBILITE:
			nClicsCategorie = MOBILITELAB ;
			break;
		case MOBILIER:
			nClicsCategorie = MOBILITELAB ;
			break;
		case ELECTRONIQUE:
			nClicsCategorie = ELECTRONIQUELAB ;
			break;
		case NOTES:
			nClicsCategorie = NOTESLAB ;
			break;
		case AUTRE:
			nClicsCategorie = AUTRELAB ;
			break;
		default:
			return;
		}
		Query q = em.createQuery(	"UPDATE StatisticUser SET " + nClicsCategorie + " = " + nClicsCategorie + "+1 WHERE userId = :usrid") ;
		if (q != null)
			q.setParameter("usrid", userId).executeUpdate();
	}
	
	@Override
	public void incrementItem(String itemId) {
		Query q = em.createQuery(	"UPDATE StatisticItem SET nClicsItem = nClicsItem+1 WHERE itemId = :itemid") ;
		q.setParameter("itemid", itemId).executeUpdate();	
	}

	@Override
	public SortedMap<Categorie, Long> getUserHighlights(String usrId, int n) {		//retourne les n catégories les + recherchées par cet utilisateur, triées par ordre croissant de nb de recherches
		return getCategories(usrId, n, false) ;
	}
	
	@Override
	public SortedMap<Categorie, Long> getCategoryHighlights(int n) {		//retourne les n catégories les + recherchées de façon générale
		return getCategories("", n, true) ;
	}

	@Override
	public SortedMap<String, Long> getCategoryItemHighlights(Categorie categorie, int n) {		//retourne les n items les + recherchés dans cette catégorie
		if (n < 1 || n > 6)
			return new TreeMap<> () ;
		return getItems(categorie, n, false) ;
	}

	@Override
	public SortedMap<String, Long> getItemHighlights(int n) {		//retourne les n items les + recherchés de façon générale
		if (n < 1 || n > 6)
			return new TreeMap<> () ;
		return getItems(null, n, true) ;
	}

	private SortedMap<Categorie, Long> getCategories(String usrId, int n, boolean isGeneral) {
		String[] cols = {MOBILITELAB, MOBILIERLAB, ELECTRONIQUELAB, NOTESLAB, LIVRESLAB, AUTRELAB} ;
		List<Categorie> categories = new ArrayList<> () ;
		long[] nClics = new long[cols.length] ;
		for (int i = 0 ; i < cols.length ; i++) {
			Query q = null;
			switch(cols[i]) {
			case MOBILITELAB:
				if (!isGeneral)
					q = em.createQuery(" SELECT nClicsMobilite FROM StatisticUser WHERE userId = :usrid", Long.class) ;
				else
					q = em.createQuery(" SELECT SUM(s.nClicsMobilite) FROM StatisticUser s", Long.class) ;
				categories.add(Categorie.MOBILITE) ;
				break;
			case MOBILIERLAB:
				if (!isGeneral)
					q = em.createQuery(" SELECT nClicsMobilier FROM StatisticUser WHERE userId = :usrid", Long.class) ;
				else
					q = em.createQuery(" SELECT SUM(s.nClicsMobilier) FROM StatisticUser s", Long.class) ;
				categories.add(Categorie.MOBILIER) ;
				break;
			case ELECTRONIQUELAB:
				if (!isGeneral)
					q = em.createQuery(" SELECT nClicsElectronique FROM StatisticUser WHERE userId = :usrid", Long.class) ;
				else
					q = em.createQuery(" SELECT SUM(s.nClicsElectronique) FROM StatisticUser s", Long.class) ;
				categories.add(Categorie.ELECTRONIQUE) ;
				break;
			case NOTESLAB:
				if (!isGeneral)
					q = em.createQuery(" SELECT nClicsNotes FROM StatisticUser WHERE userId = :usrid", Long.class) ;
				else
					q = em.createQuery(" SELECT SUM(s.nClicsNotes) FROM StatisticUser s", Long.class) ;
				categories.add(Categorie.NOTES) ;
				break;
			case LIVRESLAB:
				if (!isGeneral)
					q = em.createQuery(" SELECT nClicsLivres FROM StatisticUser WHERE userId = :usrid", Long.class) ;
				else
					q = em.createQuery(" SELECT SUM(s.nClicsLivres) FROM StatisticUser s", Long.class) ;
				categories.add(Categorie.LIVRES) ;
				break;
			case AUTRELAB:
				if (!isGeneral)
					q = em.createQuery(" SELECT nClicsAutre FROM StatisticUser WHERE userId = :usrid", Long.class) ;
				else
					q = em.createQuery(" SELECT SUM(s.nClicsAutre) FROM StatisticUser s", Long.class) ;
				categories.add(Categorie.AUTRE) ;
				break;
			default:
				return new TreeMap<> () ;
			}
			if (q != null) {
				if (!isGeneral)
					nClics[i] = (long)q.setParameter("usrid", usrId).getSingleResult() ;
				else
					nClics[i] = (long)q.getSingleResult() ;
			}
		}
		
		SortedMap<Categorie, Long> map = new TreeMap<> () ;
		for (int i = 0 ; i < cols.length ; i++)
			map.put(categories.get(i), nClics[i]) ;
		
		SortedMap<Categorie, Long> highlights = new TreeMap<> (new Comparator<Categorie> () {		//pour trier les catégories par ordre croissant de nb de recherches

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
	
	private SortedMap<String, Long> getItems(Categorie categorie, int n, boolean isGeneral) {
		List<StatisticItem> items = null ;
		if (isGeneral)
			items = em.createQuery("SELECT a FROM StatisticItem a ORDER BY a.nClicsItem DESC", StatisticItem.class).setMaxResults(n).getResultList();
		else {
			Query q = em.createQuery("SELECT a FROM StatisticItem a WHERE a.category = :categorie ORDER BY a.nClicsItem DESC", StatisticItem.class);
			items = q.setParameter("categorie", categorie).setMaxResults(n).getResultList();
		}
		
		SortedMap<String, Long> map = new TreeMap<> () ;
		for (int i = 0 ; i < items.size() ; i++)
			map.put(items.get(i).getItemId(), items.get(i).getnClicsItem()) ;
		
		SortedMap<String, Long> highlights = new TreeMap<> (new Comparator<String> () {		//pour trier les items par ordre croissant de nb de recherches

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
