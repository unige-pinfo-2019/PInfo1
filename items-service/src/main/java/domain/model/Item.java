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

	@Column(name="Name")
	String name;

	@Column(name="Price")
	int price;

	@Column(name="Category")
	String category;

	@Column(name="Description")
	String description;


	@Column(name="State")
    String state;

	@Column(name="Images")
	String images;


	@Column(name="Report")
	int report;

	@Column(name="Dates")
	long date;

	@Column(name="Sold")
	boolean sold;


	public Item() {}


	public Item(String usrId, String name, int price,String category, String description, String state) {
		this.usrId = usrId;
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.description = description;
		this.category = category;
		this.state = state;
		this.price = price;
	}
	
	public Item(String usrId, String name, int price,String category, String description, String state, String image) {
		this(usrId, name, price, category, description, state);
		this.images = image;
	}

	public Item(String id,String usrId, String name, int price,String category, String description, String... stateImg) {
		this.usrId = usrId;
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		if (stateImg.length > 0)
			this.state = stateImg[0];	
		if (stateImg.length > 1)
			this.images = stateImg[1];

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


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean getSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}
	
	public String getImages() {
		return images;
	}


	public void setImages(String images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Item [id = "+id+ " usrid="+ usrId +" name=" + name + ", price=" + price + ", category=" + category + ", description=" + description
				+ ", state=" + state + "]";
	}




}
