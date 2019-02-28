package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
