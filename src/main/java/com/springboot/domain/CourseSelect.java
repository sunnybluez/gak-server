package com.springboot.domain;

import com.springboot.enums.SelectState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 学生的选择，学生和选课的关系
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSelect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int studentId;                      //学生的Id

    @Column
    private int courseReleaseId;                //选取的发布课程的Id

    @Column
    @Enumerated(EnumType.STRING)
    private SelectState state;                  //选课关系被选后的状态

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date selectDate;                    //选取日期

    @Column
    private double examScore;                   //考试分数



}
