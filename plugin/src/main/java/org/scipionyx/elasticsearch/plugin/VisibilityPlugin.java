package org.scipionyx.elasticsearch.plugin;

import lombok.extern.java.Log;
import org.elasticsearch.action.support.ActionFilter;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.common.settings.ClusterSettings;
import org.elasticsearch.common.settings.IndexScopedSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.SettingsFilter;
import org.elasticsearch.index.IndexModule;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.*;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestHandler;
import org.scipionyx.elasticsearch.plugin.filter.VisibilityActionFilter;
import org.scipionyx.elasticsearch.plugin.filter.VisibilityIndexSearchWrapper;
import org.scipionyx.elasticsearch.plugin.filter.VisibilityRestHandler;
import org.scipionyx.elasticsearch.plugin.filter.VisibilityTokenFilterFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Log
public class VisibilityPlugin extends Plugin implements ClusterPlugin,
        ActionPlugin,
        NetworkPlugin,
        AnalysisPlugin {

    public VisibilityPlugin() {
        log.info("Visibility Plugin Started");
    }

//    @Override
//    public List<RestHandler> getRestHandlers(Settings settings,
//                                             RestController restController,
//                                             ClusterSettings clusterSettings,
//                                             IndexScopedSettings indexScopedSettings,
//                                             SettingsFilter settingsFilter,
//                                             IndexNameExpressionResolver indexNameExpressionResolver,
//                                             Supplier<DiscoveryNodes> nodesInCluster) {
//        return Collections.singletonList(new VisibilityRestHandler(settings, restController));
//    }
//
//    @Override
//    public Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
//        return Collections.singletonMap("visibility", VisibilityTokenFilterFactory::new);
//    }
//
//    @Override
//    public List<ActionFilter> getActionFilters() {
//        return Collections.singletonList(new VisibilityActionFilter());
//    }

    @Override
    public void onIndexModule(IndexModule indexModule) {
        super.onIndexModule(indexModule);
        indexModule.setSearcherWrapper(VisibilityIndexSearchWrapper::new);
    }
}
