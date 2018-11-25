package org.scipionyx.elasticsearch.plugin.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.rest.*;
import org.elasticsearch.rest.action.RestBuilderListener;

import java.io.IOException;

public class VisibilityRestHandler extends BaseRestHandler {

    private final static Logger LOGGER = LogManager.getLogger(VisibilityRestHandler.class);

    private static final String ACTION_EXISTS = "exists";

    private static final String NAME_INDEX_TO_CHECK = "visibility";

    @Inject
    public VisibilityRestHandler(Settings settings, RestController controller) {
        super(settings);
        controller.registerHandler(RestRequest.Method.GET, "_visibility/{action}", this);
        controller.registerHandler(RestRequest.Method.GET, "_visibility", this);
    }

    @Override
    public String getName() {
        return "visibility";
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) {
        String action = request.param("action");
        if (action != null && ACTION_EXISTS.equals(action)) {
            return createExistsResponse(request, client);
        } else {
            return createMessageResponse(request);
        }
    }

    private RestChannelConsumer createExistsResponse(final RestRequest request, final NodeClient client) {
        return channel -> client.
                admin().
                indices().
                prepareGetIndex().
                execute(new RestBuilderListener<GetIndexResponse>(channel) {
                        @Override
                        public RestResponse buildResponse(final GetIndexResponse response, final XContentBuilder builder) throws Exception {
                            String[] indices = response.getIndices();
                            if (indices == null || indices.length == 0) {
                                LOGGER.info("No indices are found");
                            }
                            boolean exists = false;
                            for (String index : indices) {
                                LOGGER.info("Index to check: {}", index);
                                if (index.startsWith(NAME_INDEX_TO_CHECK)) {
                                    exists = true;
                                    break;
                                }
                            }
                            Exists jettroExists = new Exists(exists);
                            builder.startObject();
                            jettroExists.toXContent(builder, request);
                            builder.endObject();
                            return new BytesRestResponse(RestStatus.OK, builder);
                        }
                    });
    }

    private RestChannelConsumer createMessageResponse(RestRequest request) {
        return channel -> {
            Message message = new Message();
            XContentBuilder builder = channel.newBuilder();
            builder.startObject();
            message.toXContent(builder, request);
            builder.endObject();
            channel.sendResponse(new BytesRestResponse(RestStatus.OK, builder));
        };
    }

    static class Message implements ToXContent {

        @Override
        public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
            return builder.field("message", "Plugin to support the Visibilty index.");
        }

    }

    static class Exists implements ToXContent {

        private boolean exists;

        Exists(boolean exists) {
            this.exists = exists;
        }

        @Override
        public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
            return builder.field("exists", exists);
        }

    }

}
