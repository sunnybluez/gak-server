package com.springboot.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * 作业题目的实体类
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column
//    private int courseReleaseId;                //发布课程的id   外键

    @Column
    @NonNull
    private String description;                  //作业要求所含带的附件path

    @Column
    @NonNull
    private String title;                       //作业的标题

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @NonNull
    private Date deadline;                      //作业截止日期


    @ManyToOne( fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn()
    @NonNull
    private CourseRelease courseRelease;

}
