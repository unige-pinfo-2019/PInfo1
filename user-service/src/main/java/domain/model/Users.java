package domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
//@Indexed
@Table(name="Users")
@Data
public class Users implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4226055603113841802L;
	
	@Id
	@Column(name="User_ID")
	String id;
	
	@Column(name="Name")
	String name;
	
	@Column(name="Surname")
	String surname;
	
	@Column(name="Email")
	String email;

	@Column(name="Image")
	String image;
	
	@Column(name="Report")
	int report;
	
	@Column(name="User_report_ID")
	String userReport;

	public Users() {}
	
	public Users(String id,String name, String surname, String email,int report) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.report = report;
	}
	
	public Users(String id, String image, int report, String userReport) {
		this.id = id;
		this.report = report;
		this.image = image;
		this.userReport = userReport;
	}
	
	public Users(String id, String image, int report) {
		this.id = id;
		this.report = report;
		this.image = image;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public int getReport() {
		return report;
	}


	public void setReport(int report) {
		this.report = report;
	
	}


	public String getUserReport() {
		return userReport;
	}


	public void setUserReport(String userReport) {
		this.userReport = userReport;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
