package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

//import org.hibernate.search.jpa.FullTextEntityManager;
//import org.hibernate.search.query.dsl.QueryBuilder;

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

	@Override
	public boolean addItems() {
		Item item1 = new Item("velofm electrique",200, "mobilier", "papapa",3);
		Item item2 = new Item("gpomtvelosnf",50,"kayak", "pfonf", 3);
		Item item3 = new Item("vtt",400, "velo", "fvelgnfo", 3);
		Item item4 = new Item("sofa",600, "velo", "lrlmvelo moteur", 3);
		em.persist(item1);
		em.persist(item2);
		em.persist(item3);
		em.persist(item4);
		return true;
	}



}
