package com.springboot.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

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
    private String attachment;                  //作业要求所含带的附件path

    @Column
    private String title;                       //作业的标题

    @OneToMany(mappedBy = "homework",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<StudentWork> studentWorkList;

    @ManyToOne( fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn()
    @NonNull
    private CourseRelease courseRelease;

}
