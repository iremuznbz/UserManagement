package com.assignment.usermanagement.controller;

import com.assignment.usermanagement.model.User;
import com.assignment.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> list() {
        List<User> users = userService.listUsers();
        return users;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(name = "id") String id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
         userService.createUser(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }
}
