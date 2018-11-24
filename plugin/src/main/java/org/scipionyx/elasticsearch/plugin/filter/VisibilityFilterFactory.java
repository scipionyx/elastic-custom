package org.scipionyx.elasticsearch.plugin.filter;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

public class VisibilityFilterFactory extends AbstractTokenFilterFactory {

    public VisibilityFilterFactory(IndexSettings indexSettings, String name, Settings settings) {
        super(indexSettings, name, settings);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new VisibilityFilter(tokenStream);
    }
}
