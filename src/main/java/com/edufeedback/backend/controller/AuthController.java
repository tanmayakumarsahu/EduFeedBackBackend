package com.edufeedback.backend.controller;

import com.edufeedback.backend.model.User;
import com.edufeedback.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

```
@Autowired
private UserRepository userRepository;

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User loginUser) {

    Optional<User> optionalUser = userRepository.findByEmail(loginUser.getEmail());

    if (optionalUser.isEmpty()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "User not found"));
    }

    User user = optionalUser.get();

    if (!user.getPassword().equals(loginUser.getPassword())) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid password"));
    }

    Map<String, Object> response = new HashMap<>();
    response.put("id", user.getId());
    response.put("name", user.getName());
    response.put("email", user.getEmail());
    response.put("role", user.getRole());
    response.put("token", "session"); // simple token

    return ResponseEntity.ok(response);
}

@PostMapping("/signup")
public ResponseEntity<?> signup(@RequestBody User user) {

    Optional<User> existing = userRepository.findByEmail(user.getEmail());

    if (existing.isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("message", "Email already registered"));
    }

    // Default role if not provided
    if (user.getRole() == null || user.getRole().isEmpty()) {
        user.setRole("student");
    }

    User saved = userRepository.save(user);

    Map<String, Object> response = new HashMap<>();
    response.put("id", saved.getId());
    response.put("name", saved.getName());
    response.put("email", saved.getEmail());
    response.put("role", saved.getRole());
    response.put("token", "session");

    return ResponseEntity.ok(response);
}
```

}
