package com.y2sec.blog.post;

import java.util.*;

public class MemoryPostRepository implements PostRepository{

    private static final Map<Long, Post> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Post save(Post post) {
        post.setId(++sequence);
        post.setViews(0);
        store.put(sequence, post);
        return post;
    }

    @Override
    public Post update(Post post, Long id) {
        store.get(id).setTitle(post.getTitle());
        store.get(id).setContent(post.getContent());

        return post;
    }

    @Override
    public Post delete(Long id) {
        Post post = store.get(id);
        store.remove(id);
        return post;
    }

    @Override
    public Optional<Post> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }
}
