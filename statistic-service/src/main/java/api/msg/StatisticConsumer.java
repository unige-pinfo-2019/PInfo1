package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import domain.model.Categorie;
import domain.model.StatisticItem;
import domain.model.StatisticUser;
import domain.service.StatisticService;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class StatisticConsumer {

	@Inject
	private StatisticService statisticservice;
	

	@Inject
	private StatisticProducer statsProducer;


	@Consumer(topics = "additem", groupId = "Pinfo1")
	public void addItem(String itemId, String categorie) {
		StatisticItem stats = new StatisticItem(itemId, 0, Categorie.lookup(categorie.toUpperCase())) ;
		statisticservice.addItemStats(stats);
	}

	@Consumer(topics = "removeitem", groupId = "Pinfo1")
	public void removeItem(String itemId) {
		statisticservice.removeItemStats(itemId);
	}

	@Consumer(topics = "incrementitem",groupId = "Pinfo1")
	public void incrementItem(String itemId) {
		statisticservice.incrementItem(itemId);
	}
	
	@Consumer(topics = "incrementuser",groupId = "Pinfo1")
	public void incrementUser(String userdata) {
		String[] parts = userdata.split(" ");
		String id = parts[0];
		String cat = parts[1];
		Categorie categorie = Categorie.lookup(cat.toUpperCase());
		statisticservice.incrementUser(id, categorie);
	}

	@Consumer(topics = "adduser", groupId = "Pinfo1")
	public void addUser(String usrid) {
		StatisticUser stats = new StatisticUser(usrid, 0,0, 0,0, 0, 0) ;
		statisticservice.addUserStats(stats);
	}

	@Consumer(topics = "removeuser", groupId = "Pinfo1")
	public void removeUser(String usrid) {
		statisticservice.removeUserStats(usrid);
	}


}
