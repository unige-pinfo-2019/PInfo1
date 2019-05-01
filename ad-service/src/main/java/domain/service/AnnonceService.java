package domain.service;

import java.util.List;

import domain.model.Annonce;

public interface AnnonceService {
	
	public List<Annonce> getAll();
	
	public void addAnnonce(Annonce annonce);
	
	public void removeAnnonce(String Wanted_ID);
	
	public void updateAnnonce(String Wanted_ID, String field, String change);
	
	public List<Annonce> getAnnonce(String usrID);


}