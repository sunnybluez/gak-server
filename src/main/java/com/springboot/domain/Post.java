package com.springboot.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 论坛帖子
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column
//    private int courseCreateId;             //因为论坛和创建的课程是1：1所以这里直接用创建的课程id当作帖子的外键

    @Column
    @NonNull
    private String title;                   //帖子标题

//    @Column
//    private String userEmail;               //因为邮箱能够确定唯一的用户所以这里用邮箱

    @Column
    @NonNull
    private String content;                 //帖子头帖描述

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date initDate;                  //帖子创建日期

    @ManyToOne( fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn()
    @NonNull
    private CourseCreate courseCreate;

    @ManyToOne( fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn()
    @NonNull
    private RegisterUser registerUser;


}
