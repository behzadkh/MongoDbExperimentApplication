package com.behzad.mongodbexperiment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employee")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    private String id = "default";

    private String name;
    private String surname;
    private String phone;

}
