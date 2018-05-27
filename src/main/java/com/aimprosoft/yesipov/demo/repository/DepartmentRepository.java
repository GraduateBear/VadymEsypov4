package com.aimprosoft.yesipov.demo.repository;

import com.aimprosoft.yesipov.demo.domain.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
