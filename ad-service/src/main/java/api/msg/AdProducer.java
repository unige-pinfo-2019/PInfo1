package api.msg;

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
	private SimpleKafkaProducer<String,String> producer;

	public void sendUserToUser(String userdata, String topic) {
		producer.send(topic, userdata);
	}
}