package com.facade;

import com.dao.PersonDAO;
import com.model.Person;
import com.model.Dog;
import com.dao.DogDAO;

import java.io.Serializable;
import java.util.List;

public class PersonFacade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private PersonDAO personDao = new PersonDAO();
	private DogDAO dogDao = new DogDAO();
	
	public void createPerson(Person person){
		personDao.beginTransaction();
		personDao.save(person);
		personDao.commitAndCloseTransaction();
	}

	public void updatePerson(Person person){
		personDao.beginTransaction();
		Person persistedPerson = personDao.find(person.getId());
		persistedPerson.setAge(person.getAge());
		persistedPerson.setName(person.getName());
		personDao.commitAndCloseTransaction();
	}
	
	public void deletePerson(Person person){
		personDao.beginTransaction();
		Person persistedPerson = personDao.findReferenceOnly(person.getId());
		personDao.delete(persistedPerson);
		personDao.commitAndCloseTransaction();
	}
	
	public Person findPerson(int personId){
		personDao.beginTransaction();
		Person person = personDao.find(personId);
		personDao.closeTransaction();
		return person;
	}
	
	public List<Person> listAll(){
		personDao.beginTransaction();
		List<Person> result = personDao.findAll();
		personDao.closeTransaction();
		return result;
	}
	
	public Person findPersonWithAllDogs (int personId){
		personDao.beginTransaction();
		Person person = personDao.findPersonWithAllDogs(personId);
		personDao.closeTransaction();
		return person;
	}
	
	public void addDogToPerson(int dogId, int personId) {
        personDao.beginTransaction();
        personDao.joinTransaction();
        Dog dog = dogDao.find(dogId);
        Person person = personDao.find(personId);
        person.getDogs().add(dog);
        dog.getPersons().add(person);
        personDao.commitAndCloseTransaction();
    }
 
    public void removeDogFromPerson(int dogId, int personId) {
    	personDao.beginTransaction();
    	personDao.joinTransaction();
        Dog dog = dogDao.find(dogId);
        Person person = personDao.find(personId);
        person.getDogs().remove(dog);
        dog.getPersons().remove(person);
        personDao.commitAndCloseTransaction();
    }

	
}
