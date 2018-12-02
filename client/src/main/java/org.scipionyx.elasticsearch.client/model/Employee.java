package org.scipionyx.elasticsearch.client.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "jpmc", type = "employee")
@Data
public class Employee {

    @Id
    private String id;

    private String name;

    private List<String> ecis;

}
