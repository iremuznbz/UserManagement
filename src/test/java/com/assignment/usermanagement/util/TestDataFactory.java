package com.assignment.usermanagement.util;

import com.assignment.usermanagement.model.User;

public class TestDataFactory {

    public User user() {
        User user = new User();
        user.setId("1");
        user.setFirstName("Billie");
        user.setLastName("Ellish");
        user.setPhone("1234567");
        return user;
    }

    public User user(String id, String firstName, String lastName, String phone) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        return user;
    }
}
