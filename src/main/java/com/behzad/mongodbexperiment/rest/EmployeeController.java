package com.behzad.mongodbexperiment.rest;

import com.behzad.mongodbexperiment.model.Employee;
import com.behzad.mongodbexperiment.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

@RestController
@RequestMapping("/v1/api/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ThreadFactory factory = Thread.ofVirtual().name("routine-", 0).factory();
    private final AsyncTaskExecutor asyncTaskExecutor;


    @PostMapping(path = "/reactive")
    public ResponseEntity<Queue<Employee>> addEmployeeReactive(@RequestBody Employee employee) {

        log.info("addEmployeeReactive...");
        List<Future<?>> futures = new ArrayList<>();
        Queue<Employee> list = new ConcurrentLinkedQueue<>();
        LocalDateTime now = LocalDateTime.now();
        log.info("begin Adding 100000 employee  {}:{}:{} - {}...", now.getHour(), now.getMinute(), now.getSecond(), now.getNano());
        final String name = employee.getName();
        for (int index = 0; index < 100000; index++) {
            Future<?> future = asyncTaskExecutor.submit(() -> {
                Employee blocked = employeeService
                        .saveReactive(
                                Employee.builder()
                                        .name(name)
                                        .surname(employee.getSurname())
                                        .phone(employee.getPhone())
                                        .build()
                        )
                        .block();
                list.add(blocked);
            });
            futures.add(future);
        }
        // Wait for all tasks to complete
        for (Future<?> future : futures) {
            try {
                future.get(); // This will block until the task is completed
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
            }
        }
        now = LocalDateTime.now();
        log.info("done adding 1000 employee {}:{}:{} - {}...", now.getHour(), now.getMinute(), now.getSecond(), now.getNano());


        return ResponseEntity.ok().body(list);

    }

    @PostMapping
    public ResponseEntity<Queue<Employee>> addEmployee(@RequestBody Employee employee) {
        log.info("addEmployee...");
        List<Future<?>> futures = new ArrayList<>();
        Queue<Employee> list = new ConcurrentLinkedQueue<>();
        LocalDateTime now = LocalDateTime.now();
        log.info("begin Adding 100000 employee  {}:{}:{} - {}...", now.getHour(), now.getMinute(), now.getSecond(), now.getNano());
        final String name = employee.getName();
//        try (ExecutorService executorService = Executors.newThreadPerTaskExecutor(factory)) {
        for (int index = 0; index < 100000; index++) {
            Future<?> future = asyncTaskExecutor.submit(() -> {
                list.add(
                        employeeService
                                .save(
                                        Employee.builder()
                                                .name(name)
                                                .surname(employee.getSurname())
                                                .phone(employee.getPhone())
                                                .build()
                                )
                );
            });
            futures.add(future);
        }

        // Wait for all tasks to complete
        for (Future<?> future : futures) {
            try {
                future.get(); // This will block until the task is completed
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
            }
        }
        now = LocalDateTime.now();
        log.info("done adding 1000 employee {}:{}:{} - {}...", now.getHour(), now.getMinute(), now.getSecond(), now.getNano());
//        }

        return ResponseEntity.ok().body(list);

    }

}
