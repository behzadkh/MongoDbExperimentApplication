package com.behzad.mongodbexperiment.repository;

import com.behzad.mongodbexperiment.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
}
