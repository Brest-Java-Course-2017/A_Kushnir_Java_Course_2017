package com.epam.test.web_app.controllers;

import com.epam.test.dao.User;
import com.epam.test.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Users Controller.
 */
@Controller
public class UsersController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public String defaultPageRedirect() {
        return "redirect:users";
    }

    @GetMapping(value = "/users")
    public String users(Model model) {
        LOGGER.debug(" /users page");
        List usersList = userService.getAllUsers();
        model.addAttribute("usersList", usersList);
        return "users";
    }

    @GetMapping(value = "/user")
    public String editUser(@RequestParam("id") Integer id,
                        Model model) {
        LOGGER.debug("/user(id: {}) page",id);
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/adduser")
    public String createUser() {
        LOGGER.debug("/adduser page");
        return "adduser";
    }

    @PostMapping(value = "/adduserconfirm")
    public String ConfirmCreateUser(@RequestParam("userlogin") String login,
                                    @RequestParam("userpassword") String password,
                                    @RequestParam("userdescription") String description) {
        LOGGER.debug("/ConfirmCreateUser()");
        userService.addUser(new User(login, password, description));
        return "redirect:users";
    }

    @PostMapping(value = "/updateuser")
    public String updateUser(@RequestParam("userid") Integer id,
                                  @RequestParam("userlogin") String login,
                                  @RequestParam("userpassword") String password,
                                  @RequestParam("userdescription") String description) {
        LOGGER.debug("/updateUser()",id);
        User user = userService.getUserById(id);

        user.setLogin(login);
        user.setPassword(password);
        user.setDescription(description);
        userService.updateUser(user);

        return "redirect:users";
    }

    // TODO: fix delete
    @DeleteMapping(value = "/deleteuser")
    public String deleteUser (@RequestParam("id") Integer id) {
        LOGGER.debug("/deleteUser(id: {})",id);
        userService.deleteUser(id);
        return "redirect:users";
    }
}