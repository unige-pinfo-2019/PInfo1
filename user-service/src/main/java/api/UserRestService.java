package api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
		List<User> all = userservice.getAll();
		return all;
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
	public List<User> selectUserID(@QueryParam("id")String str_id) {
		return userservice.getById2(str_id);

	}
	
	
	@GET
	@Path("/removeuser")
	@Produces("text/plain")
	public String deleteUser(@QueryParam("id") String str_id ) {
		return userservice.removeUser(str_id);
	}
	
	
	@GET
	@Path("/updateuser")
	@Produces("text/plain")
	public String update(@QueryParam("id") String str_id, @QueryParam("name")String name,
			@QueryParam("surname")String surname,@QueryParam("username")String username,
			@DefaultValue("") @QueryParam("email")String email,
			@DefaultValue("0") @QueryParam("report")int report
			) {
		Boolean tmp = userservice.modifyUser(str_id,name,surname,username,email,report);
		if (tmp) {
			return "L'utilisateur a été modifié \n";
		} 
		return "L'utilisateur est introuvable";
		
	}
	
	
	private String toStream(List<User> user) {
		return user.stream().map(User::toString).collect(Collectors.joining("\n"));
	}

}
