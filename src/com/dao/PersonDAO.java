package com.dao;

import java.util.*;

import com.model.Person;

public class PersonDAO extends GenericDAO<Person>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PersonDAO(){
		super(Person.class);
	}
	
	public Person findPersonWithAllDogs(int personId){
		Map<String, Object> paramenters= new HashMap<String, Object>();
		
		paramenters.put("personId", personId);
		
		return super.findOneResult(Person.FIND_BY_ID_WITH_DOGS, paramenters);
	}

	public void delete(Person person){
		super.delete(person.getId(), Person.class);
	}
}
