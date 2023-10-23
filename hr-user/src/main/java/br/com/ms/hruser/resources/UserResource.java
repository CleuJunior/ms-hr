package br.com.ms.hruser.resources;

import br.com.ms.hruser.dto.UserResponse;
import br.com.ms.hruser.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ms.hruser.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	private final UserService service;

	public UserResource(UserService service) {
		this.service = service;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
		UserResponse obj = this.service.findById(id);
		return ResponseEntity.ok(obj);
	}	
	
	@GetMapping(value = "/search")
	public ResponseEntity<UserResponse> findByEmail(@RequestParam String email) {
		UserResponse obj = this.service.findByEmail(email);
		return ResponseEntity.ok(obj);
	}
}
