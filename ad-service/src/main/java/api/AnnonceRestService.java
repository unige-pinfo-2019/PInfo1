package api;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
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
		return  annonceservice.getAll();
	}
	
	@GET
	@Path("/addannonce")
	@Produces("text/plain")
	public String addAnnoncesREST(@QueryParam("usrid")String usrid,
								  @QueryParam("name")String  name,
								  @QueryParam("category")String category,
								  @QueryParam("state")String state){
		Annonce annonce = new Annonce(usrid,name,category,Integer.parseInt(state));
		annonceservice.addAnnonce(annonce);
		return "inserted " + annonce.getId() + " with usrid = " + usrid + " ,name = " + name + " ,category " + category + " ,state = " + state;
	}
	
	@GET
	@Path("/removeannonce")
	@Produces("text/plain")
	public String addAnnoncesREST(@QueryParam("wantedid")String wantedid){
		annonceservice.removeAnnonce(wantedid);
		return "removed " + wantedid + "from database";
	}
	
	@GET
	@Path("/updateannonce")
	@Produces("text/plain")
	public String updateAnnonceREST(@QueryParam("wantedid")String wanted,
								  @QueryParam("field")String  field,
								  @QueryParam("change")String change){
		int i = annonceservice.updateAnnonce(wanted,field,change);
		if (i == 1) {
			return "field not finded";
		}
		return "changed made to " + wanted + " with field " + field + " = " + change ;
	}
	
	@GET
	@Path("/getannonce")
	@Produces("application/json")
	public List<Annonce> updateAnnonceREST(@QueryParam("usrid")String usrid){
		return annonceservice.getAnnonce(usrid);
	}
	
	public String toStream(List<Annonce> annonce) {
		return annonce.stream().map(Annonce::toString).collect(Collectors.joining("\n"));
	}
}
