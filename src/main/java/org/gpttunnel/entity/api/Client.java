package org.gpttunnel.entity.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import org.gpttunnel.entity.api.request.ClientCreateRequest;
import org.gpttunnel.entity.enums.FlowEnum;
import org.gpttunnel.impl.APIObject;
import org.gpttunnel.impl.APIRequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Use only for create {@link ClientCreateRequest}
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Client implements APIObject, APIRequestData {

    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    private String id;
    private FlowEnum flow;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Nullable
    private String password;
    private String email;
    private int limitIp;
    private long totalGB;
    private long expiryTime;
    private boolean enable;
    private String tgId;
    private String subId;
    private String method;
    @JsonIgnore
    private int inboundId;
    @JsonIgnore
    private int reset;

    /**
     * Не удалять
     */
    public String getFlow() {
        if (flow != null) return flow.getValue();
        else return null;
    }

    @Override
    public String toJson() {
        Client[] clients = new Client[1];
        clients[0] = this;
        JSONObject obj = new JSONObject();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String clientsJsonString = objectMapper.writeValueAsString(clients);
            obj.put("id", this.inboundId);
            obj.put("settings", "{\"clients\":" + clientsJsonString + "}");

        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return obj.toString();
    }

    public static class Builder {

        private String id;
        private FlowEnum flow;
        private String password;
        private String email;
        private int limitIp;
        private long totalGB;
        private long expiryTime;
        private boolean enable;
        private String tgId;
        private String subId;
        private String method;
        private int inboundId = -1;
        private int reset = 0;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder flow(FlowEnum flow) {
            this.flow = flow;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder limitIp(int limitIp) {
            this.limitIp = limitIp;
            return this;
        }

        public Builder totalGB(int totalGB) {
            this.totalGB = totalGB;
            return this;
        }

        public Builder expiryTime(int expiryTime) {
            this.expiryTime = expiryTime;
            return this;
        }

        public Builder enable(boolean enable) {
            this.enable = enable;
            return this;
        }

        public Builder tgId(String tgId) {
            this.tgId = tgId;
            return this;
        }

        public Builder subId(String subId) {
            this.subId = subId;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder inboundId(int inboundId) {
            this.inboundId = inboundId;
            return this;
        }

        public Builder reset(int reset) {
            this.reset = reset;
            return this;
        }

        public Client build() throws IllegalAccessException {
            if (inboundId == -1) throw new IllegalAccessException("inboundId cannot be: " + inboundId);
            return new Client(id, flow, password, email, limitIp, totalGB, expiryTime, enable, tgId, subId, method, inboundId, reset);
        }
    }
}