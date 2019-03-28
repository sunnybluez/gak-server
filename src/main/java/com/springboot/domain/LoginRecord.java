package com.springboot.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class LoginRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column
    private Date date;

    @ManyToOne( fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn()
    @NonNull
    private RegisterUser registerUser;
}
