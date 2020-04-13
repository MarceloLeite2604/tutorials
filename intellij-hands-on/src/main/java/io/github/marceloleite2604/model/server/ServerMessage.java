package io.github.marceloleite2604.model.server;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.marceloleite2604.model.server.serializer.ServerMessageTypeJsonSerializer;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerMessage {

    @JsonSerialize(using = ServerMessageTypeJsonSerializer.class)
    private ServerMessageType type;

    private String content;

    public ServerMessageType getType() {
        return type;
    }

    public void setType(ServerMessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerMessage that = (ServerMessage) o;
        return type == that.type &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, content);
    }

    public static final class Builder {
        private ServerMessageType type;
        private String content;

        private Builder() {
        }

        public static Builder aServerMessage() {
            return new Builder();
        }

        public Builder type(ServerMessageType type) {
            this.type = type;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public ServerMessage build() {
            ServerMessage serverMessage = new ServerMessage();
            serverMessage.content = this.content;
            serverMessage.type = this.type;
            return serverMessage;
        }
    }
}
