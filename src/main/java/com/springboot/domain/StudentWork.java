package com.springboot.domain;

import lombok.*;

import javax.persistence.*;

/**
 * 学生提交的作业
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column
//    private int courseSelesctId;                //外键 学生课程关系的id
//
//    @Column
//    private int homeworkId;                     //外键 提交作业题目的id

    @Column
    private String attachment;                  //提交作业的path

    @Column
    private double score;                       //作业得分


    @ManyToOne( fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn()
    @NonNull
    private CourseSelect courseSelect;

    @ManyToOne( fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn()
    @NonNull
    private Homework homework;

}
