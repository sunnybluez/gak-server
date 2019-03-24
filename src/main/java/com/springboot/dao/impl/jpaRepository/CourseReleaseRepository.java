package com.springboot.dao.impl.jpaRepository;

import com.springboot.domain.CourseRelease;
import com.springboot.enums.ApproveState;
import com.springboot.enums.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseReleaseRepository extends JpaRepository<CourseRelease, Integer> {
    CourseRelease findById(int id);

    List<CourseRelease> findAllByTermAndState(Term term, ApproveState state);


}

