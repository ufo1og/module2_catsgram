package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.PostIdConflictException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {
    private final Map<Integer, Post> posts = new HashMap<>();

    private final UserService userService;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public Post getPost(int postId) {
        return posts.get(postId);
    }

    public Post create(Post post) {
        if (userService.findUserByEmail(post.getAuthor()) == null) {
            throw new UserNotFoundException("Пользователь " + post.getAuthor() + " не найден!");
        }
        if (posts.containsKey(post.getPostId())) {
            throw new PostIdConflictException("Сообщение с id = \'" + post.getPostId() + "\' уже существует!");
        }
        posts.put(post.getPostId(), post);
        return post;
    }
}