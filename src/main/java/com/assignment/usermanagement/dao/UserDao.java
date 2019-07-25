package com.assignment.usermanagement.dao;

import com.assignment.usermanagement.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao {

    public void createUser(User user);

    public List<User> listUsers();

    public User findUserById(String id);

    public void updateUser(User user);

    public void deleteUser(String id);
}
