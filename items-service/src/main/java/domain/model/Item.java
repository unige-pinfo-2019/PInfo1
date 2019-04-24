package domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//import org.hibernate.search.annotations.Analyze;
//import org.hibernate.search.annotations.Field;
//import org.hibernate.search.annotations.Index;
//import org.hibernate.search.annotations.Indexed;
//import org.hibernate.search.annotations.Store;

@Entity
//@Indexed
@Table(name="Item")
@Data
public class Item implements Serializable {




	/**
	 * 
	 */
	private static final long serialVersionUID = -2820562328962012766L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Item_ID")
	long id;
	
	@Id
	@Column(name="User_ID")
	long usrId;
	
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
	String images;
	
	@Column(name="Report")
	String report;
	
	@Column(name="Date")
	long date;
	
	
	public Item() {}
	
	
	public Item( String name, int prize,String category, String description, int state) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.state = state;
		this.prize = prize;
	}
	
	public Item( long id,String name, int prize,String category, String description, int state) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.state = state;
		this.prize = prize;
	}
	
	public Item(long id,long usrId, String name, int prize,  String category,String description, int state, String images, String report, long date ) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.state = state;
		this.prize = prize;
		this.usrId = usrId;
		this.images = images;
		this.report = report;
		this.date = date;
				
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Item [id = "+id+" name=" + name + ", prize=" + prize + ", category=" + category + ", description=" + description
				+ ", state=" + state + "]";
	}

	


}