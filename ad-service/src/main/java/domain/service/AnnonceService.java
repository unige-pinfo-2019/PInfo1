package domain.service;

import java.util.List;
//import java.util.Optional;

import domain.model.Annonce;

public interface AnnonceService {
	
	public List<Annonce> getAll();
	
	public boolean addAnnonces();


}