package domain.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="Messenger")
@Data
public class Messenger implements Serializable {






	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="Message")
	String msg;
	
	@Id
	@Column(name="DateTime")
	Timestamp datetime;
	
	@Id
	@Column(name="Send_Id")
	String sendId;
	
	@Id
	@Column(name="Receive_Id")
	String receiveId;
	
	
	@Column(name="SeenReceive")
	boolean seenreceive;
	
	
	public Messenger() {}
	
	public Messenger(String message, String sendId, String receiveId) {
		this.msg = message;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		this.datetime = timestamp;
		this.sendId = sendId;
		this.receiveId = receiveId;
	}
	
	


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Timestamp getDateTime() {
		return datetime;
	}

	public void setDateTime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	@Override
	public String toString() {
		return "";
	}

}