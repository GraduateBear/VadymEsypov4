package com.aimprosoft.yesipov.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue
    @Column(name = "department_id")
    private long id;
    private String originalName;

    @OneToMany(mappedBy = "department", orphanRemoval = true)
    private Collection<Employee> employees;
}
