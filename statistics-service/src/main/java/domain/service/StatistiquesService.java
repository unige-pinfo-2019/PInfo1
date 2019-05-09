package main.java.domain.service;

import java.util.List;
import java.util.Optional;

import main.java.domain.model.StatistiquesGeneral;
import main.java.domain.model.StatistiquesUser;

public interface StatistiquesService {
	
	public List<StatistiquesUser> getAllUser();

	public void addUserStats(StatistiquesUser stats);
	public void addGeneralStats(StatistiquesGeneral stats);
	public void removeUserStats(String usrId, String itemId);
	public void removeGeneralStats(String itemId);
	
	public List<StatistiquesUser> getUserStats(String usrId);
	public List<StatistiquesUser> getItemStats(String itemId);
	public Optional<StatistiquesUser> getUserStats(String usrId, String itemId);
	public Optional<StatistiquesGeneral> getGeneralStats(String itemId);
	public void clickOnItem(String usrId, String itemId);

	public List<StatistiquesGeneral> getAllGeneral();

	public void research(String word);

	public void research(String usrId, String word);

	public void clickOnItem(String itemId);

	


}