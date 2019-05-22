package domain.service;

import java.util.List;

import domain.model.Annonce;

public interface AnnonceService {
	
	public List<Annonce> getAll();
	
	public void addAnnonce(Annonce annonce);
	
	public void removeAnnonce(Annonce annonce);
	
	public int updateAnnonce(Annonce annonce);
	
	public List<Annonce> getAnnonce(String usrID);


}