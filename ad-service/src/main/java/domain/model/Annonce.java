package domain.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="Annonce")
@Data
public class Annonce implements Serializable {
	private static final long serialVersionUID = 2161064388534170538L;

	@Id
	@Column(name="Wanted_ID")
	String id;

	@Id
	@Column(name="User_ID")
	String usrId;

	@Column(name="Name")
	String name;


	@Column(name="Category")
	String category;


	@Column(name="State")
	String state;

	@Column(name="Description")
	String desc;

	@Column(name="Sold")
	boolean sold;


	public Annonce() {}

	public Annonce(String usrId,String name,String category, String state, String desc) {
		this.id = UUID.randomUUID().toString();
		this.usrId = usrId;
		this.name = name;
		this.category = category;
		this.state = state;
		this.desc = desc;
		this.sold = false;
	}

	public Annonce(String id, String usrId,String name,String category, String state, String desc) {
		this.id = id;
		this.usrId = usrId;
		this.name = name;
		this.category = category;
		this.state = state;
		this.desc = desc;
		this.sold = false;
	}


	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String id) {
		this.usrId = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setDescription(String desc) {
		this.desc = desc;
	}

	public String getDescription() {
		return desc;
	}

	public boolean getSold() {
		return this.sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}


	@Override
	public String toString() {
		return "Annonce [id = "+ id +  " userId = " + usrId + " name=" + name + ", category=" + category
				+ ", state=" + state + "]";
	}

}
