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
@Path("/")
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
								@DefaultValue("0") 		@QueryParam("sprize")int sprize,
								@DefaultValue("10000") 	@QueryParam("fprize")int fprize,
								@PathParam("page")String page){
		int pa = Integer.parseInt(page);
		List<Item> catalogue = itemservice.getBySearch(keyword,category,state,sprize,fprize,pa);
		return catalogue;
		
	}
		
	@GET
	@Path("/home/{user}")
	@Produces("text/plain")
	public String getHighlight(@PathParam("user") String user) {
		List<Item> highl = itemservice.getHighlight(user);
		return toStream(highl);
		
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<Item> getAll() {
		List<Item> all = itemservice.getAll();
		return all;
	}
	
	public String toStream(List<Item> item) {
		return item.stream().map(Item::toString).collect(Collectors.joining("\n"));
	}
	
	@GET
	@Path("/additem")
	@Produces("text/plain")
	public String additemsREST(@QueryParam("usrid")String usrid,
			                      @QueryParam("name")String  name,
								  @QueryParam("prize")int prize,
								  @QueryParam("category")String category,
								  @QueryParam("description")String  description,
								  @QueryParam("state")String state){
		Item item = new Item(usrid,name,prize,category,description,Integer.parseInt(state));
		itemservice.addItem(item);
		return "inserted " + item.getId() + " with usrid = " + usrid + " ,name = " + name + " ,price = " + prize + " ,category " + category + " ,description = " + description + " ,state = " + state;
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
	public List<Item> updateItemREST(@QueryParam("usrid")String usrid){
		List<Item> item = itemservice.getItem(usrid);
		return item;
	}
}
