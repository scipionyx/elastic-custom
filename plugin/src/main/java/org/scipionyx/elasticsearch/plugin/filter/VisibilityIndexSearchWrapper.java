package org.scipionyx.elasticsearch.plugin.filter;

import lombok.extern.java.Log;
import org.apache.lucene.search.IndexSearcher;
import org.elasticsearch.index.IndexService;
import org.elasticsearch.index.shard.IndexSearcherWrapper;

import java.io.IOException;

@Log
public class VisibilityIndexSearchWrapper extends IndexSearcherWrapper {

    private final IndexService indexService;

    public VisibilityIndexSearchWrapper(IndexService indexService) {
        this.indexService = indexService;
        log.info("Index Search Wrapper Started, IndexName:" + indexService.getMetaData().getIndex().getName());
    }

    @Override
    protected IndexSearcher wrap(IndexSearcher searcher) throws IOException {
        log.info("Index Search - searcher:");
        return super.wrap(searcher);
    }
}
