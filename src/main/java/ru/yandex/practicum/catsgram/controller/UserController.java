
package ru.yandex.practicum.catsgram.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Set<User> getUsers() {
        Set<User> users = userService.getUsers();
        log.debug("Количество пользователей: {}", users.size());
        return users;
    }

    @PostMapping
    public User postUser(@RequestBody User user) {
        if (user.getEmail() == null || "".equals(user.getEmail())) {
            throw new InvalidEmailException("Invalid user email!");
        }
        log.debug("Создан пользователь: {}", user);
        return userService.createUser(user);
    }

    @PutMapping
    public User putUser(@RequestBody User user) {
        if (user.getEmail() == null || "".equals(user.getEmail())) {
            throw new InvalidEmailException("Invalid user email!");
        }
        return userService.updateUser(user);
    }
}