package domain.service;

import java.util.List;
//import java.util.Optional;

import domain.model.Item;

public interface ItemService {

	public List<Item> getBySearch(String keyword, String category,int state, int sprize,int fprize,int p );

	public List<Item> getHighlight(String user);
	
	public List<Item> getAll();
	
//	public boolean addItems();
	
	public void addItem(Item item);
	
	public void removeItem(String itemId);
	
	public void updateItem(String itemId, String field, String change);
	
	public List<Item> getItem(String usrID);


}