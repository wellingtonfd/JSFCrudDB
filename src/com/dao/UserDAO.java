package com.dao;
 
import java.util.*;
 
import com.model.User;
 
public class UserDAO extends GenericDAO<User> {
 
    private static final long serialVersionUID = 1L;
 
    public UserDAO() {
        super(User.class);
    }
 
    public User findUserByEmail(String email){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("email", email);     
 
        return super.findOneResult(User.FIND_BY_EMAIL, parameters);
    }
}