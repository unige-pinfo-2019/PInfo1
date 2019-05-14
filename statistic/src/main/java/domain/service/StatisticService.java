package main.java.domain.service;

import java.util.List;

import main.java.domain.model.StatisticGeneral;
import main.java.domain.model.StatisticUser;

public interface StatisticService {
	
	public List<StatisticUser> getAllUser();

	public void addUserStats(StatisticUser stats);
	public void addGeneralStats(StatisticGeneral stats);
	public void removeUserStats(String usrId, String itemId);
	public void removeGeneralStats(String itemId);
	
	public List<StatisticUser> getItemStats(String itemId);
	public StatisticUser getUserStats(String usrId, String itemId);
	public StatisticGeneral getGeneralStats(String itemId);
	public void clickOnItem(String usrId, String itemId);

	public List<StatisticGeneral> getAllGeneral();

	public void research(String word);

	public void research(String usrId, String word);

	public void clickOnItem(String itemId);

	


}