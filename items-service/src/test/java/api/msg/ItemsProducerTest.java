package api.msg;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import domain.model.Item;
import domain.service.ItemService;

public class ItemsProducerTest {
	
	@Mock
	private SimpleKafkaProducer<String, String> kafkaProducer;
	
	@Mock
	private ItemService itemservice;
	
	@InjectMocks
	private ItemsProducer producer;
	
	private String topic = "items";
	
	
//	@Test
//	void testSendItem() {
//		Item i = getRandomItem();
//		//producer.sendItem(i, this.topic);
//		//verify(kafkaProducer, times(1)).send("items", i.getId());
//	}
	
//	@Test
//	void testSendItembyId() {
//		Item i = getRandomItem();
//		producer.sendItembyid(i.getId(), this.topic);
//		verify(kafkaProducer, times(1)).send("items", i.getId());
//	}
	
	private Item getRandomItem() {
		Item i = new Item();
		i.setId(UUID.randomUUID().toString());
		i.setUsrId(UUID.randomUUID().toString());
		i.setName(UUID.randomUUID().toString());
		i.setDescription(UUID.randomUUID().toString());
		i.setCategory(UUID.randomUUID().toString());
		i.setState(0);
		i.setPrice(0);
		return i;
}
}
