package com.springboot.domain;

import com.springboot.enums.SexType;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Teacher {



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
    private int teacherNum;

    @Column
    @Enumerated(EnumType.STRING)
    private SexType sex;

    @Column
    private int age;

    @Column
    private int phoneNum;


}
