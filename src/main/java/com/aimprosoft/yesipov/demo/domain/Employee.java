package com.aimprosoft.yesipov.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue
    private long id;

    private String firstName;
    private String lastName;

    private String job;

    private String email;
    private double salary;

    @ManyToOne(optional = false, targetEntity = Department.class)
    @JoinColumn(referencedColumnName = "department_id")
    private Department department;

    private Date birthday;
}
