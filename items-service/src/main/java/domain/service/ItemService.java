package domain.service;

import java.util.List;

import domain.model.Item;

public interface ItemService {

	public List<Item> getBySearch(String keyword, String category,int state, int sprice,int fprice,int p );

	public List<Item> getHighlight(String user);

	public List<Item> getAll();

	public String create(Item i);


//	public boolean addItems();

	public void addItem(Item item);

	public void removeItem(String itemId);

	public int updateItem(String itemId, String field, String change);

	public List<Item> getItem(String usrID);

	public List<Item> getItemid(String id);

}
