package org.scipionyx.elasticsearch.plugin.filter;

import lombok.extern.java.Log;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.support.ActionFilter;
import org.elasticsearch.action.support.ActionFilterChain;
import org.elasticsearch.tasks.Task;

@Log
public class VisibilityActionFilter implements ActionFilter {

    @Override
    public int order() {
        return 0;
    }

    @Override
    public <Request extends ActionRequest, Response extends ActionResponse> void apply(Task task,
                                                                                       String action,
                                                                                       Request request,
                                                                                       ActionListener<Response> listener,
                                                                                       ActionFilterChain<Request, Response> chain) {
        log.info("Apply, task:" + task.getAction() + ", request: " + request.getDescription());
        chain.proceed(task, action, request, listener);
    }
}
