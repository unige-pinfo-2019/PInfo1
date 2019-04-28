package api;


import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DefaultValue;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
	
	
	private String toStream(List<User> user) {
		return user.stream().map(User::toString).collect(Collectors.joining("\n"));
	}

}
