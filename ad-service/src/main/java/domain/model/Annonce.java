package domain.model;

import java.io.Serializable;
import java.util.UUID;

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
@Table(name="Annonce")
@Data
public class Annonce implements Serializable {






	/**
	 * 
	 */
	private static final long serialVersionUID = 2161064388534170538L;

	@Id
	@Column(name="Wanted_ID")
	String id;
	
	@Id
	@Column(name="User_ID")
	String usrId;
	
	//@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="Name")
	String name;
	
	
	@Column(name="Category")
	String category;
	
	
	@Column(name="State")
	int state;
	
	
	public Annonce() {}
	
	public Annonce(String usrId,String name,String category, int state) {
		this.id = UUID.randomUUID().toString();
		this.usrId = usrId;
		this.name = name;
		this.category = category;
		this.state = state;
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
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "Annonce [id = "+ id +  " userId = " + usrId + " name=" + name + ", category=" + category
				+ ", state=" + state + "]";
	}

}