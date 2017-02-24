package com.epam.test.service;

import com.epam.test.dao.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kushnir on 22.2.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-test.xml"})
@Transactional
public class UserServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    UserService userService;

    private static final Integer USER_ID = 1;
    private static final String USER_LOGIN_1 = "userLogin1", USER_PASSWORD_1 = "userPassword1";
    private static final User testUser = new User("testUserLogin","testUserPassword","testUserDescription");

    @Test
    public void testGetAllUsers() throws Exception {
        LOGGER.debug("testGetAllUsers()");
        List<User> users = userService.getAllUsers();
        Assert.assertTrue("Count users = 0",users.size() > 0);
        Assert.assertEquals("Count users expected", 2, users.size());
    }

    @Test
    public void testGetUserById() throws Exception {
        LOGGER.debug("testGetUserById({})", USER_ID);
        User user = userService.getUserById(USER_ID);
        Assert.assertNotNull("User is null",user);
        Assert.assertNotNull("User login is null", user.getLogin());
        Assert.assertEquals("Users login not equals", USER_LOGIN_1, user.getLogin());
    }

    @Test
    public void testGetUserByLogin() throws Exception {
        LOGGER.debug("testGetUserByLogin({})", USER_LOGIN_1);
        User user = userService.getUserByLogin(USER_LOGIN_1);
        Assert.assertNotNull("User is null", user);
        Assert.assertNotNull("User login is null", user.getLogin());
        Assert.assertEquals("Users passwords not equals", USER_PASSWORD_1, user.getPassword());
    }

    @Test
    public void testAddUser() throws Exception {
        LOGGER.debug("testAddUser()");
        int countUsersBefore = userService.getAllUsers().size();
        int testUserId = userService.addUser(testUser);
        Assert.assertNotNull(testUserId);
        Assert.assertNotEquals(countUsersBefore, userService.getAllUsers().size());
        userService.deleteUser(testUserId);
    }

    @Test
    public void testUpdateUser() throws Exception {
        LOGGER.debug("testUpdateUser()");
        User userBefore = userService.getUserById(USER_ID);
        Assert.assertNotNull(userBefore);

        userBefore.setLogin("updatedLogin");
        userBefore.setPassword("updatedPassword");
        userBefore.setDescription("updated");

        Assert.assertTrue(userService.updateUser(userBefore) > 0);

        User userAfter = userService.getUserById(USER_ID);
        Assert.assertNotNull(userAfter);

        Assert.assertEquals(userBefore.getLogin() , userAfter.getLogin());
        Assert.assertEquals(userBefore.getPassword() , userAfter.getPassword());
        Assert.assertEquals(userBefore.getDescription() , userAfter.getDescription());
    }

    @Test
    public void testDeleteUser() throws Exception {
        int idUserForDelete = userService.addUser(new User("Delete","DeletePass","Delete"));
        int countUsersBefore = userService.getAllUsers().size();
        Assert.assertTrue(userService.deleteUser(idUserForDelete) > 0);
        Assert.assertNotEquals(countUsersBefore, userService.getAllUsers().size());
    }
}
