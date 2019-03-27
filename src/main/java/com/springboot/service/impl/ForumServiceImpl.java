package com.springboot.service.impl;

import com.springboot.dao.CourseCreateDao;
import com.springboot.dao.PostDao;
import com.springboot.dao.RegisterUserDao;
import com.springboot.dao.ReplyDao;
import com.springboot.domain.CourseCreate;
import com.springboot.domain.Post;
import com.springboot.domain.RegisterUser;
import com.springboot.domain.Reply;
import com.springboot.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {

    @Autowired
    PostDao postDao;

    @Autowired
    ReplyDao replyDao;

    @Autowired
    CourseCreateDao courseCreateDao;

    @Autowired
    RegisterUserDao registerUserDao;



    @Override
    public List<Post> getPostListByCCId(int courseCreateId) {
        return postDao.findAllByCourseCreateId(courseCreateId);
    }

    @Override
    public List<Reply> getReplyListByPostId(int postId) {
        return replyDao.findAllByPostId(postId);
    }

    @Override
    public String addPost(int courseCreateId, String email, String title, String content) {
        CourseCreate courseCreate = courseCreateDao.findById(courseCreateId);
        RegisterUser registerUser = registerUserDao.findByEmail(email);
        Post post = new Post(title, content, courseCreate, registerUser);
        postDao.addPost(post);
        return "success";
    }

    @Override
    public String addReply(int postId,  String email, String content) {
        Post post = postDao.findById(postId);
        RegisterUser registerUser = registerUserDao.findByEmail(email);
        Reply reply = new Reply(content, registerUser, post);
        replyDao.addReply(reply);
        return "success";
    }


}
