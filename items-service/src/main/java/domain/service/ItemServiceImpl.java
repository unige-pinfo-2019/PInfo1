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
	
//	enum State{
//		Neuf,
//		Bon_Etat,
//		Abime
//	}
	
//	@Override
//	public List<Item> getBySearch(String keyword, String category, State state, int sprice, int fprice, int p) {
//		List<Item> items;
//		if (keyword != null) {
//			keyword = keyword.toUpperCase();
//			keyword = "%"+keyword.replace(" ","%")+"%";
//		}else {
//			keyword = "%";
//		}
//		
//		if (category.equals("all")) {
//			items = em.createQuery(	"SELECT a FROM Item a"
//								+ 	" WHERE (UPPER(a.name) LIKE :keyword OR UPPER(a.description) LIKE :keyword)"
//								+	" AND a.state >= :state "
//								+	" AND a.price >=:sprice"
//								+	" AND a.price <=:fprice"
//								, Item.class).setParameter("keyword", keyword).setParameter("state", state).setParameter("sprice", sprice).setParameter("fprice", fprice).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
//		} else {
//			items = em.createQuery(	"SELECT a FROM Item a"
//							+ 	" WHERE (UPPER(a.name) LIKE :keyword OR UPPER(a.description) LIKE :keyword)"
//							+ 	" AND a.category = :category "
//							+	" AND a.state >= :state "
//							+	" AND a.price >= :sprice"
//							+	" AND a.price <= :fprice"
//							, Item.class).setParameter("keyword", keyword).setParameter("category",category).setParameter("state", state).setParameter("sprice", sprice).setParameter("fprice", fprice).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
//		}
//		return items;
//	}
	
	@Override
	public List<Item> getBySearch(String keyword, String category, String state, int sprice, int fprice, int p) {
		List<Item> items;
		if (keyword != null) {
			keyword = keyword.toUpperCase();
			keyword = "%"+keyword.replace(" ","%")+"%";
		}else {
			keyword = "%";
		}
		if (state.equals("all")) {
			if (category.equals("all")) {
				items = em.createQuery(	"SELECT a FROM Item a"
									+ 	" WHERE (UPPER(a.name) LIKE :keyword OR UPPER(a.description) LIKE :keyword)"
									+	" AND a.price >=:sprice"
									+	" AND a.price <=:fprice"
									, Item.class).setParameter("keyword", keyword).setParameter("state", state).setParameter("sprice", sprice).setParameter("fprice", fprice).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
			} else {
				items = em.createQuery(	"SELECT a FROM Item a"
								+ 	" WHERE (UPPER(a.name) LIKE :keyword OR UPPER(a.description) LIKE :keyword)"
								+ 	" AND a.category = :category "
								+	" AND a.price >= :sprice"
								+	" AND a.price <= :fprice"
								, Item.class).setParameter("keyword", keyword).setParameter("category",category).setParameter("state", state).setParameter("sprice", sprice).setParameter("fprice", fprice).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
			}
		} else {
			if (category.equals("all")) {
				items = em.createQuery(	"SELECT a FROM Item a"
									+ 	" WHERE (UPPER(a.name) LIKE :keyword OR UPPER(a.description) LIKE :keyword)"
									+	" AND a.state = :state "
									+	" AND a.price >=:sprice"
									+	" AND a.price <=:fprice"
									, Item.class).setParameter("keyword", keyword).setParameter("state", state).setParameter("sprice", sprice).setParameter("fprice", fprice).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
			} else {
				items = em.createQuery(	"SELECT a FROM Item a"
								+ 	" WHERE (UPPER(a.name) LIKE :keyword OR UPPER(a.description) LIKE :keyword)"
								+ 	" AND a.category = :category "
								+	" AND a.state = :state "
								+	" AND a.price >= :sprice"
								+	" AND a.price <= :fprice"
								, Item.class).setParameter("keyword", keyword).setParameter("category",category).setParameter("state", state).setParameter("sprice", sprice).setParameter("fprice", fprice).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
			}	
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


	@Override
	public void addItem(Item item) {
		em.persist(item);
	}
	
	@Override
	public String create(Item i) {
		
		if (em.contains(i)) {
			throw new IllegalArgumentException("Ad already exists");
		}
		em.persist(i);
		// Sync the transaction to get the newly generated id
		em.flush();
		
		return i.getId();
	}


	@Override
	public void removeItem(Item item) {
		Query query = em.createQuery(
				"UPDATE Item a SET a.sold = True " +
				 "WHERE a.id = :wantedid");
		query.setParameter("wantedid", item.getId()).executeUpdate();
	}
	
	@Override
	public int updateItem(Item item) {
		String Id = item.getId();
		Query query = em.createQuery(
				"UPDATE Item a SET a.name = :name , a.category = :category , a.state = :state, a.desc = :description, a.price = :price " +
				 "WHERE a.id = :wantedid");
		query.setParameter("wantedid", Id).setParameter("name",  item.getName()).setParameter("category", item.getCategory()).setParameter("state", item.getState()).setParameter("description", item.getDescription()).setParameter("price", item.getPrice()).executeUpdate();
		return 0;
	}


//	@Override
//	public int updateItem(String itemId, String field, String change) {
//		switch (field) {
//			case "name":
//				Query query = em.createQuery(
//					      "UPDATE Item c SET c." + field + " = :change " + 
//					      "WHERE c.id = :itemid");
//					  query.setParameter("itemid", itemId).setParameter("change",  change).executeUpdate();
//					  return 0;
//			case "category":
//				Query query2 = em.createQuery(
//						"UPDATE Item c SET c." + field + " = :change " + 
//					      "WHERE c.id = :itemid");
//					  query2.setParameter("itemid", itemId).setParameter("change",  change).executeUpdate();
//					  return 0;
//			case "state":
//				Query query3 = em.createQuery(
//						"UPDATE Item c SET c." + field + " = :change " + 
//					      "WHERE c.id = :itemid");
//					  query3.setParameter("itemid", itemId).setParameter("change",  Integer.parseInt(change)).executeUpdate();
//					  return 0;
//			case "description":
//				Query query4 = em.createQuery(
//						"UPDATE Item c SET c." + field + " = :change " + 
//					      "WHERE c.id = :itemid");
//					  query4.setParameter("itemid", itemId).setParameter("change",  change).executeUpdate();
//					  return 0;
//			case "price":
//				Query query5 = em.createQuery(
//						"UPDATE Item c SET c." + field + " = :change " + 
//					      "WHERE c.id = :itemid");
//					  query5.setParameter("itemid", itemId).setParameter("change",  Integer.parseInt(change)).executeUpdate();
//					  return 0;
//			default:
//				return 1;
//		}
//	}


	@Override
	public List<Item> getItem(String usrID) {
		List<Item> items;
		items = em.createQuery(	"SELECT a FROM Item AS a"
				+ 	" WHERE a.usrId = :userid"
				, Item.class).setParameter("userid", usrID).getResultList();
		return items;
	}
	
	@Override
	public List<Item> getItemid(String id) {
		List<Item> items;
		items = em.createQuery(	"SELECT a FROM Item AS a"
				+ 	" WHERE a.id = :id"
				, Item.class).setParameter("id", id).getResultList();
		return items;
	}



}
