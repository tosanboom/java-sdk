package com.tosanboom;

import okhttp3.OkHttpClient;

public class HttpClient {
    public static OkHttpClient getInstance() {
        return HttpClientHolder.INSTANCE;
    }

    private static class HttpClientHolder {
        static final OkHttpClient INSTANCE = new OkHttpClient.Builder().build();
    }
}