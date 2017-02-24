package com.epam.test.service;

import com.epam.test.dao.User;
import com.epam.test.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by kushnir on 22.2.17.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() throws DataAccessException {
        List<User> usersList = userDao.getAllUsers();
        LOGGER.debug("getAllUsers(): count of users = {} ", usersList.size());
        return usersList;
    }

    @Override
    public User getUserById(Integer userId) throws DataAccessException {
        LOGGER.debug("getUserById(): user id = {} ", userId);
        Assert.notNull(userId, "User ID should not be null.");
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByLogin(String login) throws DataAccessException {
        LOGGER.debug("getUserByLogin(): user login = {} ", login);
        Assert.hasLength(login, "User login should not be null.");
        return userDao.getUserByLogin(login);
    }

    @Override
    public int addUser(User user) throws DataAccessException {
        LOGGER.debug("addUser(): user login = {} ", user.getLogin());
        Assert.notNull(user, "User should not be null.");
        Assert.isNull(user.getUserID(), "User Id should be null.");
        Assert.hasText(user.getLogin(), "User login should not be null.");
        Assert.hasText(user.getPassword(), "User password should not be null.");
        //Assert.isNull(getUserByLogin(user.getLogin()), "User with login: " + user.getLogin() + " - already exist!");
        return userDao.addUser(user);
    }

    @Override
    public int updateUser(User user) throws DataAccessException {
        LOGGER.debug("updateUser(): user login = {} ", user.getLogin());
        Assert.notNull(user, "User should not be null.");
        Assert.notNull(user.getUserID(), "User Id should not be null.");
        Assert.hasText(user.getLogin(), "User login should not be null.");
        Assert.hasText(user.getPassword(), "User password should not be null.");
        Assert.notNull(userDao.getUserById(user.getUserID()), "User does not exist!");
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(Integer userId) throws DataAccessException {
        LOGGER.debug("deleteUser(): user id = {} ", userId);
        Assert.notNull(userId, "User id should not be null.");
        Assert.notNull(userDao.getUserById(userId), "User does not exist!");
        return userDao.deleteUser(userId);
    }
}
