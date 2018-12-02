package org.scipionyx.elasticsearch.client.repository;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.elasticsearch.common.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.scipionyx.elasticsearch.client.Application;
import org.scipionyx.elasticsearch.client.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Profile("integration-test")
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    public void testCreateAccounts() {
        final int size = 5_000;
        var stopWatch = new StopWatch();
        stopWatch.start();
        AtomicLong counter = new AtomicLong();
        Flux.range(0, size).
                map(i -> Account.
                        builder().
                        eci("ECI" + new Random().nextInt(2_000)).
                        name("NAME" + new Random().nextInt(1_000_000)).
                        number("NUMBER" + new Random().nextInt(size)).
                        build()).
                map(account -> {
                    account.setRowNumber(counter.incrementAndGet());
                    return account;
                }).
                buffer(1000).
                map(repository::saveAll).
                subscribe(System.out::println);
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
    }

    @Test
    public void testDeleteAccounts() {
        var stopWatch = new StopWatch();
        stopWatch.start("Count Before Delete All");
        log.info("Total of records, count: {}", repository.count());
        stopWatch.stop();
        stopWatch.start("Delete All");
        repository.deleteAll();
        stopWatch.stop();
        stopWatch.start("Count After Delete All");
        log.info("Total of records, count: {}", repository.count());
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
    }

    @Test
    public void testList() {
        Flux.
                fromIterable(repository.findAll()).
                subscribe(System.out::println);
    }

}