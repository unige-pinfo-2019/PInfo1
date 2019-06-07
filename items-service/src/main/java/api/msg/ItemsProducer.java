package api.msg;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import domain.model.Item;
import domain.service.ItemService;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ItemsProducer {
	
	@Producer
	private SimpleKafkaProducer<String, String> producer;
	
	@Producer
	private SimpleKafkaProducer<String,List<String>> producer2;
	
	@Inject
	private ItemService itemservice;
	
	public void sendAll(String topic) {
		for (Item item : itemservice.getAll()) {
			producer.send(topic, item.getId());	
		}
	}
	public void sendItem(Item i, String topic) {
		producer.send(topic, i.getId(), i.getCategory());
	}
	
	public void sendItembyid(String id, String topic) {
		producer.send(topic, id);
	}
	
	public void sendUser(String id,String topic) {
		producer.send(topic, id);
	}
	
	public void sendUserToUser(String id, String name, String surname, String email, String topic) {
		List<String> list = new ArrayList<String>();
		list.add(id);
		list.add(name);
		list.add(surname);
		list.add(email);
		producer2.send(topic, list);
	}
}
