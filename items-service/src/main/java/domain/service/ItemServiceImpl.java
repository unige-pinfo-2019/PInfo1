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

	@PersistenceContext(name="ItemsPU")
	EntityManager em;
	//FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
	
	@Override
	public List<Item> getBySearch(String keyword, String category, int state, int sprize, int fprize, int p) {
		List<Item> items;
		keyword = keyword.replace(" ","%");
		if (category.equals("all")) {
			items = em.createQuery(	"SELECT a FROM Item a"
								+ 	" WHERE (a.name LIKE '%"+keyword+"%' OR a.description LIKE '%"+keyword+"%')"
								+	" AND a.state > '" +state+"' "
								+	" AND a.prize >"+sprize+""
								+	" AND a.prize <"+fprize+""
								, Item.class).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
		} else {
			items = em.createQuery(	"SELECT a FROM Item a"
							+ 	" WHERE (a.name LIKE '%"+keyword+"%' OR a.description LIKE '%"+keyword+"%')"
							+ 	" AND a.category = '"+category+"' "
							+	" AND a.state > '" +state+"' "
							+	" AND a.prize >"+sprize+""
							+	" AND a.prize <"+fprize+""
							, Item.class).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
		}
		/*QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Item.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields("name","description").matching(keyword).createQuery();
		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Item.class);
		List<Item> items = jpaQuery.getResultList();*/
		return items;
	}

	@Override
	public List<Item> getHighlight(String user) {
		List<Item> items = em.createQuery("FROM Item", Item.class).getResultList();
		return items;
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
