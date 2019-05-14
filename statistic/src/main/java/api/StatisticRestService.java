package main.java.api;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import main.java.domain.model.StatisticGeneral;
import main.java.domain.model.StatisticGeneral.Categorie;
import main.java.domain.model.StatisticUser;
import main.java.domain.service.StatisticService;


@ApplicationScoped
@Transactional
@Path("/statistic")
public class StatisticRestService {
	
	@Inject 
	private StatisticService statsService;
	
	public void setStatisticservice(StatisticService serv) {
		statsService = serv;
	}
	
	@GET
	@Path("/getuserstats")
	@Produces("text/plain")
	public String getUserStats(@QueryParam("userid") String usrId, @QueryParam("itemid") String itemId) {
		try {
			StatisticUser stats = statsService.getUserStats(usrId, itemId);
			return stats.toString();
		}
		catch (NoResultException exc) {
			return "Error : there is no user with id " + usrId ;
		}
	}
	
	@GET
	@Path("/getgeneralstats")
	@Produces("text/plain")
	public String getGeneralStats(@QueryParam("itemid") String itemId) {
		try {
			StatisticGeneral stats = statsService.getGeneralStats(itemId);
			return stats.toString();
		}
		catch (NoResultException exc) {
			return "Error : there is no item with id " + itemId ;
		}
	}
	
	@GET
	@Path("/getitemstats")
	@Produces("text/plain")
	public String getItemStats(@QueryParam("itemid") String itemId) {
		List<StatisticUser> stats = statsService.getItemStats(itemId);
		if (!stats.isEmpty())
			return toStreamUser(stats);
		else
			return "Error : there is no item with id " + itemId ;
	}
		
	
	@GET
	@Path("/alluserstats")
	@Produces("text/plain")
	public String getAllUserStats() {
		List<StatisticUser> all = statsService.getAllUser();
		return toStreamUser(all);
	}
	
	@GET
	@Path("/allgeneralstats")
	@Produces("text/plain")
	public String getAll() {
		List<StatisticGeneral> all = statsService.getAllGeneral();
		return toStreamGeneral(all);
	}
	
	@GET
	@Path("/adduserstatstest")
	@Produces("text/plain")
	public String addUserStatsTest() {
		StatisticUser stats = new StatisticUser("u123", "i123", 1, "cours", 100, Categorie.Livres, 150);
		statsService.addUserStats(stats);
		return "You inserted " + stats.toString();
	}
	
	@GET
	@Path("/addgeneralstatstest")
	@Produces("text/plain")
	public String addGeneralStatsTest() {
		StatisticGeneral stats = new StatisticGeneral("i123", 10, "cours", 1000, Categorie.Livres, 1500);
		statsService.addGeneralStats(stats);
		return "You inserted " + stats.toString() ;
	}
	
	@GET
	@Path("/adduserstats")
	@Produces("text/plain")
	public String addUserStats(@QueryParam("userid") String userId, @QueryParam("itemid") String itemId, @QueryParam("nclicsitem") long nClicsItem, @QueryParam("mot") String mot, @QueryParam("nclicsmot") long nClicsMot, @QueryParam("categorie") String categorie, @QueryParam("nclicscategorie") long nClicsCategorie) {
		if (Arrays.asList(Categorie.values().toString()).contains(categorie)) {
			StatisticUser stats = new StatisticUser(userId, itemId, nClicsItem, mot, nClicsMot, Categorie.valueOf(categorie), nClicsCategorie) ;
			statsService.addUserStats(stats);
			return "You inserted " + stats.toString();
		}
		else
			return "Error : category " + categorie + " doesn't exist" ;
	}
	
	@GET
	@Path("/addgeneralstats")
	@Produces("text/plain")
	public String addGeneralStats(@QueryParam("itemid") String itemId, @QueryParam("nclicsitem") long nClicsItem, @QueryParam("mot") String mot, @QueryParam("nclicsmot") long nClicsMot, @QueryParam("categorie") String categorie, @QueryParam("nclicscategorie") long nClicsCategorie) {
		if (Arrays.asList(Categorie.values().toString()).contains(categorie)) {
			StatisticGeneral stats = new StatisticGeneral(itemId, nClicsItem, mot, nClicsMot, Categorie.valueOf(categorie), nClicsCategorie) ;
			statsService.addGeneralStats(stats);
			return "You inserted " + stats.toString() ;
		}
		else
			return "Error : category " + categorie + " doesn't exist" ;
	}
	
	@GET
	@Path("/deluserstats")
	@Produces("text/plain")
	public String deleteUserStats(@QueryParam("userid") String userId, @QueryParam("itemid") String itemId) {
		try {
			StatisticUser stats = statsService.getUserStats(userId, itemId);
			statsService.removeUserStats(userId, itemId);
			return "You deleted statistic from user " + userId + " about item " + itemId ;	
		}
		catch (NoResultException exc) {
			return "Error : there is no user with id " + userId ;
		}
		
	}
	
	@GET
	@Path("/delgeneralstats")
	@Produces("text/plain")
	public String deleteGeneralStats(@QueryParam("itemid") String itemId) {
		try {
			StatisticGeneral stats = statsService.getGeneralStats(itemId);
			statsService.removeGeneralStats(itemId);
			return "You deleted general statistic from item " + itemId ;	
		}
		catch (NoResultException exc) {
			return "Error : there is no user with id " + itemId ;
		}
		
	}
	
	@GET
	@Path("/clickonitem")
	@Produces("text/plain")
	public String clickOnItem(@QueryParam("userid") String usrId, @QueryParam("itemid") String itemId) {
		try {
			StatisticUser stats = statsService.getUserStats(usrId, itemId);
			statsService.clickOnItem(usrId, itemId);
			return "Click event on item " + itemId + " by user " + usrId ;
		}
		catch (NoResultException exc) {
			//this.addUserStats(usrId, itemId, 1, "", 0, , nClicsCategorie) ;
			return "Error : there is no user with id " + itemId ;
		}
		
	}
	
	@GET
	@Path("/clickonitem")
	@Produces("text/plain")
	public String clickOnItem(@QueryParam("itemid") String itemId) {
		statsService.clickOnItem(itemId);
		return "Click event on item " + itemId + " by any user " ;
	}
	
	@GET
	@Path("/research")
	@Produces("text/plain")
	public String research(@QueryParam("userid") String usrId, @QueryParam("mot") String mot) {
		statsService.research(usrId, mot);
		return "Research on word " + mot + " by user " + usrId ;
	}
	
	@GET
	@Path("/research")
	@Produces("text/plain")
	public String research(@QueryParam("mot") String mot) {
		statsService.research(mot);
		return "Research on word " + mot + " by any user " ;
	}
	
	
	public String toStreamUser(List<StatisticUser> userStats) {
		return userStats.stream().map(StatisticUser::toString).collect(Collectors.joining("\n"));
	}
	
	public String toStreamGeneral(List<StatisticGeneral> itemStats) {
		return itemStats.stream().map(StatisticGeneral::toString).collect(Collectors.joining("\n"));
	}
	
}
