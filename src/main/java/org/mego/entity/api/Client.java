package org.mego.entity.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.json.JSONObject;
import org.mego.entity.enums.FlowEnum;
import org.mego.impl.I3UXObject;
import org.mego.impl.I3UXRequestData;

@Getter
@AllArgsConstructor
public class Client implements I3UXObject, I3UXRequestData {

    private String id;
    private FlowEnum flow;
    private String password;
    private String email;
    private int limitIp;
    private int totalGB;
    private int expiryTime;
    private boolean enable;
    private String tgId;
    private String subId;
    private String method;
    @JsonIgnore
    private int inboundId = -1;

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
        private int totalGB;
        private int expiryTime;
        private boolean enable;
        private String tgId;
        private String subId;
        private String method;
        private int inboundId = -1;

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

        public Client build() throws IllegalAccessException {
            // Здесь можно добавить дополнительные проверки перед созданием объекта
            if (inboundId == -1) throw new IllegalAccessException("inboundId cannot be: " + inboundId);
            return new Client(id, flow, password, email, limitIp, totalGB, expiryTime, enable, tgId, subId, method, inboundId);
        }
    }
}