package com.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * 
 * @author Igor Moura
 * @since 26/08/2013	
 *
 */

@Entity
@Table(name="USERS")
@NamedQuery(name="User.findUserByEmail", query = "select u from User u where u.email =:email")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_EMAIL = "User.findUserByEmail";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique=true)
	private String email;
	private String password;
	private String name;
	@Enumerated(EnumType.STRING)
	private Role role;

	// Administrator
	public boolean isAdmin(){
		return Role.ADMIN.equals(role);
	}
	
	//Common User 
	public boolean isUser(){
		return Role.USER.equals(role);
	}
	
	
	//HASH CODE
	@Override
	public int hashCode() {
		return getId();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User){
			User user = (User) obj;
			return user.getId() == id;
		}
		return false;
	}
	
	
	
	// GETTER AND SETTER
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}


}
