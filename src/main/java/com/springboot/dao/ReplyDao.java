package com.springboot.dao;

import com.springboot.domain.Reply;

public interface ReplyDao {

    void addReply(Reply reply);

    Reply findById(int id);

}
