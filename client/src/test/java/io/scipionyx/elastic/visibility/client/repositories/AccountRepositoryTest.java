package io.scipionyx.elastic.visibility.client.repositories;

import io.scipionyx.elastic.visibility.client.Application;
import io.scipionyx.elastic.visibility.client.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.UUID;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Profile("integration")
@Slf4j
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    public void loadAccountTest(){
        Flux.
                range(0,5000000).
                map(i->Account.builder().
                        eci(UUID.randomUUID().toString()).
                        accountNumber(String.valueOf(i)).
                        build()).
                buffer(10000).
                map(repository::saveAll).
                subscribe(System.out::println);
    }

    @Test
    public void getTest(){
        repository.findAll().forEach(account -> log.info(account.getCity()));
    }

    @Test
    public void getDelete(){
        Page<Account> page = repository.findAll(PageRequest.of(1,
                10000));
        log.info("Total Elements: {}, Pages: {}",  page.getTotalElements(), page.getTotalPages());
        page.forEach(repository::delete);
    }

    @Test
    public void getCount(){
        log.info("Number of records: {}", repository.count());
    }

    @Test
    public void deleteALL(){
        repository.deleteAll();
        log.info("Number of records: {}", repository.count());
    }

}
