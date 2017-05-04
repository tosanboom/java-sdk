package ir.boommarket;

import okhttp3.OkHttpClient;

/**
 * @author Ali Dehghani
 */
public class HttpClient {
    public static OkHttpClient getInstance() {
        return HttpClientHolder.INSTANCE;
    }

    private static class HttpClientHolder {
        static final OkHttpClient INSTANCE = new OkHttpClient.Builder().build();
    }
}