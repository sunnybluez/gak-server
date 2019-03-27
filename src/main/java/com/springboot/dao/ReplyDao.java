package com.springboot.dao;

import com.springboot.domain.Reply;

import java.util.List;

public interface ReplyDao {

    void addReply(Reply reply);

    Reply findById(int id);

    List<Reply> findAllByPostId(int postId);

}
