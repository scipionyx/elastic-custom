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
    }

    public void testPluginIsLoaded() throws InterruptedException {
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
        Thread.sleep(5000);
    }

}