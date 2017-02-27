package com.epam.test.rest;

import com.epam.test.dao.User;
import com.epam.test.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kushnir on 26.2.17.
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private UserService userService;

    //curl -v localhost:8080/users
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getUsers() {
        LOGGER.debug("/users.getUsers() rest");
        return userService.getAllUsers();
    }

    //curl -v localhost:8080/user/userLogin1
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody User getUser(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("/user.getUser(id: {}) rest",id);
        return userService.getUserById(id);
    }

    //curl -X PUT -v localhost:8080/user/2/l1/p1/d1
    @RequestMapping(value = "/user/{id}/{login}/{password}/{desc}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateUser(@PathVariable(value = "id") int id,
                           @PathVariable(value = "desc") String desc,
                           @PathVariable(value = "login") String login,
                           @PathVariable(value = "password") String password) {
        LOGGER.debug("/user.updateUser(id: {})", id);
        userService.updateUser(new User(id, login, password, desc));
    }

    //curl -H "Content-Type: application/json" -X POST -d '{"login":"xyz","password":"xyz"}' -v localhost:8080/user
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Integer addUser(@RequestBody User user) {
        LOGGER.debug("/user.addUser(user: {})", user);
        return userService.addUser(user);
    }
}
