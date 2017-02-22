package com.epam.test.dao;

/**
 * test DAO implementation
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional

public class UserDaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String USER_LOGIN_1 = "userLogin1";

    public static final User newTestUser = new User("userLogin3","userPassword3","another user");

    @Autowired
    UserDao userDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        LOGGER.error("execute: setUpBeforeClass()");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        LOGGER.error("execute: tearDownAfterClass()");
    }

    @Before
    public void beforeTest() {
        LOGGER.error("execute: beforeTest()");
    }

    @After
    public void afterTest() {
        LOGGER.error("execute: afterTest()");
    }

    @Test
    public void testAddUser() throws Exception {
        LOGGER.debug("testAddUser()");
        int beforeCountUser = userDao.getAllUsers().size();

        int addedUserId = userDao.addUser(newTestUser);
        assertNotNull("testAddUser: User id not received!", addedUserId);

        User addedUser = userDao.getUserById(addedUserId);
        assertNotNull("testAddUser: User(id: "+addedUserId+") not found!", addedUser);
        assertEquals("testAddUser: users login not equals!",addedUser.getLogin(), newTestUser.getLogin());
        assertEquals("testAddUser: users Password not equals!", addedUser.getPassword(), newTestUser.getPassword());
        assertEquals("testAddUser: users Description not equals!", addedUser.getDescription(), newTestUser.getDescription());
        assertEquals("testAddUser: count of users before and after does not match!", beforeCountUser + 1, userDao.getAllUsers().size());
    }
    /*
    *@Test(expected = org.springframework.dao.DuplicateKeyException.class)
    *public void testAddDuplicateUser() throws Exception {
    *    LOGGER.debug("testAddDuplicateUser()");
    *    User duplicateUser = new User(1,"userLogin1duplicate", "userPassword1duplicate", "descr");
    *    userDao.addUser(duplicateUser);
    *}
    */

    @Test
    public void testUpdateUser() throws Exception {
        LOGGER.debug("testUpdateUser()");
        User user = userDao.getUserById(1);
        assertNotNull("testUpdateUser: user not received!", user);
        user.setLogin("updated login");
        user.setDescription("updated description");

        assertTrue("testUpdateUser: count updated rows = 0!",userDao.updateUser(user) > 0);

        User updatedUser = userDao.getUserById(user.getUserID());
        assertNotNull("testUpdateUser: user not found!", updatedUser);

        assertEquals("testUpdateUser: users Description not equals!", "updated description", updatedUser.getDescription());
        assertEquals("testUpdateUser: users Login not equals!", "updated login", updatedUser.getLogin());
    }

    @Test
    public void testDeleteUser() throws Exception {
        LOGGER.debug("testDeleteUser()");

        User forDeletingUser = new User();
        int idForDeletingUser = userDao.addUser(forDeletingUser);
        assertNotNull("testDeleteUser: idForDeletingUser = null", idForDeletingUser);

        int beforeCountUser = userDao.getAllUsers().size();

        assertEquals("testDeleteUser: count deleted rows = 0!",1, userDao.deleteUser(idForDeletingUser));
        assertEquals("testDeleteUser: count of users before-1 and after does not match!",beforeCountUser - 1, userDao.getAllUsers().size());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        LOGGER.debug("testGetAllUsers()");
        assertTrue("testGetAllUsers: receive size = 0!",userDao.getAllUsers().size() > 0);
    }

    //@Ignore
    @Test
    public void testGetUserById() throws Exception {
        LOGGER.debug("testGetUserById()");
        User user = userDao.getUserById(1);
        assertNotNull("testGetUserByLogin: user = null", user);
        assertEquals("testGetUserByLogin: users Login not equals!", USER_LOGIN_1, user.getLogin());
    }

    @Test
    public void testGetUserByLogin() throws Exception {
        LOGGER.debug("testGetUserByLogin()");
        User user = userDao.getUserByLogin("userLogin1");
        assertNotNull("testGetUserByLogin: user = null", user);
        assertEquals("testGetUserByLogin: users Login not equals!", USER_LOGIN_1, user.getLogin());
    }
}