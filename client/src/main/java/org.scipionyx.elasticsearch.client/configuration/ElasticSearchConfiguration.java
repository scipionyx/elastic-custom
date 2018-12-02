package org.scipionyx.elasticsearch.client.configuration;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.scipionyx.elasticsearch.client.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

@Configuration
@EnableElasticsearchRepositories(basePackageClasses = {AccountRepository.class})
public class ElasticSearchConfiguration {

    @Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;

    @Bean
    public Client client() throws Exception {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        return new PreBuiltTransportClient(Settings.
                builder().
                put("cluster.name", esClusterName).
                build()).
                addTransportAddress(new TransportAddress(InetAddress.
                        getByName(esHost),
                        esPort));
    }

//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate(final Client client) throws Exception {
//        return new ElasticsearchTemplate(client);
//    }

}
