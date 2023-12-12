package org.mego.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mego.entity.api.Client;
import org.mego.entity.api.ClientTraffics;
import org.mego.entity.exceptions.UnsuccessfulHttpException;

import java.io.IOException;
import java.util.Objects;

public interface ThreeUIAPI {

    /**
     * @param inboundId It`s ID from panel
     * @param client    new client settings
     * @return {@link Boolean} status
     */
    Boolean addClient(int inboundId, @NotNull Client client) throws UnsuccessfulHttpException;

    /**
     * @param inboundId It`s ID from panel
     * @param email     client email for SS. UUID For VLESS
     * @return {@link Boolean} status
     */
    Boolean deleteClient(int inboundId, @NotNull String email) throws UnsuccessfulHttpException;

    /**
     * @param email client email
     * @return {@link ClientTraffics} Statistics on the use of client traffic
     */
    ClientTraffics getClientTraffics(@NotNull String email) throws UnsuccessfulHttpException, IOException;

//    ClientsIDs getOnline() throws UnsuccessfulHttpException;

    /**
     * @param inboundId It`s ID from panel
     * @param client    updated customer data
     * @return {@link Boolean} status
     * <p><br>
     * Some data is better not to change. Since I myself have not yet understood what this can lead to.
     * It's best to use this to turn on and off. You can also change the limits.
     */
    Boolean updateClient(int inboundId, @NotNull Client client) throws UnsuccessfulHttpException;

    /**
     * Setup new Session
     */
    void setSession() throws UnsuccessfulHttpException;

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
        public ThreeUIAPI build() {
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