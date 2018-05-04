package de.uni_stuttgart.est.project.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User{
	
	private int AccessLevel;
	@Column(unique = true)
	@Id
	private String UserName;
	private String FirstName;
	private String LastName;
	private String Password;

	public User() {

	}

	public User(int accessLevel, String userName, String firstName, String lastName, String password) {
		AccessLevel = accessLevel;
		UserName = userName;
		FirstName = firstName;
		LastName = lastName;
		Password = password;
	}

	public int getAccessLevel() {
		return AccessLevel;
	}

	public String getFirstName() {
		return FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public String getPassword() {
		return Password;
	}

	public String getUserName() {
		return UserName;
	}

	public void setAccessLevel(int accessLevel) {
		AccessLevel = accessLevel;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}
	public boolean isAdmin() {
		return this.getAccessLevel() == 1;
	}
}
