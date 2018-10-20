package io.scipionyx.elastic.visibility.client.repositories;

import io.scipionyx.elastic.visibility.client.model.Account;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface AccountRepository extends ElasticsearchCrudRepository<Account, String> {

}
