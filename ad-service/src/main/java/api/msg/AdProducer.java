package api.msg;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class AdProducer {
	
	@Producer
	private SimpleKafkaProducer<String,List<String>> producer;

	public void sendUserToUser(String id, String name, String surname, String email, String topic) {
		List<String> list = new ArrayList<String>();
		list.add(id);
		list.add(name);
		list.add(surname);
		list.add(email);
		producer.send(topic, list);
	}
}