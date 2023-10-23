package br.com.ms.hruser.dto;

import br.com.ms.hruser.entities.Role;
import br.com.ms.hruser.entities.User;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<Role> roles;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = getPassword();
        this.roles = user.getRoles();
    }
}
