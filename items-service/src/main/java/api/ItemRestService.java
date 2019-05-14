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

import domain.model.Item;
import domain.service.ItemService;

@ApplicationScoped
@Transactional
@Path("/item")
public class ItemRestService {
	@Inject 
	private ItemService itemservice;
	
	public void setItemservice(ItemService is) {
		itemservice = is;
	}
	
	@GET
	@Path("/s/{page}")
	@Produces("application/json")
	public List<Item> getBySearch(	@DefaultValue("") 		@QueryParam("keyword")String keyword,
								@DefaultValue("all") 	@QueryParam("category")String category,
								@DefaultValue("1") 		@QueryParam("state")int state,
								@DefaultValue("0") 		@QueryParam("sprice")int sprice,
								@DefaultValue("100000") 	@QueryParam("fprice")int fprice,
								@PathParam("page")String page){
		int pa = Integer.parseInt(page);
		return itemservice.getBySearch(keyword,category,state,sprice,fprice,pa);
		
	}
		
	@GET
	@Path("/home/{user}")
	@Produces("text/plain")
	public String getHighlight(@PathParam("user") String user) {
		List<Item> highl = itemservice.getHighlight(user);
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
	@Path("/additem")
	@Produces("text/plain")
	public String additemsREST(@QueryParam("usrid")String usrid,
			                      @QueryParam("name")String  name,
								  @QueryParam("price")int price,
								  @QueryParam("category")String category,
								  @QueryParam("description")String  description,
								  @QueryParam("state")String state){
		Item item = new Item(usrid,name,price,category,description,Integer.parseInt(state));
		itemservice.addItem(item);
		return "inserted " + item.getId() + " with usrid = " + usrid + " ,name = " + name + " ,price = " + price + " ,category " + category + " ,description = " + description + " ,state = " + state;
	}
	
	@GET
	@Path("/removeitem")
	@Produces("text/plain")
	public String additemsREST(@QueryParam("itemid")String itemid){
		itemservice.removeItem(itemid);
		return "removed " + itemid + "from database";
	}
	
	@GET
	@Path("/updateitem")
	@Produces("text/plain")
	public String updateitemREST(@QueryParam("itemid")String item,
								  @QueryParam("field")String  field,
								  @QueryParam("change")String change){
		int err = itemservice.updateItem(item,field,change);
		if (err == 0){
			return "changed made to " + item + " with field " + field + " = " + change ;
		}
		return "not valid field";
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
		return itemservice.getItemid(id);
	}
}
