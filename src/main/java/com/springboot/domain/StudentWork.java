package com.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 学生提交的作业
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int courseSelesctId;                //外键 学生课程关系的id

    @Column
    private int homeworkId;                     //外键 提交作业题目的id

    @Column
    private String attachment;                  //提交作业的path

    @Column
    private double score;                       //作业得分

}
