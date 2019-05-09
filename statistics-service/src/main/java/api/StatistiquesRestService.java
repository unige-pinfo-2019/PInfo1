package main.java.api;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import main.java.domain.model.StatistiquesGeneral;
import main.java.domain.model.StatistiquesUser;
import main.java.domain.service.StatistiquesService;


@ApplicationScoped
@Transactional
@Path("/")
public class StatistiquesRestService {
	@Inject 
	private StatistiquesService statsService;
	
	public void setItemservice(StatistiquesService serv) {
		statsService = serv;
	}
	
	@GET
	@Path("/userstats")
	@Produces("text/plain")
	public String getUserStatsBySearch(	@DefaultValue("") 		@QueryParam("usr_id")String usrId) {
		List<StatistiquesUser> stats = statsService.getUserStats(usrId);
		return stats.toString();
		
	}
	
	@GET
	@Path("/generalstats")
	@Produces("text/plain")
	public String getItemStatsBySearch(	@DefaultValue("") 		@QueryParam("item_id")String itemId) {
		Optional<StatistiquesGeneral> stats = statsService.getGeneralStats(itemId);
		return stats.toString();
		
	}
		
	
	@GET
	@Path("/alluserstats")
	@Produces("text/plain")
	public String getAllUserStats() {
		List<StatistiquesUser> all = statsService.getAllUser();
		return all.toString();
	}
	
	@GET
	@Path("/allgeneralstats")
	@Produces("text/plain")
	public String getAll() {
		List<StatistiquesGeneral> all = statsService.getAllGeneral();
		return all.toString();
	}
	
	@GET
	@Path("/adduserstats")
	@Produces("text/plain")
	public String addUserStats() {
		statsService.addUserStats(new StatistiquesUser("User 1", "Le Seigneur des Anneaux", 0, "cours", 1000, "Livres", 1500));
		return "inserted";
	}
	
}
