package com.y2sec.blog;

import com.y2sec.blog.post.MemoryPostRepository;
import com.y2sec.blog.post.PostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PostRepository postRepository() {
        return new MemoryPostRepository();
    }
}
