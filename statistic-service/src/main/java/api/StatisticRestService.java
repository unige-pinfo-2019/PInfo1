package api;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

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
	@Path("/topcategory")
	@Produces("text/plain")
	public String getTopCategory(@QueryParam("userId")String userId ) {
		List<String> l = statsService.mostSearchCategories(userId);
		return l.get(0) + " " + l.get(1)+ " " + l.get(2);
	}

	@GET
	@Path("/topitems")
	@Produces("application/json")
	public List<String> getTopItems() {
		List<String> l = statsService.mostSearchItems();
		Iterator<String> i = l.iterator();
		String str = " ";
		while(i.hasNext()) {
			str = str+ " "+i.next();
		}
		return l;
	}

	@GET
	@Path("/incrementcat")
	@Produces("text/plain")
	public String incrementCategory(@QueryParam("userId")String userId,
									@QueryParam("category")String category) {
		statsService.incrementCategory(userId, category);
		return "incremented";
	}


}
