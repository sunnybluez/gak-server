package com.springboot.domain;

import com.springboot.enums.ApproveState;
import com.springboot.enums.Term;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 发布课程的数据库实体类
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CourseRelease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int courseCreateId;             //对应的创建的课程的id

    @Column
    private int teacherId;                  //对应的发布课程的老师id

    @Column
    private String schedule;                //班次信息的描述

    @Column
    private int limitNum;                   //限选人数

    @Column
    @Enumerated(EnumType.STRING)
    private ApproveState state;             //课程所处的审批状态

    @Column
    @Enumerated(EnumType.STRING)            //课程学期
    private Term term;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column
    private Date releaseDate;

}
