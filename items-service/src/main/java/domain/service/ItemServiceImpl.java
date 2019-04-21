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
	public List<Item> getBySearch(String keyword, String category, String state, int sprize, int fprize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getHighlight(String user) {
		List<Item> items = em.createQuery("SELECT a FROM Item a ", Item.class).getResultList();
		return items;
	}

	@Override
	public boolean addItems() {
		Item item1 = new Item("velo electrique", "papapa", "velo", "good", 200);
		Item item2 = new Item("lotr", "pfonf", "book", "excelent", 50);
		Item item3 = new Item("vtt", "rioin", "velo", "bad", 400);
		Item item4 = new Item("sofa", "pfonin", "mobilier", "very good", 600);
		em.persist(item1);
		em.persist(item2);
		em.persist(item3);
		em.persist(item4);
		return true;
	}

}
