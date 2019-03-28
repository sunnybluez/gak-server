package com.springboot.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Manager {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30,nullable = false)
    @NonNull
    private String email;

    @Column(nullable = false)
    @NonNull
    private String password;


}
