package com.epam.test.client;

import com.epam.test.client.exception.ServerDataAccessException;
import com.epam.test.client.rest.api.UsersConsumer;
import com.epam.test.dao.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Users Consumer with REST.
 */
public class UsersConsumerRest implements UsersConsumer {

    @Value("${user.protocol}://${user.host}:${user.port}/")
    private String url;

    @Value("${point.users}")
    private String urlUsers;

    @Value("${point.user}")
    private String urlUser;

    ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    RestTemplate restTemplate = new RestTemplate(requestFactory);
    {
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());
    }

    @Override
    public List<User> getAllUsers() throws ServerDataAccessException {
        ResponseEntity responseEntity = restTemplate.getForEntity(url + urlUsers, Object.class);
        List<User> users = (List<User>) responseEntity.getBody();
        return users;
    }

    @Override
    public User getUserById(Integer userId) throws ServerDataAccessException {
        ResponseEntity responseEntity = restTemplate.getForEntity(url + urlUser + "/" + userId, Object.class);
        User user = (User) responseEntity.getBody();
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws ServerDataAccessException {
        ResponseEntity responseEntity = restTemplate.getForEntity(url + urlUser + "/login/" + login, Object.class);
        User user = (User) responseEntity.getBody();
        return user;
    }

    @Override
    public int addUser(User user) throws ServerDataAccessException {
        //TODO implement addUser
        return 0;
    }

    @Override
    public int updateUser(User user) throws ServerDataAccessException {
        //TODO implement updateUser fix and check
        restTemplate.put(url + urlUser + "/", user);
        return 0;
    }

    @Override
    public int deleteUser(Integer userId) throws ServerDataAccessException {
        //TODO implement deleteUser fix and check
        restTemplate.delete(url + urlUser + "/login/" + userId);
        return 0;
    }
}
