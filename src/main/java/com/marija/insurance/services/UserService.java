package com.marija.insurance.services;

import com.marija.insurance.domain.User;
import com.marija.insurance.exception.UserException;

public interface UserService {
    User login(String username, String password) throws UserException;
}
