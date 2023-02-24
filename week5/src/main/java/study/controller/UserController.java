package study.controller;

import study.service.UserService;

public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Object readUserByName(String name) {
        return userService.readUserByName(name);
    }

    public Object readUserByAge(String age) {
        try {
            int parse = Integer.parseInt(age);
            return userService.readUserByAge(parse);
        } catch (NumberFormatException e) {
            System.out.println("error: " + e.getMessage());
        }
        return null;
    }

    public Object readUserAll() {
        return userService.readUserAll();
    }

    public Object sumAgeByEmailContains(String email) {
        return userService.sumAgeByEmailContains(email);
    }
}
