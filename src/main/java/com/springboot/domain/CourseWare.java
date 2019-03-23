package com.springboot.domain;

import lombok.*;

import javax.persistence.*;

/**
 * 课件实体类
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class CourseWare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column
//    private int courseCreateId;                 //创建的课程的id 外键

    @Column
    private String content;                     //课件的存储path

    @ManyToOne( fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn()
    @NonNull
    private CourseCreate courseCreate;

}
