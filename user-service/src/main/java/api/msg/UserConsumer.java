package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import domain.model.Users;
import domain.service.UserService;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class UserConsumer {

	@Inject
	private UserService userservice;


	@Consumer(topics = "addUserToUsers", groupId = "Pinfo1")
	public void addItem(String userId, String name,String surname, String email) {
		Users u = new Users(userId, name, surname, email,0);
		userservice.create(u);
	}
}