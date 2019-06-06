package domain.service;

import java.util.List;
import java.util.Optional;

import domain.model.Users;

public interface UserService {

	public List<Users> getAll();

	public String create(Users us);

	public Optional<Users> getById(String id);
	
	public Users getByIdUser(String id);

	
	public String incrementReport(String id, String idreport);
	
	public String updateImage(String id, String image);


}
