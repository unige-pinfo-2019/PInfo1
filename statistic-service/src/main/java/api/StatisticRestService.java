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
	public String getUserStatsRest(@QueryParam("usrid") String usrId) {
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
	public String getItemStatsRest(@QueryParam("itemid") String itemId) {
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
		return all.stream().map(StatisticUser::toString).collect(Collectors.toList());
	}
	
	@GET
	@Path("/allitemstats")
	@Produces("application/json")
	public List<String> getAllItemStats() {
		List<StatisticItem> all = statsService.getAllItem();
		return all.stream().map(StatisticItem::toString).collect(Collectors.toList());
	}
	
	@GET
	@Path("/topcat")
	@Produces("application/json")
	public List<String> getCategoryHighlightsRest(@QueryParam("ncategories") String nCategories) {
		try {
			List<StatisticUser> all = statsService.getAllUser();
			if (all.isEmpty()) {
				List<String> listCat = new ArrayList<> ();
				listCat.add("LIVRE");
				listCat.add("MOBILITE");
				listCat.add("ELECTRONIQUE") ;
				listCat.add("COURS");
				listCat.add("MOBILIER");
				listCat.add("AUTRE");
				return listCat.subList(0, Integer.parseInt(nCategories)) ;
			}
			else {
				SortedMap<Categorie, Long> map = statsService.getCategoryHighlights(Integer.parseInt(nCategories)) ;
				List<Categorie> categories = new ArrayList<> (map.keySet()) ;
				return categories.stream().map(Categorie::toString).collect(Collectors.toList()) ;
			}
		}
		catch(NoResultException|IllegalArgumentException exc) {
			return new ArrayList<> () ;
		}
	}
	
	@GET
	@Path("/topusercat")
	@Produces("application/json")
	public List<String> getUserHighlightsRest(@QueryParam("usrid") String usrId, @QueryParam("ncategories") String nCategories) {
		try {
			StatisticUser all = statsService.getUserStats(usrId);
			SortedMap<Categorie, Long> map = statsService.getUserHighlights(usrId, Integer.parseInt(nCategories)) ;
			List<Categorie> categories = new ArrayList<> (map.keySet()) ;
			return categories.stream().map(Categorie::toString).collect(Collectors.toList()) ;
		}
		catch(NoResultException|IllegalArgumentException exc) {
			return new ArrayList<> () ;
		}
	}
	
	@GET
	@Path("/topitemcat")
	@Produces("application/json")
	public List<String> getCategoryItemHighlightsRest(@QueryParam("category") String categorie, @QueryParam("nitems") String nItems) {
		try {
			Categorie cat = Categorie.lookup(categorie.toUpperCase()) ;
			if (cat != null) {
				SortedMap<String, Long> map = statsService.getCategoryItemHighlights(cat, Integer.parseInt(nItems)) ;
				return new ArrayList<> (map.keySet()) ;
			}
			else
				return new ArrayList<> () ;
		}
		catch(NoResultException|IllegalArgumentException exc) {
			return new ArrayList<> () ;
		}
	}
	
	@GET
	@Path("/topitem")
	@Produces("application/json")
	public List<String> getItemHighlightsRest(@QueryParam("nitems") String nItems) {
		try {
			SortedMap<String, Long> map = statsService.getItemHighlights(Integer.parseInt(nItems)) ;
			return new ArrayList<> (map.keySet()) ;
		}
		catch(NoResultException|IllegalArgumentException exc) {
			return new ArrayList<> () ;
		}
	}

}
