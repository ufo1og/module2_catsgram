package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.PostNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAll() {
        return postService.findAll();

    }

    @PostMapping(value = "/post")
    public Post create(@RequestBody Post post) {
        log.debug("Создан пользователь: {}", post.toString());
        return postService.create(post);
    }

    @GetMapping("/posts/{id}")
    public Post getPostByID(@PathVariable String id) {
        int postId = Integer.parseInt(id);
        Post post = postService.getPost(postId);
        if (post == null) {
            throw new PostNotFoundException();
        }
        return post;
    }
}