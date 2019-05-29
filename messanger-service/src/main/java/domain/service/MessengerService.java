package domain.service;

import java.util.List;

import domain.model.Messenger;

public interface MessengerService {
	
	public List<Messenger> getAll();
	
	public void addMessenger(Messenger messenger);
	
//	public void removeMessenger(Messenger Messenger);
	
//	public int updateMessenger(Messenger Messenger);
	
	public List<Messenger> getMessenger(String sendId, String receiveId);
	
	public int seenMessage(Messenger messenger);


}