package domain.model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="Item")
@Data
public class Item implements Serializable {




	/**
	 * 
	 */
	private static final long serialVersionUID = -2820562328962012766L;

	@Id
	@Column(name="Item_ID")
	String id;
	
	@Id
	@Column(name="User_ID")
	String usrId;
	
	//@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="Name")
	String name;
	
	@Column(name="Prize")
	int prize;
	
	@Column(name="Category")
	String category;
	
	//@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="Description")
	String description;
	
	@Column(name="State")
	int state;
	
	@Column(name="Images")
	List<BufferedImage> images;
	
	@Column(name="Report")
	int report;
	
	@Column(name="Dates")
	long date;
	
	
	
	public Item() {}
	
	
	
	public Item(String usrId, String name, int prize,String category, String description, int state) {
		this.usrId = usrId;
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.description = description;
		this.category = category;
		this.state = state;
		this.prize = prize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setUsrId(String id) {
		this.usrId = id;
	}
	
	public String getUsrId() {
		return this.usrId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getPrize() {
		return prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
	}
	
	public List<BufferedImage> getImages(){
		return this.images;
	}

	@Override
	public String toString() {
		return "Item [id = "+id+ " usrid="+ usrId +" name=" + name + ", prize=" + prize + ", category=" + category + ", description=" + description
				+ ", state=" + state + "]";
	}

	


}