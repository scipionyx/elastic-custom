package org.scipionyx.elasticsearch.plugin.searchwrapper;

import lombok.extern.java.Log;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.ConstantScoreQuery;
import org.apache.lucene.search.IndexSearcher;
import org.elasticsearch.common.util.concurrent.ThreadContext;
import org.elasticsearch.common.xcontent.LoggingDeprecationHandler;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.IndexService;
import org.elasticsearch.index.query.QueryShardContext;
import org.elasticsearch.index.shard.IndexSearcherWrapper;
import org.elasticsearch.index.shard.ShardId;
import org.elasticsearch.index.shard.ShardUtils;

import java.io.IOException;
import java.util.function.Function;

@Log
public class VisibilityIndexSearchWrapper extends IndexSearcherWrapper {

    private final IndexService indexService;

    private final ThreadContext threadContext;

    private final Function<ShardId, QueryShardContext> queryShardContextProvider;

    public VisibilityIndexSearchWrapper(IndexService indexService) {
        this.indexService = indexService;
        this.threadContext = indexService.getThreadPool().getThreadContext();
        this.queryShardContextProvider = shardId -> indexService.
                newQueryShardContext(shardId.id(),
                        null,
                        null,
                        null);
    }

    @Override
    protected DirectoryReader wrap(DirectoryReader reader) throws IOException {
        ShardId shardId = ShardUtils.extractShardId(reader);
        BooleanQuery.Builder boolQuery = new BooleanQuery.Builder();
        boolQuery.setMinimumNumberShouldMatch(1);
        QueryShardContext queryShardContext = this.queryShardContextProvider.apply(shardId);
        XContentParser parser = JsonXContent.
                jsonXContent.
                createParser(queryShardContext.getXContentRegistry(),
                        LoggingDeprecationHandler.INSTANCE,
                        Class.class.getResourceAsStream("/queries/match_eci.json"));
        boolQuery.add(queryShardContext.
                toFilter(queryShardContext.
                        parseInnerQueryBuilder(parser)).query(), BooleanClause.Occur.SHOULD);
        return DocumentFilterReader.
                wrap(reader, new ConstantScoreQuery(boolQuery.build()));
    }

    @Override
    protected IndexSearcher wrap(IndexSearcher searcher) {
        return searcher;
    }

}
