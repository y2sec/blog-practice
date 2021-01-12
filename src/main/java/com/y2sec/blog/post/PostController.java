package com.y2sec.blog.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

        Long id = postService.writePost(post);

        return "redirect:/post/" + id;
    }

    @GetMapping("/post/list")
    public String listForm(Model model) {
        List<Post> posts = postService.postAll();
        model.addAttribute("posts", posts);

        return "/post/postList";
    }

    @GetMapping("/post/{id}")
    public String postForm(@PathVariable("id") Long id, Model model) {
        Optional<Post> post = postService.viewPost(id);

        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            post.get().setViews(post.get().getViews()+1);
            return "/post/postView";
        } else {
            return "/post/postNull";
        }
    }

    @PostMapping("/post/list")
    public String deletePost(PostForm postForm, Model model) {
        postService.deletePost(postForm.getId());

        List<Post> posts = postService.postAll();
        model.addAttribute("posts", posts);

        return "/post/postList";
    }
}
