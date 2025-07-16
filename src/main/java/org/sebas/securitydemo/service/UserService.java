package org.sebas.securitydemo.service;

import org.apache.catalina.User;
import org.sebas.securitydemo.model.Users;
import org.sebas.securitydemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    public Users register(Users user){
        repo.save(user);
        return user;
    }

    public List<Users> usersList() {
        return repo.findAll();
    }
}
