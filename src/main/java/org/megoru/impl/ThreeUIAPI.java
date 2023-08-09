package org.megoru.impl;

import org.jetbrains.annotations.NotNull;
import org.megoru.entity.Status;
import org.megoru.entity.api.Client;
import org.megoru.entity.api.ClientTraffics;
import org.megoru.io.ResponseHandler;
import org.megoru.io.UnsuccessfulHttpException;

public interface ThreeUIAPI {

//    /**
//     * @param botStats botStats class
//     *                <p>Example:
//     *                <p>AtomicInteger usersCount = new AtomicInteger();
//     *                <p>jda.getGuilds().forEach(g -> usersCount.addAndGet(g.getMembers().size()));
//     * @return {@link BotInfo}
//     */

    Boolean addClient(int inboundId, @NotNull Client client) throws UnsuccessfulHttpException;


    ClientTraffics getClientTraffics(String email) throws UnsuccessfulHttpException;

    /**
     * Setup new Session
     */
    void setSession() throws UnsuccessfulHttpException;

    class Builder {

        // Required
        private String login;
        private String password;
        private boolean devMode;

        /**
         * This enables LOGS
         */
        public Builder enableDevMode() {
            this.devMode = true;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        /**
         * @throws IllegalArgumentException if token or botId null
         */
        public ThreeUIAPI build() {
            if (login == null || password == null)
                throw new IllegalArgumentException("login|password cannot be null!");

            return new ThreeUIAPIImpl(login, password, devMode);
        }
    }
}