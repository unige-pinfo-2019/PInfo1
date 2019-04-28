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

import domain.model.Annonce;
import domain.service.AnnonceService;

@ApplicationScoped
@Transactional
@Path("/")
public class AnnonceRestService {
	@Inject 
	private AnnonceService annonceservice;
	
	public void setItemservice(AnnonceService as) {
		annonceservice = as;
	}
	
//	@GET
//	@Path("/annonce/{page}")
//	@Produces("text/plain")
//	public String getBySearch(	@DefaultValue("") 		@QueryParam("keyword")String keyword,
//								@DefaultValue("all") 	@QueryParam("category")String category,
//								@DefaultValue("1") 		@QueryParam("state")int state,
//								@PathParam("page")String page){
//		int pa = Integer.parseInt(page);
//		List<Annonce> catalogue = annonceservice.getBySearch(keyword,category,state,pa);
//		return toStream(catalogue);
//		
//	}
		
	
	@GET
	@Path("/allannonce")
	@Produces("text/plain")
	public String getAll() {
		List<Annonce> all = annonceservice.getAll();
		return toStream(all);
	}
	
	@GET
	@Path("/addannonce")
	@Produces("text/plain")
	public String addAnnonces() {
		annonceservice.addAnnonces();
		return "inserted";
	}
	
	public String toStream(List<Annonce> annonce) {
		return annonce.stream().map(Annonce::toString).collect(Collectors.joining("\n"));
	}
}
