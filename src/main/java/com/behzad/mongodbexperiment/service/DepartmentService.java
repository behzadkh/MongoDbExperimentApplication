package com.behzad.mongodbexperiment.service;

import com.behzad.mongodbexperiment.repository.DepartmentReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentReactiveRepository departmentReactiveRepository;


}
