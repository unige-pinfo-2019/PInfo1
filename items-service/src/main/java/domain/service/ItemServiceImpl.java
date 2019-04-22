package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import domain.model.Item;

@ApplicationScoped
@Transactional
@Default

public class ItemServiceImpl implements ItemService {

	@PersistenceContext(name="ItemsPU")
	EntityManager em;
	
	
	@Override
	public List<Item> getBySearch(String keyword, String category, String state, int sprize, int fprize, int p) {
		List<Item> items = 
				em.createQuery(	"SELECT a FROM Item a"
						//	+ 	" WHERE (a.name LIKE '"+keyword+"%' OR a.description LIKE '"+keyword+"%')"
							+ 	" WHERE a.category = '"+category+"' "
							+	" AND a.prize >"+sprize+""
							+	" AND a.prize <"+fprize+""
							+	" LIMIT "+ p*10+" OFFSET "+ 10
							, Item.class).getResultList();
		return items;
	}

	@Override
	public List<Item> getHighlight(String user) {
		List<Item> items = em.createQuery("FROM Item", Item.class).getResultList();
		return items;
	}

	@Override
	public boolean addItems() {
		Item item1 = new Item("velo electrique",200, "velo", "papapa","good");
		Item item2 = new Item("lotr",50,"book", "pfonf", "excelent");
		Item item3 = new Item("vtt",400, "velo", "fofioadmo", "bad");
		Item item4 = new Item("sofa",600, "mobilier", "mfonwn", "very good");
		em.persist(item1);
		em.persist(item2);
		em.persist(item3);
		em.persist(item4);
		return true;
	}

}
