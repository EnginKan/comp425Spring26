package org.yeditepe.week10.service;

import org.springframework.stereotype.Service;
import org.yeditepe.week10.entity.User;
import org.yeditepe.week10.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String name, String email) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(new User(name, email));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
