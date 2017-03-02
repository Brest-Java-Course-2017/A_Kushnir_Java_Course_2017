package com.epam.test.client;

import com.epam.test.client.exception.ServerDataAccessException;
import com.epam.test.client.rest.api.UsersConsumer;
import com.epam.test.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 * REST client console application demo.
 */
@Component
public class DemoApp {

    @Autowired
    UsersConsumer usersConsumer;

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");

        DemoApp demoApp = ctx.getBean(DemoApp.class);
        demoApp.menu();
    }

    private void menu() {

        int swValue = 0;

        System.out.println("=================================");
        System.out.println("|      MENU SELECTION DEMO      |");
        System.out.println("=================================");
        System.out.println("| Options:                      |");
        System.out.println("|        1. Get all users       |");
        System.out.println("|        2. Get user by login   |");
        System.out.println("|        3. Get user by id      |");
        System.out.println("|        4. Delete user by id   |");
        System.out.println("|        5. Exit                |");
        System.out.println("=================================");
        while (swValue != 3) {
            System.out.print("Select option: ");
            if (sc.hasNextInt()) {
                swValue = sc.nextInt();
                checkValue(swValue);
            } else {
                System.out.println("Bad value: " + sc.next());
            }
        }
    }

    private void checkValue(int item) {
        switch (item) {
            case 1:
                getAllUsers();
                break;
            case 2:
                getUserByLogin();
                break;
            case 3:
                getUserByid();
                break;
            case 4:
                deleteUser();
                break;
            case 5:
                System.out.println("Exit.");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    // TODO implement getUserByid() method
    private void getUserByid() {
    }

    private void getUserByLogin() {
        String userLogin = "";
        System.out.print("    Enter user login: ");
        if (sc.hasNextLine()) {
            userLogin = sc.next();
        }

        try {
            User user = usersConsumer.getUserByLogin(userLogin);
            System.out.println("    User: " + user);
        } catch (ServerDataAccessException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }

    private void getAllUsers() {
        List<User> users = usersConsumer.getAllUsers();
        System.out.println("users: " + users);
    }

    private void deleteUser() {
        int userId = 0;
        System.out.print("    Enter user id: ");
        if (sc.hasNextLine()) {
            userId = sc.nextInt();
            checkValue(userId);
        } else {
            System.out.println("Bad value: " + sc.next());
        }

        try {
            int deletestatus = usersConsumer.deleteUser(userId);
            if (deletestatus > 0) System.out.println("    User by id("+ userId +") deleted! ");
        } catch (ServerDataAccessException ex) {
            System.out.println("    User by id("+ userId +") FAIL delete! REST server error - " + ex.getMessage());
        }
    }

    // TODO implement updateUser() method
    // TODO implement addUser() method
}
