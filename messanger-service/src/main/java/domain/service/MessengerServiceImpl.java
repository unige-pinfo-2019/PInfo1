package domain.service;

import java.util.ArrayList;
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
	public void addMessenger(Messenger messenger) {
		em.persist(messenger);
	}	
	
	@Override
	public List<Messenger> getMessenger(String sendId, String receiveId) {
		List<Messenger> messengers;
		String stringSendId = "sendId";
		messengers = em.createQuery("SELECT a FROM Messenger AS a"
				+ 	" WHERE ((a." + stringSendId + " = :"+ stringSendId +  " AND a.receiveId = :receiveId) OR (a.sendId = :sendId2 AND a.receiveId = :receiveId2))"
				+   "  ORDER BY datetime ASC"
				, Messenger.class).setParameter(stringSendId, sendId).setParameter("receiveId", receiveId).setParameter("sendId2", receiveId).setParameter("receiveId2", sendId).getResultList();
		return messengers;
	}
	
	@Override
	public int seenMessage(Messenger messenger) {
		Query query = em.createQuery("UPDATE Messenger a SET a.seenreceive = :true WHERE a.msg = :message AND a.sendId = :sendId AND a.receiveId = :receiveId");
		query.setParameter("true", true).setParameter("message", messenger.getMsg()).setParameter("sendId", messenger.getSendId()).setParameter("receiveId", messenger.getReceiveId()).executeUpdate();
		return 0;
	}


	@Override
	public List<Object> getInfo(String userId) {
		List<Object> info1 = em.createQuery("SELECT DISTINCT a.sendId FROM Messenger AS a"
				+ 	" WHERE a.receiveId = :userId").setParameter("userId", userId).getResultList();
		List<Object> info3 = new ArrayList<>();
		for (int i = 0; i < info1.size(); i++) {
			String sendId = info1.get(i).toString();
			Object info2 = em.createQuery("SELECT DISTINCT a.sendId, a.receiveId,a.msg,a.datetime FROM Messenger AS a"
					+ 	" WHERE a.receiveId = :userId AND a.sendId = :sendId ORDER BY datetime DESC").setParameter("userId", userId).setParameter("sendId", sendId).setMaxResults(1).getResultList();
			info3.add(info2);
		}
		return info3;
	}


}
