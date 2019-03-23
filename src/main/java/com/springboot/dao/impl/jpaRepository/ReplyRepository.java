package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    Reply findById(int id);

}
