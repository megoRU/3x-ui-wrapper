package org.threexui.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.threexui.entity.api.*;
import org.threexui.entity.api.response.GenericResponse;
import org.threexui.entity.exceptions.UnsuccessfulHttpException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ThreeUIAPI {

    /**
     * @param client new client settings
     * @return {@link Boolean} status
     */
    Boolean addClient(@NotNull Client client) throws UnsuccessfulHttpException, IOException;

    /**
     * @param email client.email → Shadowsocks / VMESS / VLESS / TROJAN
     * @return {@link Boolean} status
     */
    Boolean resetClientTraffic(@NotNull String email) throws UnsuccessfulHttpException, IOException;

    /**
     * @return {@link File} file backup
     */
    File getBackUpFile() throws UnsuccessfulHttpException, IOException;

    /**
     * @param email client.email → Shadowsocks / VMESS / VLESS / TROJAN
     * @return {@link Boolean} status
     */
    Boolean deleteClient(@NotNull String email) throws UnsuccessfulHttpException, IOException;

    /**
     *
     * @return {@link ClientTraffics} Statistics on the use of client traffic
     */
    List<ClientTraffics> getClientsList() throws UnsuccessfulHttpException, IOException;

    /**
     * @return {@link List<String>} List of online emails
     */
    List<String> getOnline() throws UnsuccessfulHttpException, IOException;

    /**
     * @param clientSettings updated customer data
     * @return {@link Boolean} status
     * <p><br>
     * Some data is better not to change. Since I myself have not yet understood what this can lead to.
     * It's best to use this to turn on and off. You can also change the limits.
     */
    Boolean updateClient(@NotNull ClientSettings clientSettings) throws UnsuccessfulHttpException, IOException;

    /**
     * @return {@link StatusPanel} Status panel
     */
    StatusPanel getStatus() throws UnsuccessfulHttpException, IOException;

    List<Inboard> getInboards() throws UnsuccessfulHttpException, IOException;

    GenericResponse login(@NotNull String username, @NotNull String password, String twoFactorCode) throws UnsuccessfulHttpException, IOException;

    Boolean logout() throws UnsuccessfulHttpException, IOException;

    GenericResponse getCsrfToken() throws UnsuccessfulHttpException, IOException;

    Boolean getTwoFactorEnable() throws UnsuccessfulHttpException, IOException;

    List<Inboard> getInboardsSlim() throws UnsuccessfulHttpException, IOException;

    GenericResponse getInboundsOptions() throws UnsuccessfulHttpException, IOException;

    GenericResponse getInboundById(int id) throws UnsuccessfulHttpException, IOException;

    Boolean setInboundEnable(int id, boolean enable) throws UnsuccessfulHttpException, IOException;

    Boolean resetAllInboundsTraffics() throws UnsuccessfulHttpException, IOException;

    GenericResponse getClientByEmail(@NotNull String email) throws UnsuccessfulHttpException, IOException;

    GenericResponse getClientTrafficByEmail(@NotNull String email) throws UnsuccessfulHttpException, IOException;

    GenericResponse getClientsPaged(@NotNull Map<String, String> queryParams) throws UnsuccessfulHttpException, IOException;

    StatusPanel getServerStatus() throws UnsuccessfulHttpException, IOException;

    class Builder {

        // Required
        private String token;
        private boolean devMode;
        private String host;

        /**
         * This enables LOGS
         */
        public Builder enableDevMode() {
            this.devMode = true;
            return this;
        }

        /**
         * Password
         */
        @Contract("null -> fail")
        public Builder setToken(String token) {
            Objects.requireNonNull(token);
            this.token = token;
            return this;
        }

        /**
         * Example: http://109.234.38.174:2053 or https://vpn3.megoru.ru
         * Only in this format.
         */
        @Contract("null -> fail")
        public Builder setHost(String host) {
            Objects.requireNonNull(host);
            this.host = host;
            return this;
        }

        /**
         * @throws IllegalArgumentException if some fields null
         */
        public ThreeUIAPI build() throws UnsuccessfulHttpException, IOException {
            if (token == null)
                throw new IllegalArgumentException("token cannot be null!");
            else if (host == null)
                throw new IllegalArgumentException("host cannot be null!");
            else
                return new ThreeUIAPIImpl(token, devMode, host);
        }
    }
}
