package com.jcontrerast.sso.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ObjectMapperConfiguration {
    public ObjectMapperConfiguration(ObjectMapper objectMapper) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new TrimStringDeserializer());
        objectMapper.registerModule(module);
    }

    static class TrimStringDeserializer extends StdDeserializer<String> {
        public TrimStringDeserializer() {
            this(null);
        }

        protected TrimStringDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                return jsonParser.getText().trim();
            }
            return null;
        }
    }
}
