package com.facade;

import com.model.Dog;
import com.dao.DogDAO;
import java.io.Serializable;
import java.util.List;


public class DogFacade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private DogDAO dogDao = new DogDAO();
	
	public void createDog(Dog dog){
		dogDao.beginTransaction();
		dogDao.save(dog);
		dogDao.commitAndCloseTransaction();
	}
	
	public void updateDog(Dog dog){
		dogDao.beginTransaction();
		Dog persistenceDog = dogDao.find(dog.getId());
		persistenceDog.setAge(dog.getAge());
		persistenceDog.setName(dog.getName());
		dogDao.update(persistenceDog);
		dogDao.commitAndCloseTransaction();
	}
	
	public Dog findDog(int dogId){
		dogDao.beginTransaction();
		Dog dog = dogDao.find(dogId);
		dogDao.closeTransaction();
		return dog;
	}
	
	public List<Dog> listAll(){
		dogDao.beginTransaction();
		List<Dog> result = dogDao.findAll();
		dogDao.closeTransaction();
		return result;
	}
	
	public void delete(Dog dog){
		dogDao.beginTransaction();
		Dog persistenceDog = dogDao.findReferenceOnly(dog.getId());
		dogDao.delete(persistenceDog);
		dogDao.closeTransaction();
	}
	

}
