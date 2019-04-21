package domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Item")
public class Item implements Serializable {




	/**
	 * 
	 */
	private static final long serialVersionUID = -2820562328962012766L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Item_ID")
	long id;
	
	@Column(name="TITLE")
	String title;
	
	@Column(name="DESCRIPTION")
	String description;
	
	@Column(name="CATEGORY")
	String category;
	
	@Column(name="STATE")
	String state;
	
	@Column(name="PRIZE")
	int prize;
	
	public Item() {}
	
	public Item( String title, String description, String category, String state, int prize) {
		this.title = title;
		this.description = description;
		this.category = category;
		this.state = state;
		this.prize = prize;
	}
	
	public Item(long id, String title, String description, String category, String state, int prize) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
		this.state = state;
		this.prize = prize;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getPrize() {
		return prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", description=" + description + ", category=" + category
				+ ", state=" + state + ", prize=" + prize + "]";
	}


}