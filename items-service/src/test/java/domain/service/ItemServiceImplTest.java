package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Item;
import domain.model.Item;
import domain.model.Item;
import domain.model.Item;
import domain.model.Item;
import domain.model.Item;
import domain.model.Item;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {
	
	@Spy
	@PersistenceContext(unitName = "ItemsPUTest")
	EntityManager em;
	
	@InjectMocks
	private ItemServiceImpl itemserviceimpl;
	
	private int initDataStore() {
		em.clear();
		List<Item> items;
		Item item1 = new Item("1234","étagère",200,"mobilier","un mobilier",1);
		Item item2 = new Item("1234","chaise",300,"mobilier","un autre mobilier",2);
		Item item3 = new Item("123","vtt",250,"velo","un vehicule",5);
		Item item4 = new Item("123","sofa",400,"mobilier","un mobilier encore",4);
		Item item5 = new Item("1235","velo",600,"velo","sapristi un vehicule",3);
		Item item6 = new Item("1235","magazine",100,"livre","mon bouquin",3);
		itemserviceimpl.addItem(item1);
		itemserviceimpl.addItem(item2);
		itemserviceimpl.addItem(item3);
		itemserviceimpl.addItem(item4);
		itemserviceimpl.addItem(item5);
		itemserviceimpl.addItem(item6);
		items = itemserviceimpl.getAll();
		int size = items.size();
		return size;
	}
	
	@Test
	void addItemTest(){
		int size = initDataStore();
		Item item = new Item("1236","Le seigneur des anneaux",200,"livre","Un vrai bouquin",3);
		itemserviceimpl.addItem(item);
		assertEquals(size+1, itemserviceimpl.getAll().size());
	}
	
	
	@Test
	void getHighlightTest(){
		int size = initDataStore();
		assertEquals(size, itemserviceimpl.getHighlight("tom").size());
		
	}
	
	@Test
	void getAllTest(){
		int size = initDataStore();
		assertEquals(size, itemserviceimpl.getAll().size());
		
	}
	
	
//	@Test
//	void getBySearchTest() {
//		initDataStore2();
//		Item item1 = new Item("1","velofm electrique",200, "velo", "papapa",1);
//		Item item2 = new Item("2","gpomtvelosnf",50,"velo", "pfonf", 2);
//		Item item3 = new Item("3","vtt",400, "velo", "fvelgnfo", 5);
//		Item item4 = new Item("4","sofa",600, "velo", "lrlmvelo moteur", 4);
//		Item item5 = new Item("5","velo fovno electrique",200, "velo", "papapa",3);
//		Item item6 = new Item("6","gpomtvelosnf",50,"mobiler", "pfonf", 3);
//		List <Item> testvelo = new ArrayList<Item>();List <Item> testcat = new ArrayList<Item>(); List<Item> testpri =  new ArrayList<Item>();
//		testvelo.add(item1);testvelo.add(item2);testvelo.add(item4);testvelo.add(item5);testvelo.add(item6);
//		testcat.add(item1);testcat.add(item2);testcat.add(item4);testcat.add(item5);
//		testpri.add(item1);testpri.add(item3);testpri.add(item5);
//		assertEquals(testvelo, itemserviceimpl.getBySearch("velo", "all", 1, 1, 10000, 1));
//		assertEquals(testcat, itemserviceimpl.getBySearch("velo", "velo", 1, 1, 10000, 1));
//		assertEquals(testpri, itemserviceimpl.getBySearch("", "velo", 1, 200, 400, 1));
//	}
		
	@Test
	void ToStringTest() {
		Item item1 = new Item("1","velofm electrique",200, "velo", "papapa",1);
		item1.setId("1");
		String s = "Item [id = 1 usrid=1 name=velofm electrique, prize=200, category=velo, description=papapa, state=1]";
		assertEquals(s, item1.toString());
	}
	
	
//	private void initDataStore2() {
//		em.clear();
//		Item item1 = new Item("velofm electrique",200, "velo", "papapa",1);
//		Item item2 = new Item("gpomtvelosnf",50,"velo", "pfonf", 2);
//		Item item3 = new Item("vtt",400, "velo", "fvelgnfo", 5);
//		Item item4 = new Item("sofa",600, "velo", "lrlmvelo moteur", 4);
//		Item item5 = new Item("velo fovno electrique",200, "velo", "papapa",3);
//		Item item6 = new Item("gpomtvelosnf",50,"mobiler", "pfonf", 3);
//		em.persist(item1);
//		em.persist(item2);
//		em.persist(item3);
//		em.persist(item4);
//		em.persist(item5);
//		em.persist(item6);
//	}
	
//	private List<Item> getItems() {
//		List<Item> it = new ArrayList<>();
//		long numberOfCpty = Math.round((Math.random() * 10));
//		for (int i = 0; i < numberOfCpty; i++) {
//			it.add(createItem());
//		}
//		return it;
//	}


//	private Item createItem(){
//		Item i = new Item();
//		i.setName(UUID.randomUUID().toString());
//		i.setCategory(UUID.randomUUID().toString());
//		i.setDescription(UUID.randomUUID().toString());
//		i.setState(1 + (int)(Math.random() * ((5 - 1) + 1)));
//		i.setPrize(0 + (int)(Math.random() * ((10000 - 0) + 1)));
//		return i;
//	}
	
	private int initDataStore2() {
		em.clear();
		List<Item> items;
		Item item1 = new Item("1234","étagère",200,"mobilier","un mobilier",1);
		Item item2 = new Item("1234","chaise",300,"mobilier","un autre mobilier",2);
		Item item3 = new Item("123","vtt",250,"velo","un vehicule",5);
		Item item4 = new Item("123","sofa",400,"mobilier","un mobilier encore",4);
		Item item5 = new Item("1235","velo",600,"velo","sapristi un vehicule",3);
		Item item6 = new Item("1235","magazine",100,"livre","mon bouquin",3);
		itemserviceimpl.addItem(item1);
		itemserviceimpl.addItem(item2);
		itemserviceimpl.addItem(item3);
		itemserviceimpl.addItem(item4);
		itemserviceimpl.addItem(item5);
		itemserviceimpl.addItem(item6);
		items = itemserviceimpl.getItem("1234");
		int size = items.size();
		return size;
	}
	
	@Test
	void getItemTest() {
		int size = initDataStore2();
		assertEquals(size, itemserviceimpl.getItem("1234").size());
	}
	
	private int initDataStore4() {
		em.clear();
		List<Item> items;
		Item item1 = new Item("1234","étagère",200,"mobilier","un mobilier",1);
		Item item2 = new Item("1234","chaise",300,"mobilier","un autre mobilier",2);
		Item item3 = new Item("123","vtt",250,"velo","un vehicule",5);
		Item item4 = new Item("123","sofa",400,"mobilier","un mobilier encore",4);
		Item item5 = new Item("1235","velo",600,"velo","sapristi un vehicule",3);
		Item item6 = new Item("1235","magazine",100,"livre","mon bouquin",3);
		itemserviceimpl.addItem(item1);
		itemserviceimpl.addItem(item2);
		itemserviceimpl.addItem(item3);
		itemserviceimpl.addItem(item4);
		itemserviceimpl.addItem(item5);
		itemserviceimpl.addItem(item6);
		items = itemserviceimpl.getAll();
		int size = items.size();
		return size;
	}
	
	@Test 
	void removeItemTest() {
		int size = initDataStore4();
		Item item = new Item("1236","Le seigneur des anneaux",30,"livre","un vrai bouquin",3);
		Item item2 = new Item("1236","Le pianiste",50,"livre","un bouquin bof",3);
		itemserviceimpl.addItem(item);
		itemserviceimpl.addItem(item2);
		String id = item2.getId();
		itemserviceimpl.removeItem(id);
		assertEquals(size+1, itemserviceimpl.getAll().size());
	}
	
	@Test 
	void updateItemTest() {
		initDataStore4();
		Item item = new Item("1236","Le seigneur des anneaux",30,"livre","un vrai bouquin",3);
		Item item2 = new Item("1236","Le pianiste",50,"livre","un bouquin bof",3);
		itemserviceimpl.addItem(item);
		itemserviceimpl.addItem(item2);
		String id = item2.getId();
		itemserviceimpl.updateItem(id,"name","Le liseur");
		item2.setName("Le liseur");
		itemserviceimpl.updateItem(id,"category","magazine");
		item2.setCategory("magazine");
		itemserviceimpl.updateItem(id,"state","5");
		item2.setState(5);
		itemserviceimpl.updateItem(id,"error","error");
		assertEquals(item2.getName(), "Le liseur");
		assertEquals(item2.getCategory(), "magazine");
		assertEquals(item2.getState(), 5);
	}	
	
	@Test
	void modelTest() {
		Item item = new Item("1236","Le seigneur des anneaux",30,"livre","un vrai bouquin",3);
		item.setUsrId("1234");
		String newId = UUID.randomUUID().toString();
		item.setId(newId);
		assertEquals(item.getUsrId(),"1234");
		assertEquals(item.getId(),newId);
		assertEquals(item.toString(),"Item [id = "+newId+ " usrid="+ "1234" +" name=" + "Le seigneur des anneaux" + ", prize=" + 30 + ", category=" + "livre" + ", description=" + "un vrai bouquin"
				+ ", state=" + 3 + "]");
	}
	
	
	
	
}

