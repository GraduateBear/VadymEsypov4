package com.aimprosoft.yesipov.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "department_id")
    private long id;
    private String originalName;
}
