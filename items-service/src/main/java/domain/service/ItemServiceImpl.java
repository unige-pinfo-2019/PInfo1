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


	private String selectFrom = "SELECT a FROM Item a";
	private String selectLike = " WHERE (UPPER(a.name) LIKE :keyword OR UPPER(a.description) LIKE :keyword)";
	private String skeyword = "keyword";
	private String sfprice = "fprice";
	private String ssprice = "sprice";
	private String sstate = "state";
	private String scategory = "category";

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
				items = em.createQuery(	selectFrom
									+ 	selectLike
									+	" AND a.price >=:sprice"
									+	" AND a.price <=:fprice"
									+   " AND a.sold = false"
									, Item.class).setParameter(skeyword, keyword).setParameter(ssprice, sprice).setParameter(sfprice, fprice).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
			} else {
				items = em.createQuery(	selectFrom
								+ 		selectLike
								+ 	" AND a.category = :category "
								+	" AND a.price >= :sprice"
								+	" AND a.price <= :fprice"
								+   " AND a.sold = false"
								, Item.class).setParameter(skeyword, keyword).setParameter(scategory,category).setParameter(ssprice, sprice).setParameter(sfprice, fprice).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
			}
		} else {
			if (category.equals("all")) {
				items = em.createQuery(	selectFrom
									+ 	selectLike
									+	" AND a.state = :state "
									+	" AND a.price >=:sprice"
									+	" AND a.price <=:fprice"
									+   " AND a.sold = false"
									, Item.class).setParameter(skeyword, keyword).setParameter(sstate, state).setParameter(ssprice, sprice).setParameter(sfprice, fprice).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
			} else {
				items = em.createQuery(	selectFrom
								+ 		selectLike
								+ 	" AND a.category = :category "
								+	" AND a.state = :state "
								+	" AND a.price >= :sprice"
								+	" AND a.price <= :fprice"
								+   " AND a.sold = false"
								, Item.class).setParameter(skeyword, keyword).setParameter(scategory,category).setParameter(sstate, state).setParameter(ssprice, sprice).setParameter(sfprice, fprice).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
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
	public String create(Item i) {
		if (em.contains(i)) {
			throw new IllegalArgumentException("Item already exists");
		}
		i.setSold(false);
		em.persist(i);
		em.flush();

		return i.getId();
	}


	@Override
	public void removeItem(Item item) {
		Query query = em.createQuery(
				"UPDATE Item a SET a.sold = true " +
				 "WHERE a.id = :wantedid");
		query.setParameter("wantedid", item.getId()).executeUpdate();
	}

	@Override
	public int updateItem(Item item) {
		String id = item.getId();
		Query query = em.createQuery(
				"UPDATE Item a SET a.name = :name , a.category = :category , a.state = :state, a.description = :description, a.price = :price " +
				 "WHERE a.id = :wantedid");
		query.setParameter("wantedid", id).setParameter("name",  item.getName()).setParameter("category", item.getCategory()).setParameter("state", item.getState()).setParameter("description", item.getDescription()).setParameter("price", item.getPrice()).executeUpdate();
		return 0;
	}


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
