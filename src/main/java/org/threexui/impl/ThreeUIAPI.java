package org.threexui.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.threexui.entity.api.*;
import org.threexui.entity.api.response.BulkAdjustResponse;
import org.threexui.entity.api.response.BulkAdjustResponse.BulkAdjustData;
import org.threexui.entity.api.response.NodeTestResponse.NodeTestData;
import org.threexui.entity.api.response.ClientGetByEmailResponse;
import org.threexui.entity.api.response.ClientsPagedResponse;
import org.threexui.entity.exceptions.ThreeUIException;
import org.threexui.entity.exceptions.UnsuccessfulHttpException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Интерфейс для взаимодействия с 3x-ui API.
 */
public interface ThreeUIAPI {

    /**
     * Добавить нового клиента к существующему входящему подключению.
     * @param client данные нового клиента и ID входящего подключения
     * @return true в случае успеха
     */
    Boolean addClient(@NotNull Client client) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Сбросить статистику трафика для конкретного клиента по его email.
     * @param email email клиента
     * @return true в случае успеха
     */
    Boolean resetClientTraffic(@NotNull String email) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить файл резервной копии базы данных панели.
     * @return файл резервной копии
     */
    File getBackUpFile() throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Удалить клиента по его email.
     * @param email email клиента
     * @return true в случае успеха
     */
    Boolean deleteClient(@NotNull String email) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить список всех клиентов и их статистику трафика.
     * @return список статистики трафика клиентов
     */
    List<ClientTraffics> getClientsList() throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить список email-адресов клиентов, находящихся в данный момент в сети.
     * @return список email клиентов онлайн
     */
    List<String> getOnline() throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Обновить настройки существующего клиента.
     * @param clientSettings новые настройки клиента
     * @return true в случае успеха
     */
    Boolean updateClient(@NotNull ClientSettings clientSettings) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить общую статистику состояния панели (память, CPU, диск и т.д.).
     * @return объект с данными о состоянии
     */
    StatusPanel getStatus() throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить полный список всех входящих подключений (inbounds).
     * @return список входящих подключений
     */
    List<Inbound> getInbounds() throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить CSRF токен (используется для сессионной аутентификации).
     * @return CSRF токен
     */
    String getCsrfToken() throws UnsuccessfulHttpException, ThreeUIException, IOException;
    /**
     * Аутентифицироваться в панели с помощью логина и пароля.
     * @param username имя пользователя
     * @param password пароль
     * @param twoFactorCode код 2FA (если включена)
     * @return true в случае успеха
     */
    Boolean login(@NotNull String username, @NotNull String password, String twoFactorCode) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Завершить сессию (выйти из панели).
     * @return true в случае успеха
     */
    Boolean logout() throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Проверить, включена ли двухфакторная аутентификация.
     * @return true если 2FA включена
     */
    Boolean getTwoFactorEnable() throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить сокращенный список входящих подключений (без детализации настроек).
     * @return список сокращенных данных входящих подключений
     */
    List<Inbound> getInboundsSlim() throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить список опций входящих подключений (ID, remark, протокол, порт).
     * @return список опций
     */
    List<InboundOption> getInboundsOptions() throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить данные конкретного входящего подключения по его ID.
     * @param id уникальный идентификатор входящего подключения
     * @return объект Inbound
     */
    Inbound getInboundById(int id) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Включить или выключить входящее подключение.
     * @param id ID входящего подключения
     * @param enable true для включения, false для выключения
     * @return true в случае успеха
     */
    Boolean setInboundEnable(int id, boolean enable) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Сбросить статистику трафика для всех входящих подключений.
     * @return true в случае успеха
     */
    Boolean resetAllInboundsTraffics() throws UnsuccessfulHttpException, ThreeUIException, IOException;
    /**
     * Привязать клиента к одному или нескольким входящим подключениям.
     * @param email email клиента
     * @param inboundIds список ID входящих подключений
     * @return true в случае успеха
     */
    Boolean attachClient(@NotNull String email, @NotNull List<Integer> inboundIds) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Отвязать клиента от одного или нескольких входящих подключений.
     * @param email email клиента
     * @param inboundIds список ID входящих подключений
     * @return true в случае успеха
     */
    Boolean detachClient(@NotNull String email, @NotNull List<Integer> inboundIds) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Удалить всех клиентов с исчерпанным лимитом трафика или истекшим сроком действия.
     * @return количество удаленных клиентов
     */
    Integer delDepletedClients() throws UnsuccessfulHttpException, ThreeUIException, IOException;
    /**
     * Массово скорректировать срок действия и лимит трафика для нескольких клиентов.
     * @param emails список email клиентов
     * @param addDays количество добавляемых дней (может быть отрицательным)
     * @param addBytes количество добавляемых байт (может быть отрицательным)
     * @return результат корректировки
     */
    BulkAdjustData bulkAdjustClients(@NotNull List<String> emails, int addDays, long addBytes) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Очистить список записанных IP-адресов для клиента.
     * @param email email клиента
     * @return true в случае успеха
     */
    Boolean clearClientIps(@NotNull String email) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить карту времени последнего появления клиентов в сети.
     * @return Map где ключ - email, значение - unix timestamp
     */
    Map<String, Long> getClientLastOnline() throws UnsuccessfulHttpException, ThreeUIException, IOException;
    /**
     * Получить список ссылок подключения для всех клиентов с указанным ID подписки.
     * @param subId ID подписки
     * @return список ссылок (vless://, vmess:// и т.д.)
     */
    List<String> getSubLinks(@NotNull String subId) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить все ссылки подключения для конкретного клиента по его email.
     * @param email email клиента
     * @return список ссылок подключения
     */
    List<String> getClientLinks(@NotNull String email) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Добавить новое входящее подключение (Inbound).
     * @param inbound настройки входящего подключения
     * @return true в случае успеха
     */
    Boolean addInbound(@NotNull Inbound inbound) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Удалить входящее подключение по его ID.
     * @param id ID входящего подключения
     * @return true в случае успеха
     */
    Boolean deleteInbound(int id) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Обновить настройки входящего подключения.
     * @param id ID входящего подключения
     * @param inbound новые настройки
     * @return true в случае успеха
     */
    Boolean updateInbound(int id, @NotNull Inbound inbound) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Сбросить статистику трафика конкретного входящего подключения.
     * @param id ID входящего подключения
     * @return true в случае успеха
     */
    Boolean resetInboundTraffic(int id) throws UnsuccessfulHttpException, ThreeUIException, IOException;
    /**
     * Получить список всех настроенных узлов.
     * @return список узлов
     */
    List<Node> getNodes() throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить данные узла по его ID.
     * @param id ID узла
     * @return объект Node
     */
    Node getNodeById(int id) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Добавить новый удаленный узел.
     * @param node настройки узла
     * @return true в случае успеха
     */
    Boolean addNode(@NotNull Node node) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Обновить настройки существующего узла.
     * @param id ID узла
     * @param node новые настройки
     * @return true в случае успеха
     */
    Boolean updateNode(int id, @NotNull Node node) throws UnsuccessfulHttpException, ThreeUIException, IOException;
    /**
     * Удалить узел по его ID.
     * @param id ID узла
     * @return true в случае успеха
     */
    Boolean deleteNode(int id) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Включить или выключить синхронизацию трафика с узлом.
     * @param id ID узла
     * @param enable true для включения, false для выключения
     * @return true в случае успеха
     */
    Boolean setNodeEnable(int id, boolean enable) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Протестировать соединение с узлом без сохранения его настроек.
     * @param node настройки узла для теста
     * @return данные о состоянии узла
     */
    NodeTestData testNode(@NotNull Node node) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Опросить существующий узел для обновления данных о его состоянии.
     * @param id ID узла
     * @return true в случае успеха
     */
    Boolean probeNode(int id) throws UnsuccessfulHttpException, ThreeUIException, IOException;
    /**
     * Получить список правил fallback для входящего подключения.
     * @param id ID входящего подключения
     * @return список правил fallback
     */
    List<InboundFallback> getInboundFallbacks(int id) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Обновить список правил fallback для входящего подключения.
     * @param id ID входящего подключения
     * @param fallbacks новый список правил
     * @return true в случае успеха
     */
    Boolean updateInboundFallbacks(int id, @NotNull List<InboundFallback> fallbacks) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Найти клиента по его email.
     * @param email email клиента
     * @return данные о клиенте и связанных входящих подключениях
     */
    ClientGetByEmailResponse.ClientGetByEmailObject getClientByEmail(@NotNull String email) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить статистику трафика конкретного клиента по его email.
     * @param email email клиента
     * @return объект со статистикой трафика
     */
    ClientTraffics getClientTrafficByEmail(@NotNull String email) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить постраничный список клиентов с возможностью фильтрации и поиска.
     * @param queryParams параметры запроса (page, pageSize, search и т.д.)
     * @return объект с пагинированным списком клиентов
     */
    ClientsPagedResponse.ClientsPagedObject getClientsPaged(@NotNull Map<String, String> queryParams) throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Получить текущее состояние сервера и процесса Xray.
     * @return объект с данными о состоянии сервера
     */
    StatusPanel getServerStatus() throws UnsuccessfulHttpException, ThreeUIException, IOException;

    /**
     * Построитель для создания экземпляра ThreeUIAPI.
     */
    class Builder {

        private String token;
        private boolean devMode;
        private String host;

        /**
         * Включить режим разработки (логирование запросов).
         */
        public Builder enableDevMode() {
            this.devMode = true;
            return this;
        }

        /**
         * Установить токен доступа (Bearer token).
         * @param token API токен из настроек панели
         */
        @Contract("null -> fail")
        public Builder setToken(String token) {
            Objects.requireNonNull(token);
            this.token = token;
            return this;
        }

        /**
         * Установить адрес хоста панели.
         * @param host адрес в формате http://host:port или https://host
         */
        @Contract("null -> fail")
        public Builder setHost(String host) {
            Objects.requireNonNull(host);
            this.host = host;
            return this;
        }

        /**
         * Собрать экземпляр ThreeUIAPI.
         * @throws IllegalArgumentException если не установлены обязательные поля (token, host)
         */
        public ThreeUIAPI build() throws UnsuccessfulHttpException, ThreeUIException, IOException {
            if (token == null)
                throw new IllegalArgumentException("token cannot be null!");
            else if (host == null)
                throw new IllegalArgumentException("host cannot be null!");
            else
                return new ThreeUIAPIImpl(token, devMode, host);
        }
    }
}
