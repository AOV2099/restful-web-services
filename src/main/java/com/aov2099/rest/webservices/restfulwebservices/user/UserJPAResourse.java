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
import java.util.List;
import java.util.Optional;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;


@RestController
public class UserJPAResourse {

    @Autowired //Creates an istance of the DAO Class
    private UserDaoService service;

    @Autowired
    private UserRepository userRepository;
    
    //GET /users
    //retrieveAllUsers
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    //GET /users/id
    //retrieveUser(int id)
    @GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent())
			throw new UserNotFoundException("id-" + id);

		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		Resource<User> resource = new Resource<User>(user.get());

		//ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		//resource.add(linkTo.withRel("all-users"));

		// HATEOAS

		return resource;
    }

    //POST
    //input -details of user
    //output - CREATED & Return the created URI
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser( @RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

}
