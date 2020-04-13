package io.github.marceloleite2604.model.server.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.marceloleite2604.model.server.ServerMessageType;

import java.io.IOException;

public class ServerMessageTypeJsonSerializer extends JsonSerializer<ServerMessageType> {

    @Override
    public void serialize(ServerMessageType serverMessageType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(serverMessageType.name().toLowerCase());
    }
}
