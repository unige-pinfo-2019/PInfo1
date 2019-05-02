package api;

import java.util.List;
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
@Path("/")
public class UserRestService {
	
	@Inject
	private UserService userservice;
	
	public void setUserservice(UserService us) {
		userservice = us;
	}
	
		
	@GET
	@Path("/all")
	@Produces("text/plain")
	public String getAll() {
		List<User> all = userservice.getAll();
		return toStream(all);
	}

	
	@GET
	@Path("/add")
	@Produces("text/plain")
	public String addUsers() {
		userservice.addUsers();
		return "inserted";
	}
	
	
	@GET
	@Path("/select")
	@Produces("text/plain")
	public String selectUser(@QueryParam("name")String name,
			@QueryParam("surname")String surname) {
				return userservice.getByNames(name,surname).toString();

	}
	
	
	@GET
	@Path("/delete/{id}")
	@Produces("text/plain")
	public String deleteUser(@PathParam("id") String str_id ) {
		return userservice.removeUser(str_id);
	}
	
	
	@GET
	@Path("/update")
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
