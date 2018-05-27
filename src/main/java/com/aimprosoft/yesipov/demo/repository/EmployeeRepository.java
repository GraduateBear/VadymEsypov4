package com.aimprosoft.yesipov.demo.repository;

import com.aimprosoft.yesipov.demo.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
