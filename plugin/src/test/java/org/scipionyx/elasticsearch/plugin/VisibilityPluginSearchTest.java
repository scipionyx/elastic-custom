package org.scipionyx.elasticsearch.plugin;

import com.carrotsearch.randomizedtesting.RandomizedRunner;
import org.apache.lucene.index.IndexReader;
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
        index("jpmc", "account", "11", jsonBuilder().startObject().
                field("number", "11").
                field("name", "account name 11").
                field("eci", "10").
                endObject());
        index("jpmc", "account", "12", jsonBuilder().startObject().
                field("number", "12").
                field("name", "account name 12").
                field("eci", "10").
                endObject());
        index("jpmc", "account", "21", jsonBuilder().startObject().
                field("number", "21").
                field("name", "account name 21").
                field("eci", "20").
                endObject());
        ensureGreen();
    }

    public void testSearchAccounts() {
        assertThat(get("jpmc",
                "account",
                "11").
                isExists(), is(true));
        assertThat(get("jpmc",
                "account",
                "12").
                isExists(), is(true));
        assertThat(get("jpmc",
                "account",
                "21").
                isExists(), is(false));
        assertThat(get("jpmc",
                "account",
                "4").
                isExists(), is(false));
        //newSearcher()
    }

}