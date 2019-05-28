package domain.service;

import java.util.List;
import java.util.Optional;

import domain.model.Users;

public interface UserService {

	public List<Users> getAll();

	public boolean addUsers();

	public Long create(Users us);

	public String removeUser(String strid);

	public Optional<Users> getByNames(String name, String surname);

	public Optional<Users> getByNames(String username);

	public Optional<Users> getById(long id);

	public List<Users> getById2(String id);
	
	public void updateUser(Users us);


	public boolean modifyUser(String strid, String name,String surname,String username,String email, int report);

}
