package domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
//@Indexed
@Table(name="User")
@Data
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4226055603113841802L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="User_ID")
	long id;
	
	@Column(name="Name")
	String name;
	
	@Column(name="Surname")
	String surname;
	
	@Column(name="Username")
	String username;
	
	@Column(name="Email")
	String email;

	@Column(name="Grade")
	int grade;

	@Column(name="Report")
	int report;

	public User() {}
	
	
	public User(String name, String surname, String username) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.report = 0;
		this.grade = 0;
	}
	
	public User(String name, String surname, String username,String email, int report, int grade) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.report = report;
		this.grade = grade;
	}
	
	public User(long id, String name, String surname, String username, String email, int report, int grade) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.report = report;
		this.grade = grade;

	}
	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username + ", email="
				+ email + ", grade=" + grade + ", report=" + report + "]";
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surName) {
		this.surname = surName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}
}
