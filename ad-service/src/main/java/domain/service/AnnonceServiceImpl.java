package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

//import org.hibernate.search.jpa.FullTextEntityManager;
//import org.hibernate.search.query.dsl.QueryBuilder;

import domain.model.Annonce;

@ApplicationScoped
@Transactional
@Default

public class AnnonceServiceImpl implements AnnonceService {

	@PersistenceContext(unitName="AnnoncesPU")
	private EntityManager em;
//	FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
	
//	@Override
//	public List<Annonce> getBySearch(String keyword, String category, int state,int p) {
//		List<Annonce> items;
//		keyword = keyword.replace(" ","%");
//		if (category.equals("all")) {
//			items = em.createQuery(	"SELECT a FROM Item a"
//								+ 	" WHERE (a.name LIKE '%"+keyword+"%' OR a.description LIKE '%"+keyword+"%')"
//								+	" AND a.state >= '" +state+"' "
//								, Annonce.class).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
//		} else {
//			items = em.createQuery(	"SELECT a FROM Item a"
//							+ 	" WHERE (a.name LIKE '%"+keyword+"%' OR a.description LIKE '%"+keyword+"%')"
//							+ 	" AND a.category = '"+category+"' "
//							+	" AND a.state >= '" +state+"' "
//							, Annonce.class).setFirstResult((p-1)*10).setMaxResults(10).getResultList();
//		}
//		return items;
//	}
	
	/*@Override
	public List<Item> getBySearch(String keyword, String category, int state, int sprize, int fprize){
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Item.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields("name","description").matching(keyword).createQuery();
		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Item.class);
		List<Item> items = jpaQuery.getResultList();
		return null;
	}*/

	
	@Override
	public List<Annonce> getAll() { 
		return em.createQuery("FROM Item", Annonce.class).getResultList();
	}

	@Override
	public boolean addAnnonces() {
		Annonce annonce1 = new Annonce("étagère","mobilier",3);
		Annonce annonce2 = new Annonce("table","mobilier", 3);
		Annonce annonce3 = new Annonce("chaise","mobilier", 3);
		Annonce annonce4 = new Annonce("vélo","velo", 3);
		em.persist(annonce1);
		em.persist(annonce2);
		em.persist(annonce3);
		em.persist(annonce4);
		return true;
	}



}
