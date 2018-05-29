package com.aimprosoft.yesipov.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@OnDelete(action = OnDeleteAction.CASCADE)
public class Department {

    @Id
    @GeneratedValue
    @Column(name = "department_id")
    //@Cascade(value = CascadeType.ALL)
    //@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private long id;
    private String originalName;
}
