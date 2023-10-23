package br.com.ms.hruser.services;

import br.com.ms.hruser.dto.UserResponse;
import br.com.ms.hruser.entities.User;
import br.com.ms.hruser.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponse findById(Long id) {
        User response = this.repository.findById(id).get();
        return new UserResponse(response);
    }

    public UserResponse findByEmail(String email) {
        User response = this.repository.findByEmail(email).get();
        return new UserResponse(response);
    }
}
