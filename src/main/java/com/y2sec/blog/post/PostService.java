package com.y2sec.blog.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long writePost(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public List<Post> postAll() {
        return postRepository.findAll();
    }
}
