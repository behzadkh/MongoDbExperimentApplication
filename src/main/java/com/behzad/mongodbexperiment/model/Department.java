package com.behzad.mongodbexperiment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "department")
@Getter
@Setter
@AllArgsConstructor
public class Department {

    @Id
    private String id = "default";

    private String name;

    private String description;

}
