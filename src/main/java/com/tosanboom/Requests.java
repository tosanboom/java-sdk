package com.tosanboom;

import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Requests {
    public static Request.Builder withCommonHeaders(Request.Builder builder, BoomApi boomApi) {
        if (boomApi == null) return builder;

        builder.header("Sandbox", boomApi.isSandbox() + "");
        if (boomApi.bank() != null) builder.header("Bank-Id", boomApi.bank().swiftCode());
        if (notBlank(boomApi.session())) builder.header("Session", boomApi.session());
        if (notBlank(boomApi.accessToken())) builder.header("Authorization", "Bearer " + boomApi.accessToken());
        if (notBlank(boomApi.appKey())) builder.header("App-Key", boomApi.appKey());
        if (notBlank(boomApi.boomToken())) builder.header("Token-Id", boomApi.boomToken());
        if (notBlank(boomApi.deviceId())) builder.header("Device-Id", boomApi.deviceId());
        if (boomApi.language() != null) builder.header("Accept-Language", boomApi.language().locale());

        return builder;
    }

    public static <T> T sendRequest(Request request, Class<T> clazz) {
        try {
            Response response = HttpClient.getInstance().newCall(request).execute();
            String responseBody = response.body().string();
            if (response.isSuccessful()) {
                return Json.read(responseBody, clazz);
            }

            ErrorResponse errorResponse = Json.read(responseBody, ErrorResponse.class);
            throw new RequestFailedException(errorResponse);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static boolean notBlank(String whatever) {
        return whatever != null && !whatever.trim().isEmpty();
    }
}