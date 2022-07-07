
package ru.yandex.practicum.catsgram.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final Set<User> users = new HashSet<>();

    @GetMapping
    public Set<User> getUsers() {
        log.debug("Количество пользователей: {}", users.size());
        return users;
    }

    @PostMapping
    public User postUser(@RequestBody User user) {
        if (user.getEmail() == null || "".equals(user.getEmail())) {
            throw new InvalidEmailException("Invalid user email!");
        }
        if (users.contains(user)) {
            throw new UserAlreadyExistException("User with email = \'" + user.getEmail() + "\' already exist!");
        }
        log.debug("Создан пользователь: {}", user.toString());
        users.add(user);
        return user;
    }

    @PutMapping
    public User putUser(@RequestBody User user) {
        if (user.getEmail() == null || "".equals(user.getEmail())) {
            throw new InvalidEmailException("Invalid user email!");
        }
        users.add(user);
        return user;
    }
}