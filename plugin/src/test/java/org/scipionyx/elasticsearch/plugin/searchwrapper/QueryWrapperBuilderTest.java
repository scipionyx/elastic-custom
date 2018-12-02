package org.scipionyx.elasticsearch.plugin.searchwrapper;

import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Test;
import org.scipionyx.elasticsearch.plugin.searchwrapper.QueryWrapperBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@Log
public class QueryWrapperBuilderTest {

    @Test
    public void test() throws IOException {
        Assert.assertEquals("{\"query_string\":{\"fields\":[\"eci\"],\"query\":\"1 2 3 4\"}}",
                new QueryWrapperBuilder().
                        build(Collections.singletonList("eci"),
                                Arrays.asList("1", "2", "3", "4")));
    }

}
