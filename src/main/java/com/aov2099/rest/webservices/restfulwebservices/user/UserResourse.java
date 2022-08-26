package com.aov2099.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;;

@RestController
public class UserResourse {

    @Autowired //Creates an istance of the DAO Class
    private UserDaoService service;
    
    //GET /users
    //retrieveAllUsers
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    //GET /users/id
    //retrieveUser(int id)
    @GetMapping("users/{id}")
    public User retrieveUser(@PathVariable int id){
        return service.findOne(id);
    }

    //POST
    //input -details of user
    //output - CREATED & Return the created URI
    @PostMapping("/user")
    public void createUser(@RequestBody User user ){
        User savedUser = service.save(user);
    } 

}
