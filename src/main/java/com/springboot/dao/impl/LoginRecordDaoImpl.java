package com.springboot.dao.impl;

import com.springboot.dao.LoginRecordDao;
import com.springboot.dao.impl.jpaRepository.LoginRecordRepository;
import com.springboot.domain.LoginRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginRecordDaoImpl implements LoginRecordDao {

    @Autowired
    LoginRecordRepository loginRecordRepository;

    @Override
    public void addLoginRecord(LoginRecord loginRecord) {
        loginRecordRepository.save(loginRecord);
    }

    @Override
    public List<LoginRecord> findAll() {
        return loginRecordRepository.findAll();
    }
}
