package com.epam.test.client.api;

import com.epam.test.client.exception.ServerDataAccessException;
import com.epam.test.dao.User;

import java.util.List;

/**
 * Users Consumer API
 */
public interface UserConsumer {

    /**
     * Get all users list.
     *
     * @return all users list
     */
    List<User> getAllUsers() throws ServerDataAccessException;

    /**
     * Get user by Id.
     *
     * @param userId user identifier.
     * @return user.
     */
    User getUserById(Integer userId) throws ServerDataAccessException;

    /**
     * Get user by login.
     *
     * @param userLogin user login.
     * @return user
     */
    User getUserByLogin(String userLogin) throws ServerDataAccessException;

    /**
     * Create new user.
     *
     * @param user user.
     * @return new user Id.
     */
    int addUser(User user) throws ServerDataAccessException;

    /**
     * Update user.
     *
     * @param user user.
     * @return the number of rows affected.
     */
    int updateUser(User user) throws ServerDataAccessException;

    /**
     * Delete user.
     *
     * @param userId user identifier.
     * @return the number of rows affected.
     */
    int deleteUser(Integer userId) throws ServerDataAccessException;

}
