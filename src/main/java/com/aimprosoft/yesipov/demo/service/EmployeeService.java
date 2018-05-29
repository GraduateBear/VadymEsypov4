package com.aimprosoft.yesipov.demo.service;

import com.aimprosoft.yesipov.demo.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getFilteredList(long id);

    List<Employee> findAll();

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findById(long id);

    void remove(long id);

    void add(Employee employee);

    void edit(Employee employee);
}
