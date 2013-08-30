package com.model;

import java.util.List;
import java.io.Serializable;

import javax.persistence.*;

/**
 * 
 * @author Igor Moura
 * @since 26/08/2013
 * 
 */

@Entity
//@Table(name="PERSON")
@NamedQuery(name="Person.findByIdWithDogs", query="select p from Person p left join fetch p.dogs where p.id =:personId")

public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_ID_WITH_DOGS = "Person.findByIdWithDogs";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int age;
	private String name;
	
	@ManyToMany
	private List<Dog> dogs;
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person){
			Person person = (Person) obj;
			return person.getId() == id;
		}
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Dog> getDogs() {
		return dogs;
	}

	public void setDogs(List<Dog> dogs) {
		this.dogs = dogs;
	}
	
	

}
