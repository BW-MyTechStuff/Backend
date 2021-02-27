package com.buildweek.usemytechstuff.controllers;

import com.buildweek.usemytechstuff.models.User;
import com.buildweek.usemytechstuff.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController
{
    /**
     * Using the User service to process user data
     */
    @Autowired
    private UserService userService;

    /**
     * Returns a list of all users
     * <br>Example: <a href="http://localhost:2019/users/users">http://localhost:2019/users/users</a>
     *
     * @return JSON list of all users with a status of OK
     * @see UserService#findAll() UserService.findAll()
     */
    @PreAuthorize(value = "hasAnyRole('OWNER', 'RENTER')")
    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    /**
     * Returns a single user based off a user id number
     * <br>Example: http://localhost:2019/users/user/7
     *
     * @param userId The primary key of the user you seek
     * @return JSON object of the user you seek
     * @see UserService#findByUserId(long) (long) UserService.findByUserId(long)
     */
    @GetMapping(value = "/user/{userId}", produces = "application/json")
    public ResponseEntity<?> getUserById(@PathVariable Long userId)
    {
        User user = userService.findByUserId(userId);
        return new ResponseEntity<>(user,
            HttpStatus.OK);
    }
}
