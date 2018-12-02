package org.scipionyx.elasticsearch.client.repository;

import org.scipionyx.elasticsearch.client.model.Account;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface AccountRepository extends ElasticsearchCrudRepository<Account, String> {
}
