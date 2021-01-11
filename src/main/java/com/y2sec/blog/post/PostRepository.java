package com.y2sec.blog.post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Post update(Post post, Long id);

    Post delete(Long id);

    Optional<Post> findById(Long id);

    List<Post> findAll();

}
