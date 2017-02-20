package com.epam.test.dao;

/**
 * test DAO implementation
 */
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
// sets the execution order of test
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UserDaoImplTest {

    public static final User testUser = new User(3, "userLogin3","userPassword3","another user");

    @Autowired
    UserDao userDao;

    @Test
    public void A_addUserTest() throws Exception {

        assertEquals("User add result: ", (Integer) 1, userDao.addUser(testUser));
        assertEquals(userDao.getUserById(testUser.getUserID()),testUser);
    }

    @Test
    public void B_updateUserTest() throws Exception {

        testUser.setDescription("updated");
        userDao.updateUser(testUser);
        assertEquals("User update result: ", "updated", userDao.getUserById(testUser.getUserID()).getDescription());
    }

    @Test
    public void C_deleteUserTest() throws Exception {

        userDao.deleteUser(testUser.getUserID());
        assertEquals(2, userDao.getAllUsers().size());
    }

    @Test
    public void D_getAllUsersTest() throws Exception {

        List<User> users = userDao.getAllUsers();
        assertEquals("User get result: ", (Integer)2,  (Integer)users.size());
    }

    @Test
    public void E_getUserByIdTest() throws Exception {

        User user = userDao.getUserById(1);
        assertNotNull(user);
        assertEquals("User get result: ","userLogin1", user.getLogin());
    }
}