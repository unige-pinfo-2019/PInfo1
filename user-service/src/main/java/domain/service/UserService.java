package domain.service;

import java.util.List;
import java.util.Optional;

import domain.model.User;

public interface UserService {

	public List<User> getAll();

	public boolean addUsers();

	public Long create(User us);

	public String removeUser(String strid);

	public Optional<User> getByNames(String name, String surname);

	public Optional<User> getByNames(String username);

	public Optional<User> getById(long id);

	public List<User> getById2(String id);
	
	public void updateUser(User us);


	public boolean modifyUser(String strid, String name,String surname,String username,String email, int report);

}
