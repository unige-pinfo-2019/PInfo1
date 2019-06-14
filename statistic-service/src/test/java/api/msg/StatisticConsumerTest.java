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
		String s = "Statistiques [vues de l'item i123 = 0, catégorie correspondante = MOBILIER]" ;
		assertEquals(s, statsService.getItemStats("i123").toString());
	}
	
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
	*/

}
