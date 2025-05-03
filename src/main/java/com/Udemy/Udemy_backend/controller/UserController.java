package com.Udemy.Udemy_backend.controller;

import com.Udemy.Udemy_backend.repository.UserRepository;
import com.Udemy.Udemy_backend.response.AuthResponse;
import com.Udemy.Udemy_backend.SecurityConfig.JwtProvider;
import com.Udemy.Udemy_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String mobile = user.getMobile();
        String role = user.getRole();

        if (userRepository.findByEmail(email) != null) {
            return new ResponseEntity<>(new AuthResponse(null, "Email already in use", false), HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setFullName(fullName);
        newUser.setMobile(mobile);
        newUser.setRole(role);
        newUser.setPassword(passwordEncoder.encode(password));

        userRepository.save(newUser);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token, "Register Success", true);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody User loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtProvider.generateToken(authentication);

            AuthResponse authResponse = new AuthResponse(token, "Login success", true);
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthResponse(null, "Invalid username or password", false), HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/profile")
    public ResponseEntity<User> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Get the username (email)

        User user = userRepository.findByEmail(username); // Fetch user from database using email
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if user not found
        }

        return new ResponseEntity<>(user, HttpStatus.OK);  // Return the user details as a response
    }
}
