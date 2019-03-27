package com.springboot.dao.impl;

import com.springboot.dao.PostDao;
import com.springboot.dao.impl.jpaRepository.PostRepository;
import com.springboot.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDaoImpl implements PostDao {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> findAllByCourseCreateId(int courseCreateId) {
        return postRepository.findAllByCourseCreateId(courseCreateId);
    }

    @Override
    public void addPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post findById(int postId) {
        return postRepository.findById(postId);
    }

}
