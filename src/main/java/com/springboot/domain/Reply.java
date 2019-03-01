package com.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 帖子回复的实体类
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int postId;             //帖子id

    @Column
    private String userEmail;       //用户邮箱

    @Column
    private String content;         //回复的内容

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date initDate;          //回帖的时间

}
