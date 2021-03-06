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
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;

@Log
public class VisibilityIndexSearchWrapper extends IndexSearcherWrapper {

    //private final IndexService indexService;

    //private final ThreadContext threadContext;

    private final Function<ShardId, QueryShardContext> queryShardContextProvider;

    private final QueryWrapperBuilder queryWrapperBuilder;

    public VisibilityIndexSearchWrapper(IndexService indexService) {
        //this.indexService = indexService;
        //this.threadContext = indexService.getThreadPool().getThreadContext();
        this.queryShardContextProvider = shardId -> indexService.
                newQueryShardContext(shardId.id(),
                        null,
                        null,
                        null);
        this.queryWrapperBuilder = new QueryWrapperBuilder();
    }

    @Override
    public DirectoryReader wrap(DirectoryReader reader) throws IOException {
        log.info("Adding Boolean Query wrapping the regular execution - Directory");
        final QueryShardContext queryShardContext = this.queryShardContextProvider.apply(ShardUtils.extractShardId(reader));
        final XContentParser parser = JsonXContent.
                jsonXContent.
                createParser(queryShardContext.getXContentRegistry(),
                        LoggingDeprecationHandler.INSTANCE,
                        queryWrapperBuilder.build(Collections.singletonList("eci"),
                                Arrays.asList("10")));
        return DocumentFilterReader.
                wrap(reader,
                        new ConstantScoreQuery(new BooleanQuery.
                                Builder().
                                setMinimumNumberShouldMatch(1).
                                add(queryShardContext.
                                                toFilter(queryShardContext.
                                                        parseInnerQueryBuilder(parser)).
                                                query(),
                                        BooleanClause.Occur.SHOULD).
                                build()));
    }

    @Override
    public IndexSearcher wrap(IndexSearcher searcher) {
        log.info("Adding Boolean Query wrapping the regular execution - IndexSearcher");
        return searcher;
    }

}
