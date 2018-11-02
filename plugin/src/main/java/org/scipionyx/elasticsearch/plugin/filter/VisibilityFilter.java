package org.scipionyx.elasticsearch.plugin.filter;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.support.ActionFilter;
import org.elasticsearch.action.support.ActionFilterChain;
import org.elasticsearch.tasks.Task;

public class VisibilityFilter implements ActionFilter {

    @Override
    public int order() {
        return 0;
    }

    @Override
    public <Request extends ActionRequest<Request>, Response extends ActionResponse> void apply(
            Task task, String s,
            Request request,
            ActionListener<Response> actionListener,
            ActionFilterChain<Request, Response> actionFilterChain) {

    }

    @Override
    public <Response extends ActionResponse> void apply(
            String s,
            Response response,
            ActionListener<Response> actionListener,
            ActionFilterChain<?, Response> actionFilterChain) {

    }

}
