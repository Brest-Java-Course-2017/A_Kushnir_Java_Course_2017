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
 * Rest User Controller
 */
@CrossOrigin
@RestController
public class UserRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrectDataError() {
        return "{  \"response\" : \"Incorrect Data Error\" }";
    }

    //curl -X GET -v localhost:8088/users
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<User> getAllUsers() {
        LOGGER.debug("/users.getUsers() rest");
        return userService.getAllUsers();
    }

    //curl -X GET -v localhost:8088/user/1
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public @ResponseBody User getUserById(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("/user.getUser(id: {}) rest",id);
        return userService.getUserById(id);
    }

    //curl -X GET -v localhost:8088/user/login/{login}
    @RequestMapping(value = "/user/login/{login}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public @ResponseBody User getUserById(@PathVariable(value = "login") String login) {
        LOGGER.debug("/user.getUser(login: {}) rest", login);
        return userService.getUserByLogin(login);
    }

    //curl -X PUT -v localhost:8088/user -d '{"userID":"1","newlogin":"xyz","newpassword":"xyz","description":"new description"}'
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@RequestBody User user) {
        LOGGER.debug("/user.updateUser(id: {})", user.getUserID());
        userService.updateUser(user);
    }

    //curl -H "Content-Type: application/json" -X POST -d '{"login":"xyz","password":"xyz"}' -v localhost:8088/user
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Integer addUser(@RequestBody User user) {
        LOGGER.debug("/user.addUser(user: {})", user);
        return userService.addUser(user);
    }

    //curl -X DELETE -v localhost:8088/user/2
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser (@PathVariable(value = "id") int id) {
        LOGGER.debug("/user.deleteUser(id: {})", id);
        userService.deleteUser(id);
    }
}
