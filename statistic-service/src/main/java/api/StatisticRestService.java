package main.java.api;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.persistence.NoResultException;
import javax.persistence.EntityExistsException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;

import main.java.domain.model.StatisticItem;
import main.java.domain.model.StatisticItem.Categorie;
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
	public String getUserStats(@QueryParam("userid") String usrId) {
		try {
			StatisticUser stats = statsService.getUserStats(usrId);
			return stats.toString();
		}
		catch (NoResultException exc) {
			return "Error : there is no user with id " + usrId ;
		}
	}
	
	
	@GET
	@Path("/getitemstats")
	@Produces("text/plain")
	public String getItemStats(@QueryParam("itemid") String itemId) {
		try {
			StatisticItem stats = statsService.getItemStats(itemId);
			return stats.toString();
		}
		catch (NoResultException exc) {
			return "Error : there is no item with id " + itemId ;
		}
	}
	
	
	@GET
	@Path("/setuserstats")
	@Produces("text/plain")
	public String setUserStats(@QueryParam("userid") String userId, @QueryParam("categorie") String categorie, @DefaultValue("0") @QueryParam("nclics") String nClics) {
		try {
			statsService.setUserStats(userId, Categorie.valueOf(categorie.toUpperCase()), Long.parseLong(nClics));
			return "You updated statistics from user " + userId ;
		}
		catch (IllegalArgumentException exc) {
			return "Error : category " + categorie + " doesn't exist or failed to parse to long";
		}
	}
	
	@GET
	@Path("/setitemstats")
	@Produces("text/plain")
	public String setItemStats(@QueryParam("itemid") String itemId, @QueryParam("nclics") String nClics) {
		statsService.setItemStats(itemId, Long.parseLong(nClics));
		return "You updated statistics from item " + itemId ;
	}
	
	@GET
	@Path("/alluserstats")
	@Produces("text/plain")
	public String getAllUserStats() {
		List<StatisticUser> all = statsService.getAllUser();
		return toStreamUser(all);
	}
	
	@GET
	@Path("/allitemstats")
	@Produces("text/plain")
	public String getAllItemStats() {
		List<StatisticItem> all = statsService.getAllItem();
		return toStreamItem(all);
	}
	
	@GET
	@Path("/adduserstatstest")
	@Produces("text/plain")
	public String addUserStatsTest() {
		StatisticUser stats1 = new StatisticUser("u123", 1, 2, 3, 4, 5), stats2 = new StatisticUser("u124", 3, 2, 0, 1, 1) ;
		statsService.addUserStats(stats1);
		statsService.addUserStats(stats2);
		return "You inserted " + stats1.toString() + " and " + stats2.toString();
	}
	
	@GET
	@Path("/additemstatstest")
	@Produces("text/plain")
	public String addItemStatsTest() {
		StatisticItem stats1 = new StatisticItem("i123", 10), stats2 = new StatisticItem("i124", 20);
		statsService.addItemStats(stats1);
		statsService.addItemStats(stats2);
		return "You inserted " + stats1.toString() + " and " + stats2.toString();
	}
	
	@GET
	@Path("/adduserstats")
	@Produces("text/plain")
	public String addUserStats(@QueryParam("userid") String userId, @QueryParam("nclicslivres") String nClicsLivres, @QueryParam("nclicsmobilite") String nClicsMobilite, @QueryParam("nclicselectronique") String nClicsElectronique, @QueryParam("nclicsnotes") String nClicsNotes, @QueryParam("nclicsmobilier") String nClicsMobilier) {
		try {
			StatisticUser stats = new StatisticUser(userId, Long.parseLong(nClicsLivres), Long.parseLong(nClicsMobilite), Long.parseLong(nClicsElectronique), Long.parseLong(nClicsNotes), Long.parseLong(nClicsMobilier)) ;
			statsService.addUserStats(stats);
			return "You inserted " + stats.toString();
		}
		catch (EntityExistsException exc) {
			return "Error : user " + userId + " already exists";
		}
	}
	
	@GET
	@Path("/additemstats")
	@Produces("text/plain")
	public String addItemStats(@QueryParam("itemid") String itemId, @QueryParam("nclicsitem") String nClicsItem) {
		try {
			StatisticItem stats = new StatisticItem(itemId, Long.parseLong(nClicsItem)) ;
			statsService.addItemStats(stats);
			return "You inserted " + stats.toString() ;
		}
		catch (EntityExistsException exc) {
			return "Error : item " + itemId + " already exists";
		}
	}
	
	@GET
	@Path("/deluserstats")
	@Produces("text/plain")
	public String deleteUserStats(@QueryParam("userid") String userId) {
		try {
			StatisticUser stats = statsService.getUserStats(userId);
			statsService.removeUserStats(userId);
			return "You deleted statistics from user " + userId ;	
		}
		catch (NoResultException exc) {
			return "Error : there is no user with id " + userId ;
		}
		
	}
	
	@GET
	@Path("/delitemstats")
	@Produces("text/plain")
	public String deleteItemStats(@QueryParam("itemid") String itemId) {
		try {
			StatisticItem stats = statsService.getItemStats(itemId);
			statsService.removeItemStats(itemId);
			return "You deleted statistics from item " + itemId ;	
		}
		catch (NoResultException exc) {
			return "Error : there is no item with id " + itemId ;
		}
		
	}
	
	@GET
	@Path("/clickonitembyuser")
	@Produces("text/plain")
	public String clickOnItem(@QueryParam("userid") String usrId, @QueryParam("itemid") String itemId, @QueryParam("categorie") String categorie) {
		try {
			statsService.getUserStats(usrId) ;
		}
		catch (NoResultException exc) {
			StatisticUser stats = new StatisticUser(usrId, 0, 0, 0, 0, 0) ;		//nouvelle instance de statistiques
			statsService.addUserStats(stats);
		}
		try {
			statsService.clickOnItem(usrId, itemId, Categorie.valueOf(categorie.toUpperCase()));
			return "You inserted empty statistics from user " + usrId + " and there is a click event on item " + itemId + " by user " + usrId ;
		}
		catch (IllegalArgumentException exc) {
			return "Error : category " + categorie + " doesn't exist";
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
	@Path("/gethighlights")
	@Produces("text/plain")
	public String getHighlights(@QueryParam("ncategories") String nCategories) {
		try {
			List<Categorie> categories = statsService.getHighlights(Integer.parseInt(nCategories)) ;
			return toStreamCategorie(categories);
		}
		catch(NoResultException exc) {
			return "Error : there is no highlights" ;
		}
	}
	
	@GET
	@Path("/gethighlight")
	@Produces("text/plain")
	public String getHighlight(@QueryParam("userid") String usrId, @QueryParam("ncategories") String nCategories) {
		try {
			List<Categorie> categories = statsService.getHighlight(usrId, Integer.parseInt(nCategories)) ;
			return toStreamCategorie(categories);
		}
		catch(NoResultException exc) {
			return "Error : there is no user with id " + usrId ;
		}
	}
	
	/*
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
	*/
	
	private String toStreamUser(List<StatisticUser> userStats) {
		return userStats.stream().map(StatisticUser::toString).collect(Collectors.joining("\n"));
	}
	
	private String toStreamItem(List<StatisticItem> itemStats) {
		return itemStats.stream().map(StatisticItem::toString).collect(Collectors.joining("\n"));
	}
	
	private String toStreamCategorie(List<Categorie> categories) {
		return categories.stream().map(Categorie::toString).collect(Collectors.joining("\n"));
	}
	
}
