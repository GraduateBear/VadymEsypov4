package com.aimprosoft.yesipov.demo.service;

import com.aimprosoft.yesipov.demo.domain.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<Department> findAll();

    void remove(long id);

    Optional<Department> findById(long id);

    Optional<Department> findByName(String name);

    void add(Department department);

    void edit(Department department);
}
