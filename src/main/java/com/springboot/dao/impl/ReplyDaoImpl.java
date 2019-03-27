package com.springboot.dao.impl;

import com.springboot.dao.ReplyDao;
import com.springboot.dao.impl.jpaRepository.ReplyRepository;
import com.springboot.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyDaoImpl implements ReplyDao {

    @Autowired
    private ReplyRepository replyRepository;

    @Override
    public void addReply(Reply reply) {
         replyRepository.save(reply);
    }

    @Override
    public Reply findById(int id) {
        return replyRepository.findById(id);
    }

    @Override
    public List<Reply> findAllByPostId(int postId) {
        return replyRepository.findAllByPostId(postId);
    }

}
