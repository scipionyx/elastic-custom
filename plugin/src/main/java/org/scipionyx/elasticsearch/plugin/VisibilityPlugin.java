package org.scipionyx.elasticsearch.plugin;

import lombok.extern.java.Log;
import org.elasticsearch.index.IndexModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;
import org.scipionyx.elasticsearch.plugin.searchwrapper.VisibilityIndexSearchWrapper;

@Log
public class VisibilityPlugin extends Plugin implements
        AnalysisPlugin {

    public VisibilityPlugin() {
        log.info("Visibility Plugin Started");
    }

    @Override
    public void onIndexModule(IndexModule indexModule) {
        log.info("Adding Visibility Wrapper for Index: " + indexModule.getIndex().getName());
        try {
            indexModule.setSearcherWrapper(VisibilityIndexSearchWrapper::new);
        } catch (final Exception e) {
            log.info("Error adding Search Wrapper to Index: " + indexModule.getIndex().getName());
        }

    }

}
