package org.scipionyx.elasticsearch.plugin;

import lombok.extern.java.Log;
import org.elasticsearch.index.IndexModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.ClusterPlugin;
import org.elasticsearch.plugins.Plugin;
import org.scipionyx.elasticsearch.plugin.searchwrapper.VisibilityIndexSearchWrapper;

@Log
public class VisibilityPlugin extends Plugin implements
        ClusterPlugin,
        AnalysisPlugin {

    public VisibilityPlugin() {
        log.info("Visibility Plugin Started");
    }

    @Override
    public void onIndexModule(IndexModule indexModule) {
        log.info("Adding Visibility Wrapper for Index: " + indexModule.getIndex().getName());
        indexModule.setSearcherWrapper(indexService -> new VisibilityIndexSearchWrapper(indexService));
    }

}
