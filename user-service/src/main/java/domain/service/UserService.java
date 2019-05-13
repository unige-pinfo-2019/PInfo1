package domain.service;

import java.util.List;
import java.util.Optional;

import domain.model.User;

public interface UserService {
	
	public List<User> getAll();
	
	public boolean addUsers();
	
	public String removeUser(String str_id);
	
	public Optional<User> getByNames(String name, String surname);
	
	public Optional<User> getByNames(String username);
	
	public Optional<User> getById(long id);
	
	public List<User> getById2(String id);

	
	public boolean modifyUser(String str_id, String name,String surname,String username,String email, int report);

}
