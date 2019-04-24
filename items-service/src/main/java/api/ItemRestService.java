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
	@Path("/add")
	@Produces("text/plain")
	public String addItems() {
		itemservice.addItems();
		return "inserted";
	}
	
	
	@GET
	@Path("/home/{user}")
	@Produces("text/plain")
	public String getHighlight(@PathParam("user") String user) {
		List<Item> highl = itemservice.getHighlight(user);
		return highl.stream().map(p -> p.toString()).collect(Collectors.joining("\n"));
		
	}
	
	@GET
	@Path("/s/{page}")
	@Produces("text/plain")
	public String getBySearch(	@DefaultValue("") 		@QueryParam("keyword")String keyword,
								@DefaultValue("all") 	@QueryParam("category")String category,
								@DefaultValue("1") 		@QueryParam("state")int state,
								@DefaultValue("0") 		@QueryParam("sprize")int sprize,
								@DefaultValue("10000") 	@QueryParam("fprize")int fprize,
								@PathParam("page")String page){
		int pa = Integer.parseInt(page);
		List<Item> catalogue = itemservice.getBySearch(keyword,category,state,sprize,fprize,pa);
		return catalogue.stream().map(p -> p.toString()).collect(Collectors.joining("\n"));
		
	}
}
