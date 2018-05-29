package com.aimprosoft.yesipov.demo.repository;

import com.aimprosoft.yesipov.demo.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Iterable<Employee> findByDepartment_Id(long id);

    Optional<Employee> findByEmail(String email);
}
