package api;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import domain.model.Item;
import domain.service.ItemService;
import api.msg.ItemsProducer;

@ApplicationScoped
@Transactional
@Path("/item")
public class ItemRestService {
	
	@Inject 
	private ItemService itemservice;
	
	@Inject
	private ItemsProducer itemproducer;
	
	private String allitem = "/allitem";
	
	public void setItemservice(ItemService is) {
		itemservice = is;
	}
	
	
	
	@GET
	@Path("/s/{page}")
	@Produces("application/json")
	public List<Item> getBySearch(	@DefaultValue("") 		@QueryParam("keyword")String keyword,
								@DefaultValue("all") 	@QueryParam("category")String category,
								@DefaultValue("all") 		@QueryParam("state")String state,
								@DefaultValue("0") 		@QueryParam("sprice")int sprice,
								@DefaultValue("100000") 	@QueryParam("fprice")int fprice,
								@PathParam("page")String page){
		int pa = Integer.parseInt(page);
		return itemservice.getBySearch(keyword,category,state,sprice,fprice,pa);
		
	}
	
	@POST
	@Consumes("application/json")
	public Response additemsREST(Item item1,String name,String surname,String email){
		itemproducer.sendUserToUser(item1.getUsrId(), name, surname, email, "addUserToUsers");
		Item item = new Item(item1.getUsrId(),item1.getName(),item1.getPrice(),item1.getCategory(),item1.getDescription(),item1.getState(),item1.getImages());
		try {
			itemservice.create(item);
		} catch(IllegalArgumentException i ) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch(Exception e) {
			return Response.status(Status.BAD_GATEWAY).build();
		}
		itemproducer.sendItem(item,"additem");
		return Response.status(Status.CREATED).location(URI.create("/allitem")).build();
	}
	
	@PUT
	@Path("/updateitem")
	@Consumes("application/json")
	public Response updateitemRest(Item item1) {
		try {
			Item item = new Item(item1.getId(),item1.getUsrId(),item1.getName(),item1.getPrice(),item1.getCategory(),item1.getDescription(),item1.getState(), item1.getImages());
			itemservice.updateItem(item);
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return Response.status(Status.ACCEPTED).location(URI.create(allitem)).build();
	}
	
	@PUT
	@Path("/removeitem")
	@Consumes("application/json")
	public Response removeitemRest(Item item1) {
		try {
			Item item = new Item(item1.getId(),item1.getUsrId(),item1.getName(),item1.getPrice(),item1.getCategory(),item1.getDescription(),item1.getState(), item1.getImages());
			itemservice.removeItem(item);
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.ACCEPTED).location(URI.create(allitem)).build();
	}
		
	@GET
	@Path("/highlight")
	@Produces("text/plain")
	public String getHighlight(@QueryParam("usrid") String usrid) {
		itemproducer.sendUser(usrid, "gethighlight");
		List<Item> highl = itemservice.getHighlight(usrid);
		return toStream(highl);
		
	}
	
	@GET
	@Path("/allitem")
	@Produces("application/json")
	public List<Item> getAll() {
		return itemservice.getAll();
	}
	
	public String toStream(List<Item> item) {
		return item.stream().map(Item::toString).collect(Collectors.joining("\n"));
	}

	
	@GET
	@Path("/getitem")
	@Produces("application/json")
	public List<Item> getItemREST(@QueryParam("usrid")String usrid){
		return itemservice.getItem(usrid);
	}
	
	@GET
	@Path("/getitemID")
	@Produces("application/json")
	public List<Item> getItemIDREST(@QueryParam("id")String id){
		itemproducer.sendItembyid(id, "incrementitem");
		return itemservice.getItemid(id);
	}
}
