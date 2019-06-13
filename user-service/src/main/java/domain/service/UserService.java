package domain.service;

import java.util.List;
import domain.model.Users;

public interface UserService {

	public List<Users> getAll();

	public String create(Users us);
	
	public Users getByIdUser(String id);
	
	public String incrementReport(String id, String idreport);
	
	public String updateImage(String id, String image);


}
