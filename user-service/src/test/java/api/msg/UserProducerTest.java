package api.msg;

import java.util.UUID;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.User;
import domain.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserProducerTest {
	
	@Mock
	private SimpleKafkaProducer<String, String> kafkaProducer;
	@Mock
	private UserService userservice;
	
	@InjectMocks
	private UserProducer producer;
	
	private String topic = "user";
	
	@Test
	void testSendUser() {
		User user = getRandomUser();
		producer.sendUser(user, this.topic);
		verify(kafkaProducer, times(1)).send("user", ""+user.getId());
	}
	
	@Test
	void testSendUserbyid() {
		User user = getRandomUser();
		producer.sendUserbyid(""+user.getId(), this.topic);
		verify(kafkaProducer, times(1)).send("user", ""+user.getId());
	}
	
	private User getRandomUser() {
		User user = new User();
		user.setName(UUID.randomUUID().toString());
		user.setSurname(UUID.randomUUID().toString());
		user.setUsername(UUID.randomUUID().toString());
		user.setEmail(UUID.randomUUID().toString());
		user.setReport(0);
		return user;
}

}
