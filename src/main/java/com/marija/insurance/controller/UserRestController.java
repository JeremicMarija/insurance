package com.marija.insurance.controller;

import com.marija.insurance.domain.User;
import com.marija.insurance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;


    @PostMapping("login")
    public @ResponseBody
    ResponseEntity<User> login(@RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.login(user.getUsername(), user.getPassword()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
