package ir.boommarket;

import java.net.URI;

/**
 * Encapsulates contextual information about the Boom's REST API. Most of the parameters
 * encapsulated in an instance of {@linkplain BoomApi} will manifest themselves as HTTP
 * headers.
 *
 * <p>
 *      <strong>Note: </strong>{@linkplain BoomApi} is immutable and thread-safe
 * </p>
 *
 * <h1>How to build?</h1>
 * In order to create a basic instance of {@linkplain BoomApi}, you can use the
 * {@linkplain BoomApi#newBuilder()} factory method. There are four common scenarios when
 * you're creating a {@linkplain BoomApi}, which will be described in the following sections.
 *
 * <h6>Public APIs</h6>
 * Some APIs are <em>Public</em> in a sense that they don't require any authentication or
 * authorization related metadata. So, for creating an instance of {@linkplain BoomApi} to
 * call a public API, just use the following:
 * <pre>
 *      BoomApi boomApi = BoomApi.forPublicApi();
 * </pre>
 *
 * <h6>OAuth & Access Token</h6>
 * If you're using our OAuth 2.0 flows and already got an <em>Access Token</em>, you can build
 * a very minimal and valid instance like the following:
 * <pre>
 *      BoomApi boomApi = BoomApi.newBuilder()
 *                               .withAccessToken("your_access_token")
 *                               .build()
 * </pre>
 * By default, all requests will be routed to our <em>Live</em> environment. If you're willing to
 * use the <em>Sandbox</em> environment, you can use the {@linkplain Builder#setSandbox(boolean)}
 * method:
 * <pre>
 *      BoomApi boomApi = BoomApi.newBuilder()
 *                               .withAccessToken("your_access_token")
 *                               .setSandbox(true)
 *                               .build();
 * </pre>
 * Current base URL for all services is {@literal https://app.tosanboom.com:4432/v1/}. You also can
 * change it like:
 * <pre>
 *      BoomApi boomApi = BoomApi.newBuilder()
 *                               .withAccessToken("your_access_token")
 *                               .withBaseUrl("https://api.tosanboom.com/")
 *                               .setSandbox(true)
 *                               .build();
 * </pre>
 *
 * <h6>Session</h6>
 * If you've got a {@code Session} from our old login APIs, you should provide following parameters to
 * create a {@linkplain BoomApi} applicable for protected services:
 * <pre>
 *      BoomApi boomApi = BoomApi.newBuilder()
 *                               .withBoomToken("boom_token")
 *                               .withAppKey("app_key")
 *                               .withBank(Bank.ANSAR)
 *                               .withDeviceId("device_id")
 *                               .setSandbox(true)
 *                               .withSession("session")
 *                               .build();
 * </pre>
 *
 * <h6>Card APIs</h6>
 * Some card services don't require the session or access token, since they already got card credentials.
 * So for calling them, create a {@linkplain BoomApi} just like the previous scenario but without providing
 * a session:
 * <pre>
 *      BoomApi boomApi = BoomApi.newBuilder()
 *                               .withBoomToken("boom_token")
 *                               .withAppKey("app_key")
 *                               .withBank(Bank.ANSAR)
 *                               .withDeviceId("device_id")
 *                               .setSandbox(true)
 *                               .build();
 * </pre>
 *
 * @author Ali Dehghani
 */
public class BoomApi {
    private static final String DEFAULT_BASE_URL = "https://app.tosanboom.com:4432/v1/";

    private final String baseUrl;
    private final String accessToken;
    private final String session;
    private final String deviceId;
    private final String boomToken;
    private final String appKey;
    private final boolean sandbox;
    private final Bank bank;
    private final Language language;

    private BoomApi(String baseUrl, String accessToken, String session, String deviceId, String boomToken,
                    String appKey, boolean sandbox, Bank bank, Language language) {
        this.baseUrl = getBaseURL(baseUrl);
        this.accessToken = accessToken;
        this.session = session;
        this.deviceId = deviceId;
        this.boomToken = boomToken;
        this.appKey = appKey;
        this.sandbox = sandbox;
        this.bank = bank;
        this.language = language;
    }

    /**
     * The base url which will be used as a prefix for all service calls. This base url
     * will always will end with a trailing slash, even if you won't provide one.
     *
     * @return The base URL
     */
    public String baseUrl() {
        return baseUrl;
    }

    /**
     * Return OAuth access token
     *
     * @return OAuth access token
     */
    public String accessToken() {
        return accessToken;
    }

    /**
     * The session fetch from our old Login APIs
     *
     * @return The internet bank session
     * @see ir.boommarket.accounts.Accounts#bankLogin(ir.boommarket.accounts.BankLoginRequest, BoomApi)
     */
    public String session() {
        return session;
    }

    /**
     * The device id that you got the boom token with
     *
     * @return The device
     * @see #boomToken()
     */
    public String deviceId() {
        return deviceId;
    }

    /**
     * The token issued by the Boom Store
     *
     * @return The boom token
     */
    public String boomToken() {
        return boomToken;
    }

    /**
     * The {@literal App Key} or {@literal API Key} of your application
     *
     * @return The app key
     */
    public String appKey() {
        return appKey;
    }

    /**
     * Whether this is a sandbox bound {@linkplain BoomApi} or not
     *
     * @return Is this sandboxed {@linkplain BoomApi}?
     */
    public boolean isSandbox() {
        return sandbox;
    }

    /**
     * The bank to use
     *
     * @return The bank
     */
    public Bank bank() {
        return bank;
    }

    /**
     * Language being used for Content Negotiation
     *
     * @return Language being used for Content Negotiation
     */
    public Language language() {
        return language;
    }

    /**
     * Create a {@linkplain BoomApi} for calling a Public API
     *
     * @return An instance of {@linkplain BoomApi} for calling Public APIs
     */
    public static BoomApi forPublicApi() {
        return newBuilder().build();
    }

    /**
     * A static factory method that acts as a nexus to the fluent {@linkplain Builder} abstraction.
     *
     * @return A very raw instance of {@linkplain Builder}
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    private String getBaseURL(String baseUrl) {
        try {
            new URI(baseUrl);

            final String TRAILING_SLASH = "/";
            return baseUrl.endsWith(TRAILING_SLASH) ? baseUrl : baseUrl + TRAILING_SLASH;
        } catch (Exception e) {
            throw new IllegalArgumentException("Base URL is not a valid URL: " + baseUrl, e);
        }
    }

    /**
     * Builder for creating an instance of {@linkplain BoomApi}. Using the fluent interface provided by
     * this class, you can chain multiple options together and when you think this is the time, build
     * an actual instance of {@linkplain BoomApi} by calling the {@linkplain #build()} method. Please
     * note that all validations will be deferred until you call the {@linkplain #build()} method.
     *
     * @see BoomApi#forPublicApi()
     * @see BoomApi#newBuilder()
     */
    public static class Builder {
        private String baseUrl = DEFAULT_BASE_URL;
        private String accessToken;
        private String session;
        private String deviceId;
        private String boomToken;
        private String appKey;
        private boolean isSandbox = false;
        private Bank bank;
        private Language language = Language.FARSI;

        /**
         * Sets the access token fetched from our OAuth 2.0 flows
         *
         * @param accessToken The access token
         * @return The builder itself
         */
        public Builder withAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        /**
         * Sets the session fetched from our old Login APIs
         *
         * @param session The internet bank session
         * @return The builder itself
         */
        public Builder withSession(String session) {
            this.session = session;
            return this;
        }

        /**
         * Sets the device id you used to get a boom token
         *
         * @param deviceId The device id
         * @return The builder itself
         * @see #withBoomToken(String)
         */
        public Builder withDeviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        /**
         * Sets the boom token. This is the token that you get from our App Store.
         *
         * @param boomToken The boom token
         * @return The builder itself
         */
        public Builder withBoomToken(String boomToken) {
            this.boomToken = boomToken;
            return this;
        }

        /**
         * The app key you got after client registration
         *
         * @param appKey The app key
         * @return The builder itself
         */
        public Builder withAppKey(String appKey) {
            this.appKey = appKey;
            return this;
        }

        /**
         * Determines whether this is a sandbox environment or not. Default is {@code false}
         *
         * @param isSandbox Set {@code true} if you want a sandboxed {@linkplain BoomApi}
         * @return The builder itself
         */
        public Builder setSandbox(boolean isSandbox) {
            this.isSandbox = isSandbox;
            return this;
        }

        /**
         * Sets the language that will be used for Content Negotiation. Default is {@linkplain Language#FARSI}
         *
         * @param language The language
         * @return The builder itself
         */
        public Builder setLanguage(Language language) {
            this.language = language;
            return this;
        }

        /**
         * Sets the destination bank, this is the bank that eventually will get our requests
         *
         * @param bank The bank or financial institution
         * @return The bank itself
         */
        public Builder withBank(Bank bank) {
            this.bank = bank;
            return this;
        }

        /**
         * The base url for our REST API
         *
         * @param url The base url
         * @return The builder itself
         */
        public Builder withBaseUrl(String url) {
            this.baseUrl = url;
            return this;
        }

        /**
         * Build an instance of {@linkplain BoomApi} from the accumulated state of the {@linkplain Builder}.
         *
         * @return An instance of {@linkplain BoomApi}
         * @throws IllegalArgumentException If the {@code baseUrl} were a invalid URL.
         */
        public BoomApi build() {
            return new BoomApi(baseUrl, accessToken, session, deviceId, boomToken, appKey, isSandbox, bank, language);
        }
    }
}