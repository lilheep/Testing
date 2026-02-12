package client.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ReqresEnvInterceptor implements Interceptor {
    private final String devToken;

    public ReqresEnvInterceptor(String devToken) { this.devToken = devToken; }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("X-Reqres-Env", devToken)
                .build();

        return chain.proceed(request);
    }
}
