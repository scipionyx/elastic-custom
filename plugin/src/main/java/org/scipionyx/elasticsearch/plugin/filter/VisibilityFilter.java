package org.scipionyx.elasticsearch.plugin.filter;

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
    public <Request extends ActionRequest,
            Response extends ActionResponse> void apply(Task task,
                                                        String s,
                                                        Request request,
                                                        ActionListener<Response> actionListener,
                                                        ActionFilterChain<Request, Response> actionFilterChain) {

    }

}
