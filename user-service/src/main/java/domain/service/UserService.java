package domain.service;

import java.util.List;

import domain.model.User;

public interface UserService {
	
	public List<User> getAll();
	
	public boolean addUsers();

}
