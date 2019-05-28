package domain.service;

import java.util.List;
import java.util.SortedMap;

import domain.model.StatisticItem;
import domain.model.StatisticUser;
import domain.model.Categorie;

public interface StatisticService {
	
	public List<StatisticItem> getAllItem();
	public List<StatisticUser> getAllUser();
	
	public void addUserStats(StatisticUser stats);
	public void addItemStats(StatisticItem stats);
	public void removeUserStats(String usrId);
	public void removeItemStats(String itemId);
	
	public StatisticUser getUserStats(String usrId);
	public StatisticItem getItemStats(String itemId);
	public void incrementUser(String userId, Categorie categorie);
	public void incrementItem(String itemId);
	
	public SortedMap<Categorie, Long> getUserHighlights(String usrId, int n);
	public SortedMap<Categorie, Long> getCategoryHighlights(int n);
	public SortedMap<String, Long> getCategoryItemHighlights(Categorie categorie, int n);
	public SortedMap<String, Long>  getItemHighlights(int n);
	
	
}
