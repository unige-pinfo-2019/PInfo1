package api.msg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import api.msg.StatisticConsumer;
import domain.service.StatisticService;

@ExtendWith(MockitoExtension.class)
public class StatisticConsumerTest {
	
	@Mock
	private SimpleKafkaProducer<String, String> kafkaProducer;
	
	@Mock
	private StatisticService statsService;
	
	@InjectMocks
	private StatisticConsumer consumer;
	
	/*
	@Test
	void addItemTest() {
		consumer.addItem("i123", "mobilier");
		verify(kafkaProducer, times(1)).send("mobilier", "i123");
	}*/
	
	@Test
	void removeItemTest() {
		
	}
	
	@Test
	void incrementItemTest() {
		
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
