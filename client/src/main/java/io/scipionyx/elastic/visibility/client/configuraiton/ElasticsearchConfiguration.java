package io.scipionyx.elastic.visibility.client.configuraiton;

import io.scipionyx.elastic.visibility.client.repositories.AccountRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackageClasses={AccountRepository.class})
public class ElasticsearchConfiguration {

}
