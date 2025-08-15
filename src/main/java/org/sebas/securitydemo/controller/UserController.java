package org.sebas.securitydemo.controller;

import org.apache.catalina.User;
import org.sebas.securitydemo.model.Users;
import org.sebas.securitydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for user operations - registration, login, and user management
 */
@RestController
public class UserController {
    @Autowired
    private UserService service;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(
            12
    );

    /**
     * Register new user - encrypts password before saving
     */
    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return service.register(user);
    }

    /**
     * Get list of all users - requires JWT authentication
     */
    @GetMapping("/get-users")
    public List<Users> usersList(){
        return service.usersList();
    }

    /**
     * User login - returns JWT token if successful, "fail" if not
     */
    @PostMapping("/login")
    public String login(@RequestBody Users user){
        System.out.println(user);

        return service.verify(user);
    }

}
