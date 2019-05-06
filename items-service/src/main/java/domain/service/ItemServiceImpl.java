package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import domain.model.Item;

@ApplicationScoped
@Transactional
@Default

public class ItemServiceImpl implements ItemService {

	@PersistenceContext(unitName="ItemsPU")
	private EntityManager em;
	
	@Override
	public List<Item> getBySearch(String keyword, String category, int state, int sprize, int fprize, int p) {
		List<Item> items;
		keyword = "%"+keyword.replace(" ","%")+"%";
		if (category.equals("all")) {
			items = em.createQuery(	"SELECT a FROM Item a"
								+ 	" WHERE (a.name LIKE :keyword OR a.description LIKE :keyword)"
								+	" AND a.state >= :state "
								+	" AND a.prize >=:sprize"
								+	" AND a.prize <=:fprize"
								, Item.class).setParameter("keyword", keyword).setParameter("state", state).setParameter("sprize", sprize).setParameter("fprize", fprize).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
		} else {
			items = em.createQuery(	"SELECT a FROM Item a"
							+ 	" WHERE (a.name LIKE :keyword OR a.description LIKE :keyword)"
							+ 	" AND a.category = :category "
							+	" AND a.state >= :state "
							+	" AND a.prize >= :sprize"
							+	" AND a.prize <= :fprize"
							, Item.class).setParameter("keyword", keyword).setParameter("category",category).setParameter("state", state).setParameter("sprize", sprize).setParameter("fprize", fprize).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
		}
		return items;
	}
	

	@Override
	public List<Item> getHighlight(String user) { 
		return em.createQuery("FROM Item", Item.class).getResultList();
	}
	
	@Override
	public List<Item> getAll() { 
		return em.createQuery("FROM Item", Item.class).getResultList();
	}

//	@Override
//	public boolean addItems() {
//		Item item1 = new Item("1","velofm electrique",200, "mobilier", "papapa",3);
//		Item item2 = new Item("2","gpomtvelosnf",50,"kayak", "pfonf", 3);
//		Item item3 = new Item("3","vtt",400, "velo", "fvelgnfo", 3);
//		Item item4 = new Item("4","sofa",600, "velo", "lrlmvelo moteur", 3);
//		em.persist(item1);
//		em.persist(item2);
//		em.persist(item3);
//		em.persist(item4);
//		return true;
//	}


	@Override
	public void addItem(Item item) {
		em.persist(item);
	}


	@Override
	public void removeItem(String itemid) {
		Query query = em.createQuery("DELETE FROM Item c WHERE c.id = :p ");
		query.setParameter("p", itemid).executeUpdate();
	}


	@Override
	public int updateItem(String itemId, String field, String change) {
		switch (field) {
			case "name":
				Query query = em.createQuery(
					      "UPDATE Item c SET c." + field + " = :change " + 
					      "WHERE c.id = :itemid");
					  query.setParameter("itemid", itemId).setParameter("change",  change).executeUpdate();
					  return 0;
			case "category":
				Query query2 = em.createQuery(
						"UPDATE Item c SET c." + field + " = :change " + 
					      "WHERE c.id = :itemid");
					  query2.setParameter("itemid", itemId).setParameter("change",  change).executeUpdate();
					  return 0;
			case "state":
				Query query3 = em.createQuery(
						"UPDATE Item c SET c." + field + " = :change " + 
					      "WHERE c.id = :itemid");
					  query3.setParameter("itemid", itemId).setParameter("change",  Integer.parseInt(change)).executeUpdate();
					  return 0;
			case "description":
				Query query4 = em.createQuery(
						"UPDATE Item c SET c." + field + " = :change " + 
					      "WHERE c.id = :itemid");
					  query4.setParameter("itemid", itemId).setParameter("change",  change).executeUpdate();
					  return 0;
			case "prize":
				Query query5 = em.createQuery(
						"UPDATE Item c SET c." + field + " = :change " + 
					      "WHERE c.id = :itemid");
					  query5.setParameter("itemid", itemId).setParameter("change",  Integer.parseInt(change)).executeUpdate();
					  return 0;
			default:
				return 1;
		}
	}


	@Override
	public List<Item> getItem(String usrID) {
		List<Item> items;
		items = em.createQuery(	"SELECT a FROM Item AS a"
				+ 	" WHERE a.usrId = :userid"
				, Item.class).setParameter("userid", usrID).getResultList();
		return items;
	}



}
