package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.RegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterUserRepository extends JpaRepository<RegisterUser, Integer> {

    RegisterUser findByEmail(String email);

}
