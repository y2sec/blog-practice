package com.y2sec.blog.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Long updatePost(Post post, Long id) {
        postRepository.update(post, id);
        return id;
    }

    public Long deletePost(Long id) {
        postRepository.delete(id);
        return id;
    }

    public Optional<Post> viewPost(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> postAll() {
        return postRepository.findAll();
    }
}
