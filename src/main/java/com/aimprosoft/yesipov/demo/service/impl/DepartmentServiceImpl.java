package com.aimprosoft.yesipov.demo.service.impl;

import com.aimprosoft.yesipov.demo.domain.Department;
import com.aimprosoft.yesipov.demo.repository.DepartmentRepository;
import com.aimprosoft.yesipov.demo.service.DepartmentService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        return Lists.newArrayList(departmentRepository.findAll());
    }

    @Override
    public void remove(long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Optional<Department> findById(long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Optional<Department> findByName(String name) {
        return departmentRepository.findByOriginalName(name);
    }

    @Override
    public void add(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void edit(Department department, long id) {
        departmentRepository.save(department);   //update(department, id);
    }
}
