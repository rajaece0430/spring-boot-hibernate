package com.raj.spring.orm.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.spring.orm.api.dao.PersonDao;
import com.raj.spring.orm.api.model.Person;

@RestController
@RequestMapping("/spring-boot-orm")
public class PersonController {
	
	@Autowired
	private PersonDao personDao;
	
	@PostMapping("/savePerson")
	public String savePerson(@RequestBody Person person) {
		personDao.savePerson(person);
		return "Succes";
	}
	
	@GetMapping("/getAllPersons")
	public List<Person> getAllPersons(){
		return personDao.getPersons();
	}
	
	@GetMapping("/request-header")
	public String getLang(@RequestHeader(value="Accept-Language") String lang){
		return lang;
	}
	
	@GetMapping("/greeting/{name}")
	public String greeting(@PathVariable String name){
		return name;
	}
	
	@GetMapping("/greetings/{time}/{name}")
	public ResponseEntity<String> greetings(@PathVariable String time,
				@PathVariable String name){
		List<String> times = List.of("evening","morning");
		
		if(!times.contains(time)) {
			return ResponseEntity.badRequest().body("Time should be morning/Evening");
		}
		return ResponseEntity.ok("Good "+time+", "+name);
	}
}
