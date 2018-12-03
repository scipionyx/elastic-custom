package org.scipionyx.elasticsearch.plugin;

import lombok.extern.java.Log;
import org.elasticsearch.index.IndexModule;
import org.elasticsearch.plugins.Plugin;
import org.scipionyx.elasticsearch.plugin.searchwrapper.VisibilitySearchOperationListener;

@Log
public class VisibilityPlugin
        extends Plugin {

    public VisibilityPlugin() {
        log.info("Visibility Plugin Started");
    }

    @Override
    public void onIndexModule(IndexModule indexModule) {
        log.info("Adding Visibility Wrapper for Index: " + indexModule.getIndex().getName());
        indexModule.addSearchOperationListener(new VisibilitySearchOperationListener());
    }

}
