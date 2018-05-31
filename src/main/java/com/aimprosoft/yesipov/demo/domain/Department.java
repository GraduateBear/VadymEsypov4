package com.aimprosoft.yesipov.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.Length;

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
    @Length(min = 5, message = "validation.originalName")
    private String originalName;

    @OneToMany(mappedBy = "department", orphanRemoval = true)
    private Collection<Employee> employees;
}
