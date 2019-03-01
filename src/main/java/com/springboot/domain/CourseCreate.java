package com.springboot.domain;

import com.springboot.enums.ApproveState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 创建的课程的数据库实体类
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CourseCreate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int teacherId;          //创办老师的id

    @Column
    @Enumerated(EnumType.STRING)
    private ApproveState state;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createDate;
}
