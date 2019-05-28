package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


import domain.model.Messenger;

@ApplicationScoped
@Transactional
@Default

public class MessengerServiceImpl implements MessengerService {

	@PersistenceContext(unitName="MessengersPU")
	private EntityManager em;

	
	@Override
	public List<Messenger> getAll() { 
		return em.createQuery("FROM Messenger", Messenger.class).getResultList();
	}
	

	@Override
	public void addMessenger(Messenger Messenger) {
		em.persist(Messenger);
	}

//	@Override
//	public void removeMessenger(Messenger Messenger) {
//		Query query = em.createQuery(
//				"UPDATE Messenger a SET a.state = :state * 10" +
//				 "WHERE a.id = :wantedid");
//		query.setParameter("wantedid", Messenger.getId()).setParameter("state",Messenger.getState()).executeUpdate();
//	}
//
//	@Override
//	public int updateMessenger(Messenger Messenger) {
//		String Id = Messenger.getId();
//		Query query = em.createQuery(
//				"UPDATE Messenger a SET a.name = :name , a.category = :category , a.state = :state, a.desc = :description " +
//				 "WHERE a.id = :wantedid");
//		query.setParameter("wantedid", Id).setParameter("name",  Messenger.getName()).setParameter("category", Messenger.getCategory()).setParameter("state", Messenger.getState()).setParameter("description", Messenger.getDescription()).executeUpdate();
//		return 0;
//	}
	
	

	@Override
	public List<Messenger> getMessenger(String sendId, String receiveId) {
		List<Messenger> Messengers;
		Messengers = em.createQuery("SELECT a FROM Messenger AS a"
				+ 	" WHERE (a.sendId = :sendId AND a.receiveId = :receiveId)"
				+   " OR (a.sendId = :receiveId2 AND a.receiveId = :sendId2)"
				, Messenger.class).setParameter("sendId", sendId).setParameter("receiveId", receiveId).setParameter("sendId2", receiveId).setParameter("receiveId2", sendId).getResultList();
		return Messengers;
	}
	
	@Override
	public int seenMessage(Messenger messenger) {
		Query query = em.createQuery("UPDATE Messenger a SET a.seenreceive = True WHERE a.message = :message AND a.sendId = :sendId AND a.receiveId = :receiveId");
		query.setParameter("message", messenger.getMsg()).setParameter("sendId", messenger.getSendId()).setParameter("receiveId", messenger.getReceiveId()).executeUpdate();
		return 0;
	}



}
