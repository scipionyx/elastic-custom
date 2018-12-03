package org.scipionyx.elasticsearch.plugin.searchwrapper;

import lombok.extern.java.Log;
import org.elasticsearch.index.shard.SearchOperationListener;
import org.elasticsearch.search.internal.SearchContext;

@Log
public class VisibilitySearchOperationListener implements SearchOperationListener {

    @Override
    public void onPreQueryPhase(SearchContext searchContext) {
        log.info("onPreQueryPhase");
    }

    @Override
    public void onQueryPhase(SearchContext searchContext, long tookInNanos) {
        log.info("onQueryPhase");
    }
}
