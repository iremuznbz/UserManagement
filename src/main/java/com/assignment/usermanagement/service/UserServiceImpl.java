package com.assignment.usermanagement.service;

import com.assignment.usermanagement.dao.UserDaoImpl;
import com.assignment.usermanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDaoImpl userDao;

    public void createUser(User user) {
        userDao.createUser(user);
    }

    public List<User> listUsers() {
        return userDao.listUsers();
    }

    public User findUserById(String id) {
        return userDao.findUserById(id);
    }

    public void deleteUser(String id) {
        userDao.deleteUser(id);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
