package com.aimprosoft.yesipov.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Guarded
public class Department {

    @Id
    @GeneratedValue
    @Column(name = "department_id")
    private long id;

    @NotNull(message = "validation.originalName.null")
    @NotEmpty(message = "validation.originalName.null")
    @Length(min = 3, message = "validation.originalName.length")
    @MatchPattern(pattern = "^[A-ZА-Я][a-zа-яё]+", message = "validation.name.pattern")
    private String originalName;

    @OneToMany(mappedBy = "department", orphanRemoval = true)
    private Collection<Employee> employees;
}
