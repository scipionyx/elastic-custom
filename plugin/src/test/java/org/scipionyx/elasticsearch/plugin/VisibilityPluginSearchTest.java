package org.scipionyx.elasticsearch.plugin;

import com.carrotsearch.randomizedtesting.RandomizedRunner;
import org.elasticsearch.client.Client;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Collections;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.hamcrest.Matchers.is;


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
                field("eci", "X1").
                endObject());
        ensureGreen();
    }

    public void testSearchAccounts() {
        assertThat(get("jpmc",
                "account",
                "1").
                isExists(), is(true));
        assertThat(get("jpmc",
                "account",
                "2").
                isExists(), is(true));
        assertThat(get("jpmc",
                "account",
                "3").
                isExists(), is(false));
        assertThat(get("jpmc",
                "account",
                "4").
                isExists(), is(false));
    }

}