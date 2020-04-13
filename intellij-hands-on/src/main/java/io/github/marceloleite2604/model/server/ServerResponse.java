package io.github.marceloleite2604.model.server;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> {

    private ServerMessage message;

    private T content;

    public ServerMessage getMessage() {
        return message;
    }

    public void setMessage(ServerMessage message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerResponse<?> that = (ServerResponse<?>) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, content);
    }

    public static final class Builder<T> {
        private ServerMessage message;
        private T content;

        private Builder() {
        }

        public static <T> Builder<T> aServerResponse(Class<T> contentClass) {
            return new Builder<>();
        }

        public Builder<T> message(ServerMessage message) {
            this.message = message;
            return this;
        }

        public Builder<T> content(T content) {
            this.content = content;
            return this;
        }

        public ServerResponse<T> build() {
            ServerResponse<T> serverResponse = new ServerResponse<>();
            serverResponse.setMessage(message);
            serverResponse.setContent(content);
            return serverResponse;
        }
    }
}
