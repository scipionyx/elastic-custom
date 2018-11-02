package org.scipionyx.elasticsearch.plugin.filter;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestFilter;
import org.elasticsearch.rest.RestFilterChain;
import org.elasticsearch.rest.RestRequest;

public class VisibilityRestFilter extends RestFilter {

    @Override
    public void process(RestRequest restRequest,
                        RestChannel restChannel,
                        NodeClient nodeClient,
                        RestFilterChain restFilterChain) throws Exception {

    }

}
