package com.assignment.usermanagement.service;

import com.assignment.usermanagement.model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    List<User> listUsers();

    User findUserById(String id);

    void deleteUser(String id);

    void updateUser(User user);
}
