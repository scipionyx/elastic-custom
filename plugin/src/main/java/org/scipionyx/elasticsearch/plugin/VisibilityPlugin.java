package org.scipionyx.elasticsearch.plugin;

import lombok.extern.java.Log;
import org.elasticsearch.index.IndexModule;
import org.elasticsearch.plugins.*;
import org.scipionyx.elasticsearch.plugin.searchwrapper.VisibilityIndexSearchWrapper;

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
