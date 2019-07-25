package com.assignment.usermanagement.controller;

import com.assignment.usermanagement.model.User;
import com.assignment.usermanagement.service.UserService;
import com.assignment.usermanagement.util.TestDataFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private TestDataFactory factory = new TestDataFactory();
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);
        when(userService.listUsers()).thenReturn(Arrays.asList(factory.user(), factory.user("2", "Ceylan", "Ertem", "222")));
    }

    @Test
    public void list() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
        MvcResult result = mockMvc.perform(get("/api/user/list"))
                .andExpect(status().isOk()).andReturn();

        assertEquals("[{\"id\":\"1\",\"firstName\":\"Billie\",\"lastName\":\"Ellish\",\"phone\":\"1234567\"},{\"id\":\"2\",\"firstName\":\"Ceylan\",\"lastName\":\"Ertem\",\"phone\":\"222\"}]", result.getResponse().getContentAsString());
        assertNotNull(result);
        verify(userService, times(1)).listUsers();
    }

    @Test
    public void deleteUser() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
        mockMvc.perform(delete("/api/user/1/delete"))
                .andExpect(status().isOk()).andReturn();
        verify(userService, times(1)).deleteUser("1");
    }

    @Test
    public void createUser() throws Exception {
        final User user = factory.user();

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
        mockMvc.perform(post("/api/user/create")
                .content(mapper.writeValueAsString(user))
                .contentType("application/json"))
                .andExpect(status().isOk()).andReturn();
        verify(userService, times(1)).createUser(Mockito.any(User.class));
    }

    @Test
    public void updateUser() throws Exception {
        final User user = factory.user();

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
        mockMvc.perform(post("/api/user/update")
                .content(mapper.writeValueAsString(user))
                .contentType("application/json"))
                .andExpect(status().isOk()).andReturn();

        verify(userService, times(1)).updateUser(Mockito.any(User.class));
    }
}