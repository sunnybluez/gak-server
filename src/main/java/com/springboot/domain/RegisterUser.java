package com.springboot.domain;

import com.springboot.enums.UserIdentity;
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
public class RegisterUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, nullable = false)
    @NonNull
    private String email;

    @Column(nullable = false)
    @NonNull
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NonNull
    private UserIdentity userIdentity;

    @Column(columnDefinition = "bit(1) default false", nullable = false)
    private boolean authenticated;
//
    @OneToMany(mappedBy = "registerUser",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Reply> replyList;

    @OneToMany(mappedBy = "registerUser",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Post> postList;
}
