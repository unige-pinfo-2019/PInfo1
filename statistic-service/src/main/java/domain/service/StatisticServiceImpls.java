package domain.service;

import java.util.ArrayList;
import java.util.Collections;
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



@ApplicationScoped
@Transactional
@Default
public class StatisticServiceImpls  implements StatisticService {

	@PersistenceContext(unitName="StatisticPU")
	private EntityManager em;
	
	@Override
	public List<String> MostSearchCategories(String userId) {
		List<StatisticUser> sul = em.createQuery("SELECT a FROM StatisticUser a WHERE a.userId = :userId",StatisticUser.class).setParameter("userId", userId).getResultList();
		StatisticUser su = sul.get(0);
		Map<Long,String> map = new TreeMap<Long,String>();
		ArrayList<Long> l = new ArrayList<Long>();
		map.put(su.getnClicsLivres(),"livre"); l.add(su.getnClicsLivres());
		map.put(su.getnClicsElectronique(),"electronique");l.add(su.getnClicsElectronique());
		map.put( su.getnClicsMobilier(),"mobilier");l.add(su.getnClicsMobilier());
		map.put(su.getnClicsMobilite(),"mobilite");l.add(su.getnClicsMobilite());
		map.put(su.getnClicsNotes(),"notes");l.add(su.getnClicsNotes());
		Collections.sort(l);
		Collections.reverse(l);
		List<String> res = new ArrayList<String>();
	    Iterator iterator = l.iterator();
	    int k = 0;
	    String toadd = " ";
	    Random r = new Random();
	    List<String> check = new ArrayList<String> ();
	    check.add("livre");check.add("electronique");check.add("mobilier");check.add("mobilite");check.add("notes");
		while (iterator.hasNext()) {
			toadd = map.get((iterator.next()));
			while (res.contains(toadd)) {
				toadd = check.get(r.nextInt(4-0));
			}
			res.add(toadd);
			k = k+1;
			if (k == 3) {
				return res;
			}
			
		}
		return res;
	}

	@Override
	public List<String> MostSearchItems() {
		List<StatisticItem> si = em.createQuery("SELECT a FROM StatisticItem a ORDER BY a.nClicsItem DESC", StatisticItem.class).setMaxResults(5).getResultList();
		List<String> res = new ArrayList<String>();
		Iterator<StatisticItem> i = si.iterator();
		int k = 0;
		while (i.hasNext()) {
			res.add(i.next().getItemId());
			k = k+1;
			if (k == 5) {
				return res;
			}
		}
		return res;
	}

	@Override
	public int IncrementCategory(String userId, String category) {
		String nClicsCategorie = null ;
		switch (category) {
		case "Livres":
			nClicsCategorie = "nClicsLivres" ;
			break;
		case "Mobilite":
			nClicsCategorie = "nClicsMobilite" ;
			break;
		case "Mobilier":
			nClicsCategorie = "nClicsMobilier" ;
			break;
		case "Electronique":
			nClicsCategorie = "nClicsElectronique" ;
			break;
		case "Notes":
			nClicsCategorie = "nClicsNotes" ;
			break;
		default:
			break;
		}
		Query q = em.createQuery(	"UPDATE StatisticUser c SET c."+ nClicsCategorie +" = c."+ nClicsCategorie +" + 1 WHERE userId = :userId") ;
		q.setParameter("userId", userId).executeUpdate();
		return 0;
	}

	@Override
	public int IncrementItems(String itemId) {
		Query q = em.createQuery(	"UPDATE StatisticItem SET nClicsItem = nClicsItem+1 WHERE itemId = :itemId") ;
		q.setParameter("itemId", itemId).executeUpdate();	
		return 0;
	}

	@Override
	public void additem(String id) {
		StatisticItem i1 = new StatisticItem(id,0);
		em.persist(i1);
	}

	@Override
	public void removeitem(String itemId) {
		Query query = em.createQuery("DELETE FROM StatisticItem c WHERE c.id = :p ");
		query.setParameter("p", itemId).executeUpdate();
		
	}

	@Override
	public void addUser(String usrid) {
		StatisticUser u1 = new StatisticUser(usrid, 0,0,0,0,0);
		em.persist(u1);
		
	}

	@Override
	public void removeUser(String usrid) {
		Query query = em.createQuery("DELETE FROM StatisticUser c WHERE c.id = :p ");
		query.setParameter("p", usrid).executeUpdate();
	}

	
	
}
