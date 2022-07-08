
package ru.yandex.practicum.catsgram.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserWithEmailNotFoundException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.List;

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
    public List<User> getUsers() {
        List<User> users = userService.getUsers();
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

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        User user = userService.findUserByEmail(email);
        if (user == null) {
            throw new UserWithEmailNotFoundException();
        }
        return user;
    }
}