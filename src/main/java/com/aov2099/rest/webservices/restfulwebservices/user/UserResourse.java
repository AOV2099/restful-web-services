package com.aov2099.rest.webservices.restfulwebservices.user;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        User user = service.findOne(id);
        
        if(user == null)
            throw new UserNotFoundException("id- " + id);

        return user;
    }

    //POST
    //input -details of user
    //output - CREATED & Return the created URI
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user ){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    } 

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        
        if(user == null)
            throw new UserNotFoundException("id- " + id);

    }

}
