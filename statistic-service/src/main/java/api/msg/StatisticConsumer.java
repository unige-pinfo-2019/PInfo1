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
	
	/*
	@Consumer(topics = "additem", groupId = "Pinfo1")
	public void addItem(String itemId) {
		statisticservice.additem(itemId);
	}
		
	@Consumer(topics = "removeitem", groupId = "Pinfo1")
	public void removeItem(String itemId) {
		statisticservice.removeitem(itemId);
	}
	
	@Consumer(topics = "incrementitem",groupId = "Pinfo1")
	public void incrementItem(String itemId) {
		statisticservice.incrementItems(itemId);
	}
	
	@Consumer(topics = "gethighlight",groupId = "Pinfo1")
	public void getHighlight(String userId) {
		statisticservice.mostSearchCategories(userId);
	}
	
	@Consumer(topics = "adduser", groupId = "Pinfo1")
	public void addUser(String usrid) {
		statisticservice.addUser(usrid);
	}
	
	@Consumer(topics = "removeuser", groupId = "Pinfo1")
	public void removeUser(String usrid) {
		statisticservice.removeUser(usrid);
	}
	*/
	
}
