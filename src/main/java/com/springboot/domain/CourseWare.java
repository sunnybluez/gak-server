package com.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 课件实体类
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseWare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int courseCreateId;                 //创建的课程的id 外键

    @Column
    private String content;                     //课件的存储path

}
