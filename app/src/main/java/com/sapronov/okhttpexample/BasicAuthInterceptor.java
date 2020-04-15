package com.sapronov.okhttpexample;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {

    private String credentials;

    public BasicAuthInterceptor(String user, String password) {
        this.credentials = Credentials.basic(user, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String url="https://api.github.com";
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials)
                .url(url+"/users/")
                .build();
        return chain.proceed(authenticatedRequest);
    }

}
