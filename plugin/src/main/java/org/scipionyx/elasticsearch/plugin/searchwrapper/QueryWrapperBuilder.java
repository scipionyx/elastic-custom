package org.scipionyx.elasticsearch.plugin.searchwrapper;

import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.json.JsonXContent;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class QueryWrapperBuilder {

    public String build(final List<String> fields, final List<String> orValues) throws IOException {
        return Strings.
                toString(JsonXContent.
                        contentBuilder().
                        startObject().
                        field("query_string").startObject().
                        field("fields", fields).
                        field("query", orValues.
                                stream().
                                collect(Collectors.joining(" "))).
                        endObject().endObject());
    }

}
