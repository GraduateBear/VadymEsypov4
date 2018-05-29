package com.aimprosoft.yesipov.demo.service.impl;

import com.aimprosoft.yesipov.demo.domain.Employee;
import com.aimprosoft.yesipov.demo.repository.EmployeeRepository;
import com.aimprosoft.yesipov.demo.service.EmployeeService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getFilteredList(long id) {
        return Lists.newArrayList(employeeRepository.findByDepartment_Id(id));
    }

    @Override
    public List<Employee> findAll() {
        return Lists.newArrayList(employeeRepository.findAll());
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public void remove(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void add(Employee employee) {
        employeeRepository.save(employee);
    }
}
