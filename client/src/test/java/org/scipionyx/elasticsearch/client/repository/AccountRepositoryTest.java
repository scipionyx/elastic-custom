package org.scipionyx.elasticsearch.client.repository;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.scipionyx.elasticsearch.client.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

@Slf4j
@RunWith(SpringRunner.class)
public class AccountRepositoryTest {

    //@Autowired
    //private AccountRepository repository;

    @Test
    public void testCreateUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        AtomicLong counter = new AtomicLong();
        Stream.
                iterate(0, i -> i + 1).
                limit(10).
                map(i-> Account.
                        builder().
                        eci("ECI" + new Random().nextInt(2_000)).
                        name("NAME" + new Random().nextInt(1_000_000)).
                        number("NUMBER" + new Random().nextInt(1_000_000)).
                        build()).
                peek(a->a.setRowNumber(counter.incrementAndGet())).
               //peek(repository::save).
                forEach(a->log.info(a.toString()));
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
    }

}