package com.transtour.kernel.infrastructure.jackson;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.transtour.kernel.domain.AggregateRoot;

import java.io.IOException;
import java.util.Optional;

public abstract class AbstractDeserializer<T extends AggregateRoot> extends StdDeserializer<T> {

    protected transient JsonNode node;

    protected AbstractDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return null;
    }

    protected Optional<String> obtainTextValue(String field) {
        return checkJsonNode(field)
            .map(JsonNode::textValue);
    }

    protected Optional<Integer> obtainIntValue(String field) {
        return checkJsonNode(field)
            .map(JsonNode::intValue);
    }

    private Optional<JsonNode> checkJsonNode(String field) {
        if (node.get(field) == null) {
            return Optional.empty();
        }
        return Optional.of(node.get(field).get("value"));
    }


}

