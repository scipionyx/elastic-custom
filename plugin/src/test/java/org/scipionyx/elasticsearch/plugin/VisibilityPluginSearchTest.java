package org.scipionyx.elasticsearch.plugin;

import com.carrotsearch.randomizedtesting.RandomizedRunner;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Collections;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@RunWith(RandomizedRunner.class)
@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE)
public class VisibilityPluginSearchTest extends ESIntegTestCase {

    @Override
    protected Collection<Class<? extends Plugin>> nodePlugins() {
        return Collections.singleton(VisibilityPlugin.class);
    }

    @Override
    @Before()
    public void setUp() throws Exception {
        super.setUp();
        setUpTestData();
    }

    private void setUpTestData() throws Exception {
        index("jpmc", "account", "1", jsonBuilder().startObject().
                field("number", "1").
                field("name", "account name 1").
                field("eci", "10").
                endObject());
        index("jpmc", "account", "2", jsonBuilder().startObject().
                field("number", "2").
                field("name", "account name 2").
                field("eci", "10").
                endObject());
        index("jpmc", "account", "3", jsonBuilder().startObject().
                field("number", "3").
                field("name", "account name 3").
                field("eci", "100").
                endObject());
        ensureGreen();
    }

    public void testPluginIsLoaded() throws InterruptedException {
        Assert.assertTrue(client().
                prepareGet("jpmc", "account", "1").
                execute().
                actionGet().
                isExists());
        Assert.assertTrue(client().
                prepareGet("jpmc", "account", "2").
                execute().
                actionGet().
                isExists());
        Assert.assertFalse(client().
                prepareGet("jpmc", "account", "3").
                execute().
                actionGet().
                isExists());
        ensureGreen();
        Thread.sleep(5000);
    }

}