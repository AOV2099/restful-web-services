package com.aov2099.rest.webservices.restfulwebservices.helloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	//GET
	//URI - /hello world
	//method - "Hello world"
	//@RequestMapping(method = RequestMethod.GET, path = "/hello-world" )
	@GetMapping(path = "/hello-world" )
	public String HelloWorld() {
		return "Hello world";
	}
	
	//hello-wold-bean
	@GetMapping(path = "/hello-world-bean" )
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world Bean");
	}

	//http://localhost:8080/hello-world/path-variable/Moy
	@GetMapping(path = "/hello-world/path-variable/{name}" )
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello world -> %s", name));
	}

}
