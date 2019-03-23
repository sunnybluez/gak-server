package com.springboot.domain;

import com.springboot.enums.ApproveState;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 创建的课程的数据库实体类
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CourseCreate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column
//    private int teacherId;          //创办老师的id

    @Column
    private String description;


    @Column
    @Enumerated(EnumType.STRING)
    private ApproveState state;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createDate;

    @ManyToOne( fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn()
    @NonNull
    private Teacher teacher;

    @OneToMany(mappedBy = "courseCreate",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<CourseRelease> courseReleaseList;

    @OneToMany(mappedBy = "courseCreate",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<CourseWare> courseWareList;

    @OneToMany(mappedBy = "courseCreate",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Post> postList;

}
