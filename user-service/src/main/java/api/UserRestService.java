package api;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import domain.model.User;
import domain.service.UserService;

@ApplicationScoped
@Transactional
@Path("/user")
public class UserRestService {
	
	@Inject
	private UserService userservice;
	
	public void setUserservice(UserService us) {
		userservice = us;
	}

	
		
	@GET
	@Path("/alluser")
	@Produces("application/json")
	public List<User> getAll() {
		return userservice.getAll();
	}

	
	@GET
	@Path("/adduser")
	@Produces("text/plain")
	public String addUsers() {
		userservice.addUsers();
		return "inserted";
	}
	
	
	@GET
	@Path("/getuser")
	@Produces("text/plain")
	public String selectUser(@QueryParam("name")String name,
			@QueryParam("surname")String surname) {
				return userservice.getByNames(name,surname).toString();

	}
	
	@GET
	@Path("/getuserid")
	@Produces("application/json")
	public List<User> selectUserID(@QueryParam("id")String strid) {
		return userservice.getById2(strid);

	}
	
	
	@GET
	@Path("/removeuser")
	@Produces("text/plain")
	public String deleteUser(@QueryParam("id") String strid ) {
		return userservice.removeUser(strid);
	}
	
	
	@GET
	@Path("/updateuser")
	@Produces("text/plain")
	public String update(@QueryParam("id") String strid, @QueryParam("name")String name,
			@QueryParam("surname")String surname,@QueryParam("username")String username,
			@DefaultValue("") @QueryParam("email")String email,
			@DefaultValue("0") @QueryParam("report")int report
			) {
		Boolean tmp = userservice.modifyUser(strid,name,surname,username,email,report);
		if (tmp) {
			return "L'utilisateur a été modifié \n";
		} 
		return "L'utilisateur est introuvable";
		
	}
	

}
