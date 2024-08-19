package com.behzad.mongodbexperiment.service;

import com.behzad.mongodbexperiment.model.Employee;
import com.behzad.mongodbexperiment.repository.EmployeeReactiveRepository;
import com.behzad.mongodbexperiment.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeReactiveRepository employeeReactiveRepository;
    private final EmployeeRepository employeeRepository;

    public Mono<Employee> saveReactive(Employee employee) {
        return employeeReactiveRepository.save(employee);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
}
