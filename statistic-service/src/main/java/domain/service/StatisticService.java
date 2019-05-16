package main.java.domain.service;

import java.util.List;

import main.java.domain.model.Categorie;
import main.java.domain.model.StatisticItem;
import main.java.domain.model.StatisticUser;

public interface StatisticService {
	
	public List<StatisticUser> getAllUser();
	public List<StatisticItem> getAllItem();

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

	public void clickOnItem(String usrId, String itemId, Categorie categorie);
	public void clickOnItem(String itemId);
	
	public List<Categorie> getUserHighlights(String usrId, int n);
	public List<Categorie> getHighlights(int n);
	public List<Categorie> getItemHighlight(Categorie categorie, int n);
	public List<Categorie> getItemHighlights(int n);

}