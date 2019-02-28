package com.springboot.domain;

import com.springboot.enums.UserIdentity;
import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30,nullable = false)
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






}
