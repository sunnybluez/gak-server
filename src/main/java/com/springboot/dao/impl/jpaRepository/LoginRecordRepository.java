package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.LoginRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRecordRepository extends JpaRepository<LoginRecord, Integer> {

}
