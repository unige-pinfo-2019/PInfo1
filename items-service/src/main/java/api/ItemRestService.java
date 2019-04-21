package api;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import domain.model.Item;
import domain.service.ItemService;

@ApplicationScoped
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
	@Path("/s")
	@Produces("text/plain")
	public String getBySearch(	@QueryParam("keyword")String keyword,
								@QueryParam("category")String category,
								@QueryParam("state")String state,
								@QueryParam("sprize")int sprize,
								@QueryParam("fprize")int fprize) {
		
		List<Item> catalogue = itemservice.getBySearch(keyword,category,state,sprize,fprize);
		return catalogue.stream().map(p -> p.toString()).collect(Collectors.joining("\n"));
		
	}
}
