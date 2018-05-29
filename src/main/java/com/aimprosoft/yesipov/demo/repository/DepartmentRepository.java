package com.aimprosoft.yesipov.demo.repository;

import com.aimprosoft.yesipov.demo.domain.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<Department, Long> { //, DepartmentRepositoryCustom {

    Optional<Department> findByOriginalName(String name);
}
