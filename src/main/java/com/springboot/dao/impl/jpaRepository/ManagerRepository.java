package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
        Manager findByEmail(String email);
}
