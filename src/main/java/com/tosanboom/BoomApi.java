package com.tosanboom;

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
        this.baseUrl = baseUrl;
        this.accessToken = accessToken;
        this.session = session;
        this.deviceId = deviceId;
        this.boomToken = boomToken;
        this.appKey = appKey;
        this.sandbox = sandbox;
        this.bank = bank;
        this.language = language;
    }

    public String baseUrl() {
        return baseUrl;
    }

    public String accessToken() {
        return accessToken;
    }

    public String session() {
        return session;
    }

    public String deviceId() {
        return deviceId;
    }

    public String boomToken() {
        return boomToken;
    }

    public String appKey() {
        return appKey;
    }

    public boolean isSandbox() {
        return sandbox;
    }

    public Bank bank() {
        return bank;
    }

    public Language language() {
        return language;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

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

        public Builder withAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder withSession(String session) {
            this.session = session;
            return this;
        }

        public Builder withDeviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public Builder withBoomToken(String boomToken) {
            this.boomToken = boomToken;
            return this;
        }

        public Builder withAppKey(String appKey) {
            this.appKey = appKey;
            return this;
        }

        public Builder setSandbox(boolean isSandbox) {
            this.isSandbox = isSandbox;
            return this;
        }

        public Builder setLanguage(Language language) {
            this.language = language;
            return this;
        }

        public Builder withBank(Bank bank) {
            this.bank = bank;
            return this;
        }

        public Builder withBaseUrl(String url) {
            this.baseUrl = url;
            return this;
        }

        public BoomApi build() {
            return new BoomApi(baseUrl, accessToken, session, deviceId, boomToken, appKey, isSandbox, bank, language);
        }
    }
}