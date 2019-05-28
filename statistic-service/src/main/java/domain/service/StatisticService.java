package domain.service;

import java.util.List;
import java.util.TreeMap;

import domain.model.StatisticItem;
import domain.model.StatisticUser;
import domain.model.Categorie;

public interface StatisticService {
	
	public List<StatisticItem> getAllItem();
	public List<StatisticUser> getAllUser();
	
	public void addUserStats(StatisticUser stats);
	public void addItemStats(StatisticItem stats);
	public void setUserStats(String usrId, Categorie categorie, long n);
	public void setItemStats(String itemId, long n);
	public void removeUserStats(String usrId);
	public void removeItemStats(String itemId);
	
	public StatisticUser getUserStats(String usrId);
	public StatisticItem getItemStats(String itemId);
	public void incrementUser(String userId, Categorie categorie);
	public void incrementItem(String itemId);

	/*
	public void research(String word);
	public void research(String usrId, String word);
	*/

	public void clickOnItemByUser(String usrId, String itemId);
	public void clickOnItem(String itemId);
	
	public TreeMap<Categorie, Long> getUserHighlights(String usrId, int n);
	public TreeMap<Categorie, Long> getCategoryHighlights(int n);
	public TreeMap<String, Long> getCategoryItemHighlights(Categorie categorie, int n);
	public TreeMap<String, Long>  getItemHighlights(int n);
	
	
}
