package api;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import domain.model.Categorie;
import domain.model.StatisticItem;
import domain.model.StatisticUser;
import domain.service.StatisticService;


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
	@Path("/getuser")
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
	@Path("/getitem")
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
	@Path("/alluserstats")
	@Produces("application/json")
	public List<String> getAllUserStats() {
		List<StatisticUser> all = statsService.getAllUser();
		return toStreamUser(all);
	}
	
	@GET
	@Path("/allitemstats")
	@Produces("application/json")
	public List<String> getAllItemStats() {
		List<StatisticItem> all = statsService.getAllItem();
		return toStreamItem(all);
	}
	
	@GET
	@Path("/topcat")
	@Produces("application/json")
	public List<String> getCategoryHighlights(@QueryParam("ncategories") String nCategories) {
		try {
			SortedMap<Categorie, Long> map = statsService.getCategoryHighlights(Integer.parseInt(nCategories)) ;
			List<Categorie> categories = new ArrayList<> (map.keySet()) ;
			return categories.stream().map(Categorie::toString).collect(Collectors.toList()) ;
		}
		catch(NoResultException exc) {
			return new ArrayList<> () ;
		}
	}
	
	@GET
	@Path("/topusercat")
	@Produces("application/json")
	public List<String> getUserHighlights(@QueryParam("userid") String usrId, @QueryParam("ncategories") String nCategories) {
		try {
			SortedMap<Categorie, Long> map = statsService.getUserHighlights(usrId, Integer.parseInt(nCategories)) ;
			List<Categorie> categories = new ArrayList<> (map.keySet()) ;
			return categories.stream().map(Categorie::toString).collect(Collectors.toList()) ;
		}
		catch(NoResultException exc) {
			return new ArrayList<> () ;
		}
	}
	
	@GET
	@Path("/topitemcat")
	@Produces("application/json")
	public List<String> getCategoryItemHighlights(@QueryParam("category") String categorie, @QueryParam("nitems") String nItems) {
		try {
			Categorie cat = Categorie.lookup(categorie.toUpperCase()) ;
			if (cat != null) {
				SortedMap<String, Long> map = statsService.getCategoryItemHighlights(cat, Integer.parseInt(nItems)) ;
				return new ArrayList<> (map.keySet()) ;
			}
			else
				return new ArrayList<> () ;
		}
		catch(NoResultException exc) {
			return new ArrayList<> () ;
		}
	}
	
	@GET
	@Path("/topitem")
	@Produces("application/json")
	public List<String> getItemHighlights(@QueryParam("nitems") String nItems) {
		try {
			SortedMap<String, Long> map = statsService.getItemHighlights(Integer.parseInt(nItems)) ;
			return new ArrayList<> (map.keySet()) ;
		}
		catch(NoResultException exc) {
			return new ArrayList<> () ;
		}
	}
	
	private static List<String> toStreamUser(List<StatisticUser> userStats) {
		return userStats.stream().map(StatisticUser::toString).collect(Collectors.toList());
	}
	
	private static List<String> toStreamItem(List<StatisticItem> itemStats) {
		return itemStats.stream().map(StatisticItem::toString).collect(Collectors.toList());
	}

}
