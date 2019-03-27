package com.springboot.dao;

import com.springboot.domain.Post;

import java.util.List;

public interface PostDao {

    List<Post> findAllByCourseCreateId(int courseCreateId);

    void addPost(Post post);

    Post findById(int postId);

}
