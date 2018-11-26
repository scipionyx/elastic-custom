package org.scipionyx.elasticsearch.plugin;

import com.carrotsearch.randomizedtesting.RandomizedRunner;
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
public class VisibilityPluginTest extends ESIntegTestCase {

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

    public void testPluginIsLoaded() {
        client().
                admin().
                cluster().
                prepareNodesInfo().
                setPlugins(true).
                get().
                getNodes().
                forEach(nodeInfo -> assertThat(nodeInfo.
                        getPlugins().
                        getPluginInfos().
                        stream().
                        anyMatch(pluginInfo -> pluginInfo.
                                getName().
                                equals(VisibilityPlugin.class.getName())), is(true)));
    }

}