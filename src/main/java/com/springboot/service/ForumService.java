package com.springboot.service;

import com.springboot.domain.Post;
import com.springboot.domain.Reply;

import java.util.List;

public interface ForumService {

    List<Post> getPostListByCCId(int courseCreateId);

    List<Reply> getReplyListByPostId(int postId);

    String addPost(int courseCreateId, String email, String title, String content);

    String addReply(int postId, String email, String content);

}
