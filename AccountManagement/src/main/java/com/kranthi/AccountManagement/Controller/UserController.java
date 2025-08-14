package com.kranthi.AccountManagement.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kranthi.AccountManagement.Model.Users;
import com.kranthi.AccountManagement.Service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Users> createUsers(@Valid @RequestBody Users user){
		return ResponseEntity.status(201).body(service.createUsers(user));
	}
	
	@GetMapping
	public List<Users> getAllUsers(){
		return service.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public Users getUserById(@PathVariable Long id) {
		return service.getUserById(id);
	}
	
	@PutMapping("/{id}")
	public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
		return service.updateUser(id, user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		service.deleteUser(id);
		return ResponseEntity.noContent().build();
		
	}
}
