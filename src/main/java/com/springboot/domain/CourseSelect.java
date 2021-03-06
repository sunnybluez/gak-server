package com.springboot.domain;

import com.springboot.enums.SelectState;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 学生的选择，学生和选课的关系
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CourseSelect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column
//    private int studentId;                      //学生的Id

//    @Column
//    private int courseReleaseId;                //选取的发布课程的Id

    @Column
    @Enumerated(EnumType.STRING)
    @NonNull
    private SelectState state;                  //选课关系被选后的状态

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date selectDate;                    //选取日期


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn()
    @NonNull
    private CourseRelease courseRelease;

    @ManyToOne( fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn()
    @NonNull
    private Student student;


}
