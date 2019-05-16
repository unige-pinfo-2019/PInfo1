package domain.service;

import java.util.List;

import domain.model.StatisticItem;
import domain.model.StatisticUser;

public interface StatisticService {
	
	public List<String> MostSearchCategories (String userId);
	public List<String> MostSearchItems ();
	
	public int IncrementCategory(String userId, String category);
	public int IncrementItems(String itemId);
		
	public void additem(String itemid);
	public void addUser(String usrid);
	public void removeitem(String itemId);
	public void removeUser(String usrid);
	
	public List<StatisticItem> getAllItem();
	public List<StatisticUser> getAllUser();
	
	
}
