package com.epam.test.model;

import junit.framework.Assert;

import static org.junit.Assert.*;

/**
 * Created by kushnir on 13.2.17.
 */
public class UserTest {

    public static final int USER_ID = 11;
    @org.junit.Test
    public void getUserID() throws Exception {
        User user = new User();
        user.setUserID(11);
        Assert.assertEquals("User id: ", (Integer) USER_ID, user.getUserID());
    }

    @org.junit.Test
    public void getLogin() throws Exception {

    }

    @org.junit.Test
    public void getPassword() throws Exception {

    }

    @org.junit.Test
    public void getDescription() throws Exception {

    }

}