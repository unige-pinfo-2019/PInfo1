package api;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	
	@POST
	@Consumes("application/json")
	public Response addAnnoncesREST(Annonce annonce) {
		Annonce ann = new Annonce(annonce.getUsrId(),annonce.getName(),annonce.getCategory(),annonce.getState(),annonce.getDescription());
		try {
			annonceservice.addAnnonce(ann);
		} catch(IllegalArgumentException i ) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch(Exception e) {
			return Response.status(Status.BAD_GATEWAY).build();
		}
//		annonceProducer.send(annonce,"inserted " + annonce.getId() + " with usrid = " + usrid + " ,name = " + name + " ,category " + category + " ,state = " + state + " ,description = " + desc)
		return Response.status(Status.CREATED).location(URI.create("/allannonce")).build();
	}
	
	@DELETE
	@Path("/removeannonce")
	public Response addAnnoncesREST(@QueryParam("wantedid")String wantedid){
		annonceservice.removeAnnonce(wantedid);
		return Response.ok().build();
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
