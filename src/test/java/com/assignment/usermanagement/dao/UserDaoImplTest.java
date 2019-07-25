package com.assignment.usermanagement.dao;

import com.assignment.usermanagement.model.User;
import com.assignment.usermanagement.util.TestDataFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    @Autowired
    MongoTemplate mongoTemplate;

    private TestDataFactory factory = new TestDataFactory();

    @Test
    public void listUsers_when_db_is_empty() {
        mongoTemplate.remove(factory.user());
        List<User> users = userDao.listUsers();
        assertEquals(0, users.size());
    }

    @Test
    public void createUser() {
        userDao.createUser(factory.user("6","Kenan","Dogulu","123"));
        User user = userDao.findUserById("6");
        assertNotNull(user);
        assertEquals("Kenan", user.getFirstName());
    }

    @Test
    public void listUsers_when_there_is_record_in_db() {
        userDao.createUser(factory.user());

        List<User> users = userDao.listUsers();
        assertEquals(1, users.size());
    }

    @Test
    public void findUserById() {
        userDao.createUser(factory.user("5","Queen","","10"));

        User user = userDao.findUserById("5");
        assertEquals("5", user.getId());
        assertEquals("Queen", user.getFirstName());
        assertEquals("", user.getLastName());
        assertEquals("10", user.getPhone());

    }

    @Test
    public void findUserById_when_user_not_found_returns_null() {
        User user = userDao.findUserById("111");
        assertNull(user);
    }

    @Test
    public void updateUser() {
        userDao.createUser(factory.user("7","Nazan","Oncel","99"));

        userDao.updateUser(factory.user("7", "Haluk", "Levent", "333"));
        User user = userDao.findUserById("7");
        assertEquals("7", user.getId());
        assertEquals("Haluk", user.getFirstName());
        assertEquals("Levent", user.getLastName());
        assertEquals("333", user.getPhone());
    }

    @Test
    public void deleteUser() {
        userDao.createUser(factory.user("4","Yeni","Turku","44"));
        userDao.deleteUser("4");
        User user = userDao.findUserById("4");
        assertNull(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteUser_when_user_not_found_throws_exception() {
        userDao.deleteUser("4");
    }
}