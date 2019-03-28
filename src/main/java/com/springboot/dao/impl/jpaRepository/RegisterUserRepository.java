package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.RegisterUser;
import com.springboot.enums.UserIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterUserRepository extends JpaRepository<RegisterUser, Integer> {

    RegisterUser findByEmail(String email);

    List<RegisterUser> findAllByAuthenticatedAndAndUserIdentity(boolean authenticated, UserIdentity userIdentity);

}
