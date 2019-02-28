package com.springboot.domain;

import com.springboot.enums.ApproveState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 创建的课程的数据库实体类
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int teacherId;          //创办老师的id

    @Column
    @Enumerated(EnumType.STRING)
    private ApproveState state;


}
