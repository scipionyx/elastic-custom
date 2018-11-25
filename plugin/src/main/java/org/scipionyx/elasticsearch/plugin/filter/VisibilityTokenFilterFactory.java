package org.scipionyx.elasticsearch.plugin.filter;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

public class VisibilityTokenFilterFactory extends AbstractTokenFilterFactory {

    private final Environment environment;

    public VisibilityTokenFilterFactory(IndexSettings indexSettings,
                                        Environment environment,
                                        String name,
                                        Settings settings) {
        super(indexSettings, name, settings);
        this.environment = environment;
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new VisibilityTokenFilter(tokenStream);
    }
}
