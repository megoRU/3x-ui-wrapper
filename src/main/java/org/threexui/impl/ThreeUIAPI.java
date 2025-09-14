package org.threexui.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.threexui.entity.api.Client;
import org.threexui.entity.api.ClientTraffics;
import org.threexui.entity.api.Inboard;
import org.threexui.entity.api.StatusPanel;
import org.threexui.entity.exceptions.UnsuccessfulHttpException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public interface ThreeUIAPI {

    /**
     * @param client new client settings
     * @return {@link Boolean} status
     */
    Boolean addClient(@NotNull Client client) throws UnsuccessfulHttpException, IOException;

    /**
     * @return {@link File} file backup
     */
    File getBackUpFile() throws UnsuccessfulHttpException, IOException;

    /**
     * @param inboundId It`s ID from panel
     * @param clientId
     * client.id → VMESS / VLESS
     * client.password → TROJAN
     * client.email → Shadowsocks
     * @return {@link Boolean} status
     */
    Boolean deleteClient(int inboundId, @NotNull String clientId) throws UnsuccessfulHttpException, IOException;

    /**
     * @param inboundId It`s ID from panel
     * @param email email
     * @return {@link Boolean} status
     */
    Boolean deleteClientByEmail(int inboundId, @NotNull String email) throws UnsuccessfulHttpException, IOException;

    /**
     * @param clientId
     * client.id → VMESS / VLESS
     * client.password → TROJAN
     * client.email → Shadowsocks
     * @return {@link ClientTraffics} Statistics on the use of client traffic
     */
    ClientTraffics getClientTraffics(@NotNull String clientId) throws UnsuccessfulHttpException, IOException;

    /**
     * @return {@link List<String>} List of online emails
     */
    List<String> getOnline() throws UnsuccessfulHttpException, IOException;

    /**
     * @param client updated customer data
     * @return {@link Boolean} status
     * <p><br>
     * Some data is better not to change. Since I myself have not yet understood what this can lead to.
     * It's best to use this to turn on and off. You can also change the limits.
     */
    Boolean updateClient(@NotNull Client client) throws UnsuccessfulHttpException, IOException;

    /**
     * @return {@link StatusPanel} Status panel
     */
    StatusPanel getStatus() throws UnsuccessfulHttpException, IOException;

    List<Inboard> getInboards() throws UnsuccessfulHttpException, IOException;

    /**
     * Setup new Session
     */
    void setSession() throws UnsuccessfulHttpException, IOException;

    class Builder {

        // Required
        private String login;
        private String password;
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
        public Builder setPassword(String password) {
            Objects.requireNonNull(password);
            this.password = password;
            return this;
        }

        /**
         * Login
         */
        @Contract("null -> fail")
        public Builder setLogin(String login) {
            Objects.requireNonNull(login);
            this.login = login;
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
            if (login == null)
                throw new IllegalArgumentException("login cannot be null!");
            else if (password == null)
                throw new IllegalArgumentException("password cannot be null!");
            else if (host == null)
                throw new IllegalArgumentException("host cannot be null!");
            else
                return new ThreeUIAPIImpl(login, password, devMode, host);
        }
    }
}