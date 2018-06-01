package com.aimprosoft.yesipov.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.*;

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

    @NotNull(message = "")
    @NotEmpty(message = "")
    @MatchPattern(pattern = "^[A-ZА-Я][a-zа-яё]+", message = "validation.name.pattern")
    private String firstName;
    @NotNull(message = "")
    @NotEmpty(message = "")
    @MatchPattern(pattern = "^[A-ZА-Я][a-zа-яё]+", message = "validation.name.pattern")
    private String lastName;

    @NotNull(message = "")
    @NotEmpty(message = "")
    @MatchPattern(pattern = "^[A-ZА-Я][a-zа-яё]+", message = "validation.name.pattern")
    private String job;

    @NotNull(message = "validation.email.null")
    @NotEmpty(message = "validation.email.null")
    @MatchPattern(message = "validation.email.pattern", pattern = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}")
    private String email;

    @NotNull(message = "")
    @NotEmpty(message = "")
    @Min(value = 100, message = "")
    @MatchPattern(message = "", pattern = "[\\d]+[\\.[\\d]{0,2}]")
    private double salary;

    @ManyToOne(optional = false, targetEntity = Department.class)
    @JoinColumn(referencedColumnName = "department_id")
    private Department department;

    @NotNull(message = "validation.birthday.null")
    @NotEmpty(message = "validation.birthday.null")
    @DateRange(format = "yyyy-MM-dd")
    //@DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthday;
}
