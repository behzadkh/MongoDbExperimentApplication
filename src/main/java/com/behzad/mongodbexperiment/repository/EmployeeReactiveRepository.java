package com.behzad.mongodbexperiment.repository;

import com.behzad.mongodbexperiment.model.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmployeeReactiveRepository extends ReactiveCrudRepository<Employee, String> {

}
