package com.springboot.domain;

import com.springboot.enums.GradeType;
import com.springboot.enums.SexType;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30,nullable = false)
    @NonNull
    private String email;

    @Column(nullable = false)
    @NonNull
    private String password;


    //详细的个人信息
    @Column
    private String name;

    @Column
    private int studentNum;

    @Column
    @Enumerated(EnumType.STRING)
    private SexType sex;

    @Column
    private int age;

    @Column
    private int phoneNum;

    @Column
    @Enumerated(EnumType.STRING)
    private GradeType grade;


    @Column(columnDefinition = "bit(1) default false", nullable = false)
    private boolean cancelled;

    @OneToMany(mappedBy = "student",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<CourseSelect> courseSelectList;

}
