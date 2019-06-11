package api;
import java.net.URI;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import api.msg.AdProducer;
import domain.model.Annonce;
import domain.service.AnnonceService;

@ApplicationScoped
@Transactional
@Path("/annonce")
public class AnnonceRestService {

	@Inject
	private AnnonceService annonceservice;

	@Inject
	private AdProducer adproducer;

	private final static String labAnnonce = "/allannonce" ;
	
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
	public Response addAnnonceREST(Annonce ad){
		Annonce annonce = new Annonce(ad.getUsrId(), ad.getName(), ad.getCategory(), ad.getState(), ad.getDescription());
		try {
			annonceservice.addAnnonce(annonce);
		} catch(IllegalArgumentException i ) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch(Exception e) {
			return Response.status(Status.BAD_GATEWAY).build();
		}
		return Response.status(Status.CREATED).location(URI.create(labAnnonce)).build();
	}

	@PUT
	@Path("/updateannonce")
	@Consumes("application/json")
	public Response updateAnnonceRest(Annonce annonce1) {
		try {
			Annonce annonce = new Annonce(annonce1.getId(),annonce1.getUsrId(),annonce1.getName(),annonce1.getCategory(),annonce1.getState(),annonce1.getDescription());
			annonceservice.updateAnnonce(annonce);
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.status(Status.ACCEPTED).location(URI.create(labAnnonce)).build();
	}

	@PUT
	@Path("/removeannonce")
	@Consumes("application/json")
	public Response removeAnnonceRest(Annonce annonce1) {
		try {
			Annonce annonce = new Annonce(annonce1.getId(),annonce1.getUsrId(),annonce1.getName(),annonce1.getCategory(),annonce1.getState(),annonce1.getDescription());
			annonceservice.removeAnnonce(annonce);
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.ACCEPTED).location(URI.create(labAnnonce)).build();
	}


	@GET
	@Path("/getannonce")
	@Produces("application/json")
	public List<Annonce> getAnnonceRest(@QueryParam("usrid")String usrid){
		return annonceservice.getAnnonce(usrid);
	}
}
