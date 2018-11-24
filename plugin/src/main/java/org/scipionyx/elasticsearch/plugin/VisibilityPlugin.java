package org.scipionyx.elasticsearch.plugin;

import lombok.extern.java.Log;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.common.settings.ClusterSettings;
import org.elasticsearch.common.settings.IndexScopedSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.SettingsFilter;
import org.elasticsearch.plugins.*;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestHandler;
import org.scipionyx.elasticsearch.plugin.filter.VisibilityRestHandler;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@Log
public class VisibilityPlugin extends Plugin implements ClusterPlugin,
        ActionPlugin,
        NetworkPlugin,
        AnalysisPlugin {

    public VisibilityPlugin() {
        log.info("Visibility Plugin Started");
    }

    @Override
    public List<RestHandler> getRestHandlers(Settings settings,
                                             RestController restController,
                                             ClusterSettings clusterSettings,
                                             IndexScopedSettings indexScopedSettings,
                                             SettingsFilter settingsFilter,
                                             IndexNameExpressionResolver indexNameExpressionResolver,
                                             Supplier<DiscoveryNodes> nodesInCluster) {
        return Collections.singletonList(new VisibilityRestHandler(settings, restController));
    }
}
