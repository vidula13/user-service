package com.example.userservice.service.implementation;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String email) {
        Optional<User> optUser = userRepository.findByEmail(email);
        return optUser.get();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(String email){
        userRepository.deleteByEmail(email);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
