package com.project.mdyshop.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;

public class GrantedAuthorityDeserializer extends StdDeserializer<SimpleGrantedAuthority> {
    protected GrantedAuthorityDeserializer(Class<?> vc) {
        super(vc);
    }

    protected GrantedAuthorityDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected GrantedAuthorityDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public SimpleGrantedAuthority deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        return new SimpleGrantedAuthority(node.asText());
    }
}
