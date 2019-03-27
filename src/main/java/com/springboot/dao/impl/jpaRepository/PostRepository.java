package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByCourseCreateId(int courseCreateId);

    Post findById(int postId);
}
