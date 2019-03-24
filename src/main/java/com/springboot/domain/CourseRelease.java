package com.springboot.domain;

import com.springboot.enums.ApproveState;
import com.springboot.enums.CourseState;
import com.springboot.enums.GradeType;
import com.springboot.enums.Term;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 发布课程的数据库实体类
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CourseRelease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column
//    private int courseCreateId;             //对应的创建的课程的id
//
//    @Column
//    private int teacherId;                  //对应的发布课程的老师id

    @Column
    @Enumerated(EnumType.STRING)            //课程学生类型
    @NonNull
    private GradeType gradeType;

    @Column
    @Enumerated(EnumType.STRING)            //课程选课状态
    @NonNull
    private CourseState courseState;

    @Column
    @NonNull
    private int limitNum;                   //限选人数

    @Column
    @Enumerated(EnumType.STRING)
    @NonNull
    private ApproveState state;             //课程所处的审批状态

    @Column
    @Enumerated(EnumType.STRING)            //课程学期
    @NonNull
    private Term term;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column
    private Date releaseDate;

    @ManyToOne( fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn()
    @NonNull
    private Teacher teacher;

    @ManyToOne( fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn()
    @NonNull
    private CourseCreate courseCreate;

    @OneToMany(mappedBy = "courseRelease",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<CourseSelect> courseSelectList;



    @OneToMany(mappedBy = "courseRelease",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Homework> homework;

}
