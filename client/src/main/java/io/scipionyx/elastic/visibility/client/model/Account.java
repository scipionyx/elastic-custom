package io.scipionyx.elastic.visibility.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "account", type = "account")
public class Account implements Serializable {

    @Id
    private String id;

    private String eci;

    private String accountNumber;

    private String name;

    private String city;

}
