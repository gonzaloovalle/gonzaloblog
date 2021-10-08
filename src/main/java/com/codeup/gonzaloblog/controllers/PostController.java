package com.codeup.gonzaloblog.controllers;

import com.codeup.gonzaloblog.models.Post;
import com.codeup.gonzaloblog.models.User;
import com.codeup.gonzaloblog.repositories.PostRepository;
import com.codeup.gonzaloblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
class PostController {

    private final PostRepository postsDao;
    private final UserRepository usersDao;

    public PostController(PostRepository postsDao, UserRepository usersDao) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
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
    public String updatePost(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post post = new Post(
                id,
                title,
                body
        );
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postForm(){
        return "Create a post here!";
    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam String title, @RequestParam String body) {

        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);

        User user = usersDao.findAll().get(0);
        post.setUser(user);

        postsDao.save(post);
        return "redirect:/posts/" + post.getId();
    }

}
