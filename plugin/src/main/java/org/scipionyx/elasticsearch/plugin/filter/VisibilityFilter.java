package org.scipionyx.elasticsearch.plugin.filter;

import lombok.extern.java.Log;
import org.apache.lucene.analysis.FilteringTokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

@Log
public class VisibilityFilter extends FilteringTokenFilter {

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);

    public VisibilityFilter(TokenStream in) {
        super(in);
    }

    @Override
    protected boolean accept() {
        return termAtt.toString().equals("jettro");
    }
}
