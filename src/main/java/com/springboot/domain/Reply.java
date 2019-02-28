package com.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 帖子回复的实体类
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Date initDate;          //回帖的时间

}
