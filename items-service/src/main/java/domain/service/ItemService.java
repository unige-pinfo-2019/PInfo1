package domain.service;

import java.util.List;

import domain.model.Item;

public interface ItemService {
	
//	enum State{
//		Neuf,
//		Bon_Etat,
//		Abime
//	}

//	public List<Item> getBySearch(String keyword, String category,State state, int sprice,int fprice,int p );

	public List<Item> getBySearch(String keyword, String category,String state, int sprice,int fprice,int p );
	
	public List<Item> getHighlight(String user);

	public List<Item> getAll();

	public String create(Item i);


//	public boolean addItems();

	public void addItem(Item item);

	public void removeItem(Item item);

	public int updateItem(Item item);

	public List<Item> getItem(String usrID);

	public List<Item> getItemid(String id);

}
