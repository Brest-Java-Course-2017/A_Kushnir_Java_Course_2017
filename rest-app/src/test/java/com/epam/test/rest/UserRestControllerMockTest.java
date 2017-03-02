package com.epam.test.rest;

import com.epam.test.dao.User;
import com.epam.test.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Mock tests for UserRestController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:test-spring-rest-mock.xml")
public class UserRestControllerMockTest {

    @Resource
    private UserRestController userController;
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(userController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        verify(userService);
        reset(userService);
    }

    @Test
    public void getAllUsersTest() throws Exception {
        expect(userService.getAllUsers())
                .andReturn(Arrays.<User>asList(new User(1,"l", "p", "d")));
        replay(userService);

        mockMvc.perform(
                get("/users")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("[{\"userID\":1,\"login\":\"l\",\"password\":\"p\",\"description\":\"d\"}]"));
    }

    @Test
    public void getUserByIdTest() throws Exception {
        expect(userService.getUserById(anyInt()))
                .andReturn(new User(1,"l", "p", "d"));
        replay(userService);

        mockMvc.perform(
                get("/user/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isFound())
                .andExpect(content()
                        .string("{\"userID\":1,\"login\":\"l\",\"password\":\"p\",\"description\":\"d\"}"));
    }

    @Test
    public void getUserByLoginTest() throws Exception {
        expect(userService.getUserByLogin(anyString()))
                .andReturn(new User(1,"l", "p", "d"));
        replay(userService);

        mockMvc.perform(
                get("/user/login/userLogin1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isFound())
                .andExpect(content()
                        .string("{\"userID\":1,\"login\":\"l\",\"password\":\"p\",\"description\":\"d\"}"));
    }

    @Test
    public void addUserTest() throws Exception {
        expect(userService.addUser(anyObject(User.class)))
                .andReturn(3);
        replay(userService);

        String user = new ObjectMapper()
                .writeValueAsString(new User("l", "p", "d"));

        mockMvc.perform(
                post("/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("3"));
    }

    @Test
    public void updateUserTest() throws Exception {
        expect(userService.updateUser(anyObject(User.class)))
                .andReturn(1);
        replay(userService);

        String user = new ObjectMapper()
                .writeValueAsString(new User(1,"l", "p", "d"));

        mockMvc.perform(
                put("/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user)
        ).andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string(""));
    }

    @Test
    public void deleteUserTest() throws Exception {
        expect(userService.deleteUser(anyInt()))
                .andReturn(1);
        replay(userService);

        mockMvc.perform(
                delete("/user/1")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
