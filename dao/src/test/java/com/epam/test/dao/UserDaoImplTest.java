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
// sets the execution order of test
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
        LOGGER.debug("test: addUser()");
        int beforeCountUser = userDao.getAllUsers().size();

        int addedUserId = userDao.addUser(newTestUser);
        assertNotNull(addedUserId);

        User addedUser = userDao.getUserById(addedUserId);
        assertNotNull(addedUser);

        assertEquals(addedUser.getLogin(), newTestUser.getLogin());
        assertEquals(addedUser.getPassword(), newTestUser.getPassword());
        assertEquals(addedUser.getDescription(), newTestUser.getDescription());

        assertEquals(beforeCountUser + 1, userDao.getAllUsers().size());
    }

    @Test(expected = org.springframework.dao.DuplicateKeyException.class)
    public void testAddDuplicateUser() throws Exception {
        LOGGER.debug("test: testAddDuplicateUser()");
        User duplicateUser = new User(1,"userLogin1duplicate", "userPassword1duplicate", "descr");
        userDao.addUser(duplicateUser);
    }

    @Test
    public void testUpdateUser() throws Exception {
        LOGGER.debug("test: updateUser()");
        User user = userDao.getUserById(1);
        assertNotNull(user);
        user.setLogin("updated login");
        user.setDescription("updated description");

        assertEquals(1, userDao.updateUser(user));
        User updatedUser = userDao.getUserById(user.getUserID());
        assertNotNull(updatedUser);

        assertEquals("User description update result: ", "updated description", updatedUser.getDescription());
        assertEquals("User login update result: ", "updated login", updatedUser.getLogin());
    }

    @Test
    public void testDeleteUser() throws Exception {
        LOGGER.debug("test: deleteUser()");

        User forDeletingUser = new User();
        int idForDeletingUser = userDao.addUser(forDeletingUser);
        assertNotNull(idForDeletingUser);

        int beforeCountUser = userDao.getAllUsers().size();

        assertEquals(1, userDao.deleteUser(idForDeletingUser));
        assertEquals(beforeCountUser - 1, userDao.getAllUsers().size());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        LOGGER.debug("test: getAllUsers()");
        assertTrue(userDao.getAllUsers().size() > 0);
    }

    //@Ignore
    @Test
    public void testGetUserById() throws Exception {
        LOGGER.debug("test: getUserById()");
        User user = userDao.getUserById(1);
        assertNotNull(user);
        assertEquals("User get result: ", USER_LOGIN_1, user.getLogin());
    }

    @Test
    public void testGetUserByLogin() throws Exception {
        LOGGER.debug("test: getUserByLogin()");
        User user = userDao.getUserByLogin("userLogin1");
        assertNotNull(user);
        assertEquals("User get result: ", USER_LOGIN_1, user.getLogin());
    }
}