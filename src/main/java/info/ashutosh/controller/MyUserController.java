package info.ashutosh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.ashutosh.domain.MyUser;
import info.ashutosh.repository.MyUserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class MyUserController {

	@Autowired
	private MyUserService userService;

	@GetMapping
	public List<MyUser> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public MyUser getUser(@PathVariable Long id) {
		return userService.getUserById(id).orElse(null);
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody MyUser user) {
		// The 'user' parameter will be automatically populated with JSON data from the
		// request body.
		// You can work with 'user' as a Java object.
		MyUser createUser = userService.createUser(user);
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public MyUser updateUser(@PathVariable Long id, @Valid @RequestBody MyUser updatedUser) {
		return userService.updateUser(id, updatedUser);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
