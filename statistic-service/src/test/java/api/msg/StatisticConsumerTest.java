package api.msg;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.service.StatisticService;

@ExtendWith(MockitoExtension.class)
public class StatisticConsumerTest {
	
	@Mock
	private SimpleKafkaProducer<String, String> kafkaProducer;
	@Mock
	private StatisticService statservice;
	
	@InjectMocks
	private StatisticConsumer consumer;
	
	private String topic = "stat";
	
	@Test
	void addItemTest() {
		
	}
	
	@Test
	void testremoveItemTest() {
		
	}
	
	@Test
	void incrementTest() {
		
	}
	
	@Test
	void gethighlightTest() {
		
	}
	
	@Test
	void adduserTest() {
		
	}
	
	@Test
	void removeuserTest() {
		
	}

}
