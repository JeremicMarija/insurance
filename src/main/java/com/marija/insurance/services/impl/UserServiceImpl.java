package com.marija.insurance.services.impl;

import com.marija.insurance.domain.User;
import com.marija.insurance.exception.UserException;
import com.marija.insurance.repository.UserRepository;
import com.marija.insurance.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) throws UserException {
        User user = userRepository.login(username,password);
        if (user == null) {
            throw new UserException("Invalid username/password!");
        }
        return user;
    }
}
