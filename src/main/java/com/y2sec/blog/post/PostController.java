package com.y2sec.blog.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {

    private PostService postService;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/new")
    public String newForm(Model model) {
        return "post/postCreate";
    }

    @PostMapping("/post/new")
    public String createPost(PostForm form) {
        Post post = new Post();

        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setDate(format.format(new Date()));

        postService.writePost(post);

        return "redirect:/";
    }

    @GetMapping("/post/list")
    public String listForm(Model model) {
        List<Post> posts = postService.postAll();
        model.addAttribute("posts", posts);
        return "/post/postList";
    }
}
