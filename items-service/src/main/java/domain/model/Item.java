package domain.model;

import java.io.Serializable;
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
	
	@Column(name="Price")
	int price;
	
	@Column(name="Category")
	String category;
	
	//@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="Description")
	String description;
	
	@Column(name="State")
	int state;
	
	@Column(name="Images")
	String images;
	
	@Column(name="Report")
	int report;
	
	@Column(name="Dates")
	long date;
	
	
	public Item() {}
	
	
	
	public Item(String usrId, String name, int price,String category, String description, int state) {
		this.usrId = usrId;
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.description = description;
		this.category = category;
		this.state = state;
		this.price = price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [id = "+id+ " usrid="+ usrId +" name=" + name + ", price=" + price + ", category=" + category + ", description=" + description
				+ ", state=" + state + "]";
	}

	


}