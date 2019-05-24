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
	private StatisticService statsService;
	
	@Inject
	private StatisticProducer statsProducer;
	
	@Consumer(topics = "additem", groupId = "Pinfo1")
	public void addItem(StatisticItem stats) {
		statsService.addItemStats(stats);
	}
		
	@Consumer(topics = "removeitem", groupId = "Pinfo1")
	public void removeItem(String itemId) {
		statsService.removeItemStats(itemId);
	}
	
	@Consumer(topics = "adduser", groupId = "Pinfo1")
	public void addUser(StatisticUser stats) {
		statsService.addUserStats(stats);
	}
	
	@Consumer(topics = "removeuser", groupId = "Pinfo1")
	public void removeUser(String usrid) {
		statsService.removeUserStats(usrid);
	}
	
	@Consumer(topics = "incrementitem",groupId = "Pinfo1")
	public void incrementItem(String itemId) {
		statsService.incrementItem(itemId);
	}
	
	@Consumer(topics = "incrementuser",groupId = "Pinfo1")
	public void incrementUser(String usrId, Categorie categorie) {
		statsService.incrementUser(usrId, categorie);
	}
	
	@Consumer(topics = "getcategoryhighlights",groupId = "Pinfo1")
	public void getCategoryHighlights(int n) {
		statsService.getCategoryHighlights(n);
	}
	
	@Consumer(topics = "getuserhighlights",groupId = "Pinfo1")
	public void getUserHighlights(String usrId, int n) {
		statsService.getUserHighlights(usrId, n);
	}
	
	@Consumer(topics = "getcategoryitemhighlights",groupId = "Pinfo1")
	public void getCategoryItemHighlights(Categorie categorie, int n) {
		statsService.getCategoryItemHighlights(categorie, n);
	}
	
	@Consumer(topics = "getitemhighlights",groupId = "Pinfo1")
	public void getItemHighlights(int n) {
		statsService.getItemHighlights(n);
	}
	
	
}
