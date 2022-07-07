package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    private final Set<User> users = new HashSet<>();

    public User createUser(User user) {
        if (users.contains(user)) {
            throw new UserAlreadyExistException("User with email = \'" + user.getEmail() + "\' already exist!");
        }
        return updateUser(user);
    }

    public User updateUser(User user) {
        users.add(user);
        return user;
    }

    public Set<User> getUsers() {
        return users;
    }
}
