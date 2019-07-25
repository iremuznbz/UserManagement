package com.assignment.usermanagement.service;

import com.assignment.usermanagement.dao.UserDaoImpl;
import com.assignment.usermanagement.model.User;
import com.assignment.usermanagement.util.TestDataFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDaoImpl userDao;

    private TestDataFactory factory = new TestDataFactory();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createUser() {
        final User user = factory.user();
        userService.createUser(user);
        verify(userDao, times(1)).createUser(user);
    }

    @Test
    public void listUsers() {
        when(userDao.listUsers()).thenReturn(Arrays.asList(factory.user()));

        List<User> userList = userService.listUsers();

        assertEquals(1, userList.size());
        verify(userDao, times(1)).listUsers();
    }

    @Test
    public void findUserById() {

        final User user = factory.user("2", "Michael", "Jackson", "55555");
        when(userDao.findUserById("2")).thenReturn(user);

        User found = userService.findUserById("2");
        assertEquals("2", found.getId());
        assertEquals("Michael", found.getFirstName());
        assertEquals("Jackson", found.getLastName());
        assertEquals("55555", found.getPhone());

        verify(userDao, times(1)).findUserById("2");
    }

    @Test
    public void deleteUser() {
        //when(userDao.findUserById("2")).thenReturn(factory.user("2", "Calvin", "Harris", "55555555"));

        userService.deleteUser("2");
        verify(userDao, times(1)).deleteUser("2");
    }

    @Test
    public void updateUser() {
        final User user = factory.user();
        userService.updateUser(user);
        verify(userDao, times(1)).updateUser(user);
    }
}