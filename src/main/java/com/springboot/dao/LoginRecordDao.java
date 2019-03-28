package com.springboot.dao;

import com.springboot.domain.LoginRecord;

import java.util.List;

public interface LoginRecordDao {

    void addLoginRecord(LoginRecord loginRecord);

    List<LoginRecord> findAll();
}
