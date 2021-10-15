package com.codeup.gonzaloblog.controllers;

import com.codeup.gonzaloblog.models.Post;
import com.codeup.gonzaloblog.models.User;
import com.codeup.gonzaloblog.repositories.PostRepository;
import com.codeup.gonzaloblog.repositories.UserRepository;
import com.codeup.gonzaloblog.services.EmailService;
import com.codeup.gonzaloblog.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
class PostController {

    private final PostRepository postsDao;
    private final UserRepository usersDao;
    private final UserService userService;
    private final EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository usersDao, UserService userService, EmailService emailService) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String postIndex(Model model) {

        List<Post> postList = postsDao.findAll();

        model.addAttribute("title", "All Posts");
        model.addAttribute("posts", postList);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postView(Model model, @PathVariable long id ) {
        Post post = postsDao.getById(id);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEditPostForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.getById(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post) {
        User user = usersDao.findAll().get(0);
        post.setUser(user);
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String postForm(Model model) {
        model.addAttribute("post", new Post());
            return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        User user = usersDao.findAll().get(0);
        post.setUser(user);

        Post savedPost = postsDao.save(post);

        String subject = "New Post Created: " + savedPost.getTitle();
        String body = "Dear " + savedPost.getUser().getUsername()
                + ". Thank you for creating a post. Your post id is "
                + savedPost.getId();
        emailService.prepareAndSend(savedPost, subject, body);
        return "redirect:/posts/";
    }

}
