package api;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;

import domain.service.StatisticService;
import domain.model.Categorie;
import domain.model.StatisticItem;
import domain.model.StatisticUser;


@ApplicationScoped
@Transactional
@Path("/statistic")
public class StatisticRestService {
	
private static final String userIdErr = "Error : there is no user with id ", itemIdErr = "Error : there is no user with id ", highlightErr = "Error : there is no highlights", inserted = "You inserted " ;
	
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
			return userIdErr + usrId ;
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
			return itemIdErr + itemId ;
		}
	}
	
	
	@GET
	@Path("/setuserstats")
	@Produces("text/plain")
	public String setUserStats(@QueryParam("userid") String userId, @QueryParam("category") String categorie, @DefaultValue("0") @QueryParam("nclics") String nClics) {
		statsService.setUserStats(userId, Categorie.lookup(categorie.toUpperCase(), Categorie.LIVRES), Long.parseLong(nClics));
		return "You updated statistics from user " + userId ;
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
		StatisticUser stats1 = new StatisticUser("u123", 1, 2, 3, 4, 5) ;
		StatisticUser stats2 = new StatisticUser("u124", 3, 2, 0, 1, 1) ;
		StatisticUser stats3 = new StatisticUser("u125", 3, 2, 1, 3, 1) ;
		StatisticUser stats4 = new StatisticUser("u126", 2, 1, 3, 2, 0) ;
		StatisticUser stats5 = new StatisticUser("u127", 2, 1, 0, 2, 1) ;
		StatisticUser stats6 = new StatisticUser("u128", 3, 3, 1, 0, 2) ;
		List<StatisticUser> users = new ArrayList<> () ;
		users.add(stats1);
		users.add(stats2);
		users.add(stats3);
		users.add(stats4);
		users.add(stats5);
		users.add(stats6);
		for (int i = 0 ; i < users.size() ; i++)
			statsService.addUserStats(users.get(i));
		return inserted + toStreamUser(users) ;
	}
	
	@GET
	@Path("/additemstatstest")
	@Produces("text/plain")
	public String addItemStatsTest() {
		StatisticItem stats1 = new StatisticItem("i123", 10, Categorie.ELECTRONIQUE);
		StatisticItem stats2 = new StatisticItem("i124", 20, Categorie.ELECTRONIQUE);
		StatisticItem stats3 = new StatisticItem("i125", 15, Categorie.LIVRES);
		StatisticItem stats4 = new StatisticItem("i126", 10, Categorie.MOBILIER);
		StatisticItem stats5 = new StatisticItem("i127", 12, Categorie.ELECTRONIQUE);
		StatisticItem stats6 = new StatisticItem("i128", 18, Categorie.LIVRES);
		StatisticItem stats7 = new StatisticItem("i129", 9, Categorie.NOTES);
		List<StatisticItem> items = new ArrayList<> () ;
		items.add(stats1);
		items.add(stats2);
		items.add(stats3);
		items.add(stats4);
		items.add(stats5);
		items.add(stats6);
		items.add(stats7);
		for (int i = 0 ; i < items.size() ; i++)
			statsService.addItemStats(items.get(i));
		return inserted + toStreamItem(items) ;
	}
	
	@GET
	@Path("/adduserstats")
	@Produces("text/plain")
	public String addUserStats(@QueryParam("userid") String userId, @QueryParam("nclicslivres") String nClicsLivres, @QueryParam("nclicsmobilite") String nClicsMobilite, @QueryParam("nclicselectronique") String nClicsElectronique, @QueryParam("nclicsnotes") String nClicsNotes, @QueryParam("nclicsmobilier") String nClicsMobilier) {
		StatisticUser stats = new StatisticUser(userId, Long.parseLong(nClicsLivres), Long.parseLong(nClicsMobilite), Long.parseLong(nClicsElectronique), Long.parseLong(nClicsNotes), Long.parseLong(nClicsMobilier)) ;
		statsService.addUserStats(stats);
		return inserted + stats.toString();
	}
	
	@GET
	@Path("/additemstats")
	@Produces("text/plain")
	public String addItemStats(@QueryParam("itemid") String itemId, @QueryParam("nclicsitem") String nClicsItem, @QueryParam("category") String categorie) {
		StatisticItem stats = new StatisticItem(itemId, Long.parseLong(nClicsItem), Categorie.lookup(categorie, Categorie.LIVRES)) ;
		statsService.addItemStats(stats);
		return inserted + stats.toString() ;
	}
	
	@GET
	@Path("/deluserstats")
	@Produces("text/plain")
	public String deleteUserStats(@QueryParam("userid") String userId) {
		try {
			statsService.getUserStats(userId);
			statsService.removeUserStats(userId);
			return "You deleted statistics from user " + userId ;	
		}
		catch (NoResultException exc) {
			return userIdErr + userId ;
		}
		
	}
	
	@GET
	@Path("/delitemstats")
	@Produces("text/plain")
	public String deleteItemStats(@QueryParam("itemid") String itemId) {
		try {
			statsService.getItemStats(itemId);
			statsService.removeItemStats(itemId);
			return "You deleted statistics from item " + itemId ;	
		}
		catch (NoResultException exc) {
			return itemIdErr + itemId ;
		}
		
	}
	
	@GET
	@Path("/clickonitembyuser")
	@Produces("text/plain")
	public String clickOnItemByUser(@QueryParam("userid") String usrId, @QueryParam("itemid") String itemId) {
		try {
			statsService.getUserStats(usrId) ;
		}
		catch (NoResultException exc) {
			StatisticUser stats = new StatisticUser(usrId, 0, 0, 0, 0, 0) ;		//nouvelle instance de statistiques
			statsService.addUserStats(stats);
		}
		statsService.clickOnItemByUser(usrId, itemId);
		return "Click event on item " + itemId + " by user " + usrId ;
		
	}
	
	@GET
	@Path("/clickonitem")
	@Produces("text/plain")
	public String clickOnItem(@QueryParam("itemid") String itemId) {
		try {
			statsService.getItemStats(itemId) ;
		}
		catch (NoResultException exc) {
			StatisticItem stats = new StatisticItem(itemId, 0, Categorie.LIVRES) ;		//nouvelle instance de statistiques
			statsService.addItemStats(stats);
		}
		statsService.clickOnItem(itemId);
		return "Click event on item " + itemId + " by any user" ;
	}
	
	@GET
	@Path("/getcategoryhighlights")
	@Produces("text/plain")
	public String getCategoryHighlights(@QueryParam("ncategories") String nCategories) {
		try {
			TreeMap<Categorie, Long> categories = statsService.getCategoryHighlights(Integer.parseInt(nCategories)) ;
			return toStreamTreeMapCategorie(categories);
		}
		catch(NoResultException exc) {
			return highlightErr ;
		}
	}
	
	@GET
	@Path("/getuserhighlights")
	@Produces("text/plain")
	public String getUserHighlights(@QueryParam("userid") String usrId, @QueryParam("ncategories") String nCategories) {
		try {
			TreeMap<Categorie, Long> categories = statsService.getUserHighlights(usrId, Integer.parseInt(nCategories)) ;
			return toStreamTreeMapCategorie(categories);
		}
		catch(NoResultException exc) {
			return userIdErr + usrId ;
		}
	}
	
	@GET
	@Path("/getcategoryitemhighlights")
	@Produces("text/plain")
	public String getCategoryItemHighlights(@QueryParam("category") String categorie, @QueryParam("nitems") String nItems) {
		try {
			TreeMap<String, Long> items = statsService.getCategoryItemHighlights(Categorie.lookup(categorie, Categorie.LIVRES), Integer.parseInt(nItems)) ;
			return toStreamTreeMapItem(items);
		}
		catch(NoResultException exc) {
			return highlightErr + " in category " + categorie ;
		}
	}
	
	@GET
	@Path("/getitemhighlights")
	@Produces("text/plain")
	public String getItemHighlights(@QueryParam("nitems") String nItems) {
		try {
			TreeMap<String, Long> items = statsService.getItemHighlights(Integer.parseInt(nItems)) ;
			return toStreamTreeMapItem(items);
		}
		catch(NoResultException exc) {
			return highlightErr ;
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
	
	public static String toStreamUser(List<StatisticUser> userStats) {
		return userStats.stream().map(StatisticUser::toString).collect(Collectors.joining("\n"));
	}
	
	public static String toStreamItem(List<StatisticItem> itemStats) {
		return itemStats.stream().map(StatisticItem::toString).collect(Collectors.joining("\n"));
	}
	
	public static String toStreamCategorie(List<Categorie> categories) {
		return categories.stream().map(Categorie::toString).collect(Collectors.joining("\n"));
	}
	
	public static String toStreamTreeMapCategorie(TreeMap<Categorie, Long> map) {
		String str = "" ;
		Iterator<Entry<Categorie, Long>> it = map.entrySet().iterator() ;
		while (it.hasNext()) {
			Entry<Categorie, Long> entry = it.next() ;
			str += entry.getKey().toString() + " - " + entry.getValue() + "\n" ;
		}
		return str ;
	}
	
	public static String toStreamTreeMapItem(TreeMap<String, Long> map) {
		String str = "" ;
		Iterator<Entry<String, Long>> it = map.entrySet().iterator() ;
		while (it.hasNext()) {
			Entry<String, Long> entry = it.next() ;
			str += entry.getKey() + " - " + entry.getValue() + "\n" ;
		}
		return str ;
	}

}
