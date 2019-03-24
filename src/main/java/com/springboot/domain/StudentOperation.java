package com.springboot.domain;

import com.springboot.enums.OperateType;
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
public class StudentOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NonNull
    private int studentId;

    @Column
    @NonNull
    private int courseReleaseId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date operateDate;


    @Column
    @Enumerated(EnumType.STRING)
    @NonNull
    private OperateType operateType;




}
