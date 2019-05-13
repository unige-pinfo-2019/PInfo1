package api;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import domain.model.Annonce;
import domain.service.AnnonceService;

@ApplicationScoped
@Transactional
@Path("/annonce")
public class AnnonceRestService {
	@Inject 
	private AnnonceService annonceservice;
	
	public void setAnnonceService(AnnonceService as) {
		annonceservice = as;
	}
		
	
	@GET
	@Path("/allannonce")
	@Produces("application/json")
	public List<Annonce> getAll() {
		List<Annonce> all = annonceservice.getAll();
		return all;
	}
	
	@POST
	@Path("/addannonce")
	@Produces("text/plain")
	public String addAnnoncesREST(@QueryParam("usrid")String usrid,
								  @QueryParam("name")String  name,
								  @QueryParam("category")String category,
								  @QueryParam("state")String state,
								  @QueryParam("description")String desc){
		Annonce annonce = new Annonce(usrid,name,category,Integer.parseInt(state),desc);
		annonceservice.addAnnonce(annonce);
		return "inserted " + annonce.getId() + " with usrid = " + usrid + " ,name = " + name + " ,category " + category + " ,state = " + state + " ,description = " + desc;
	}
	
	@DELETE
	@Path("/removeannonce")
	@Produces("text/plain")
	public String addAnnoncesREST(@QueryParam("wantedid")String wantedid){
		annonceservice.removeAnnonce(wantedid);
		return "removed " + wantedid + "from database";
	}
	
	@PUT
	@Path("/updateannonce")
	@Produces("text/plain")
	public String updateAnnonceREST(@QueryParam("wantedid")String wanted,
								  @QueryParam("field")String  field,
								  @QueryParam("change")String change){
		annonceservice.updateAnnonce(wanted,field,change);
		return "changed made to " + wanted + " with field " + field + " = " + change ;
	}
	
	@GET
	@Path("/getannonce")
	@Produces("application/json")
	public List<Annonce> updateAnnonceREST(@QueryParam("usrid")String usrid){
		List<Annonce> annonce = annonceservice.getAnnonce(usrid);
		return annonce;
	}
	
	public String toStream(List<Annonce> annonce) {
		return annonce.stream().map(Annonce::toString).collect(Collectors.joining("\n"));
	}
}
