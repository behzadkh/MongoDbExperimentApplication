package com.behzad.mongodbexperiment.repository;

import com.behzad.mongodbexperiment.model.Department;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DepartmentReactiveRepository extends ReactiveCrudRepository<Department, String> {
}
