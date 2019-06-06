package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import domain.service.UserService;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class UserProducer {
	@Producer
	private SimpleKafkaProducer <String, String> producer;
	
	@Inject
	private UserService userservice;
	
	public void sendUserbyid(String id, String topic) {
		producer.send(topic, id);
	}
	

}