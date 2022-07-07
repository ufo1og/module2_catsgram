package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    private final HashMap<String, User> users = new HashMap<>();

    public User createUser(User user) {
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("User with email = \'" + user.getEmail() + "\' already exist!");
        }
        return updateUser(user);
    }

    public User updateUser(User user) {
        users.put(user.getEmail(), user);
        return user;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public User findUserByEmail(String email) {
        return users.get(email);
    }
}
