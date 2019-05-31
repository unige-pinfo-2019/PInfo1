package api;

import java.net.URI;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import api.msg.UserProducer;
import domain.model.Users;
import domain.service.UserService;

@ApplicationScoped
@Transactional
@Path("/user")
public class UserRestService {
	
	@Inject
	private UserService userservice;
	
	@Inject
	private UserProducer userproducer;
	
	public void setUserservice(UserService us) {
		userservice = us;
	}

	
		
	@GET
	@Path("/alluser")
	@Produces("application/json")
	public List<Users> getAll() {
		return userservice.getAll();
	}
	
	@POST
	@Consumes("application/json")
	public Response create(Users us) {
		Users usr = new Users(us.getName(), us.getSurname(), us.getUsername(),us.getEmail(),us.getReport(),us.getGrade());
		try {
			userservice.create(usr);
		} catch(IllegalArgumentException i) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch(Exception e) {
			return Response.status(Status.BAD_GATEWAY).build();
		}
		userproducer.sendUser(usr, "adduser");
		return Response.status(Status.CREATED).location(URI.create("/home")).build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response updateUser(Users us) {
		Users usr = new Users(us.getId(), us.getName(), us.getSurname(), us.getUsername(),us.getEmail(),us.getReport(),us.getGrade());
		try {
			userservice.updateUser(usr);
		} catch(IllegalArgumentException i) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch(Exception e) {
			return Response.status(Status.BAD_GATEWAY).build();
		}
		return Response.status(Status.CREATED).location(URI.create("/home")).build();
	}
	
	@GET
	@Path("/adduser")
	@Produces("application/json")
	public List<Users> addUsers() {
		userservice.addUsers();
		return userservice.getAll();
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
	public List<Users> selectUserID(@QueryParam("id")String strid) {
		return userservice.getById2(strid);

	}
	
	
	@GET
	@Path("/removeuser")
	@Produces("text/plain")
	public String deleteUser(@QueryParam("id") String strid ) {
		userproducer.sendUserbyid(strid,"removeuser");
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
