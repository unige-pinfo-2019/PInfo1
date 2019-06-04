package api;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import domain.model.Messenger;
import domain.service.MessengerService;

@ApplicationScoped
@Transactional
@Path("/messenger")
public class MessengerRestService {
	
	@Inject 
	private MessengerService Messengerservice;
	
	public void setMessengerService(MessengerService ms) {
		Messengerservice = ms;
	}
		
	
	@GET
	@Path("/allmessenger")
	@Produces("application/json")
	public List<Messenger> getAll() {
		return  Messengerservice.getAll();
	}
	
	@POST
	@Consumes("application/json")
	public Response addMessengerREST(Messenger Messenger1){
		Messenger Messenger = new Messenger(Messenger1.getMsg(),Messenger1.getSendId(),Messenger1.getReceiveId());
		try {
			Messengerservice.addMessenger(Messenger);
		} catch(IllegalArgumentException i ) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch(Exception e) {
			return Response.status(Status.BAD_GATEWAY).build();
		}
		return Response.status(Status.CREATED).location(URI.create("/allmessenger")).build();
	}
	
	
	@GET
	@Path("/getmessenger")
	@Produces("application/json")
	public List<Messenger> updateMessengerREST(@QueryParam("sendId")String sendId,
												@QueryParam("receiveId")String receiveId){
		return Messengerservice.getMessenger(sendId,receiveId);
	}
	
	@GET
	@Path("/getinfo")
	@Produces("application/json")
	public List<Object> updateMessengerREST(@QueryParam("usrId")String usrId){
		return Messengerservice.getInfo(usrId);
	}
	
}
