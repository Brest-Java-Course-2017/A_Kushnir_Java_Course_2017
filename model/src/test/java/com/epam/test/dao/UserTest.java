package com.epam.test.dao;

import junit.framework.Assert;

/**
 * Created by kushnir on 13.2.17.
 * This is a Test
 */
public class UserTest {

    public static final int USER_ID = 11;
    public static final String USER_LOGIN = "Login";
    public static final String USER_PASSWORD = "Password";
    public static final String USER_DESCRIPTION = "Description";
    User user = new User();

    @org.junit.Test
    public void getUserID() throws Exception {
        user.setUserID(11);
        // ожидаемое значение
        Assert.assertEquals("User id: ", (Integer) USER_ID, user.getUserID());
    }

    @org.junit.Test
    public void getLogin() throws Exception {
        user.setLogin("Login");
        // ожидаемое значение
        Assert.assertEquals("User id: ", USER_LOGIN, user.getLogin());
    }

    @org.junit.Test
    public void getPassword() throws Exception {
        user.setPassword("Password");
        // ожидаемое значение
        Assert.assertEquals("User password: ", USER_PASSWORD, user.getPassword());
    }

    @org.junit.Test
    public void getDescription() throws Exception {
        user.setDescription("Description");
        // ожидаемое значение
        Assert.assertEquals("User Description: ", USER_DESCRIPTION, user.getDescription());
    }
}