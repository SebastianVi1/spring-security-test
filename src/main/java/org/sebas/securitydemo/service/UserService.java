package org.sebas.securitydemo.service;

import org.sebas.securitydemo.model.Users;
import org.sebas.securitydemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service containing business logic for user operations
 * Acts as intermediary between controller and repository
 */
@Service
public class UserService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    /**
     * Register new user in database
     */
    public Users register(Users user){
        repo.save(user);
        return user;
    }

    /**
     * Get list of all users
     */
    public List<Users> usersList() {
        return repo.findAll();
    }

    /**
     * Verify user credentials and generate JWT token if valid
     */
    public String verify(Users user) {
        Authentication authentication =
                authManager.authenticate( new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if (authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
        return "fail";
    }
}
