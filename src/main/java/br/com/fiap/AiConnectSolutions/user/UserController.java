package br.com.fiap.AiConnectSolutions.user;

import br.com.fiap.AiConnectSolutions.user.dto.UserFormRequest;
import br.com.fiap.AiConnectSolutions.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers(@RequestParam(required = false) String name) {
        if (name != null) return userService.getUserByName(name);
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User getUser(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserFormRequest userForm, UriComponentsBuilder uriBuilder){
        var user = userService.createUser(userForm.toModel());
        var uri = uriBuilder
                .path("/users/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(UserResponse.from(user));
    }

}
