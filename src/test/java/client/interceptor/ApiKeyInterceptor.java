package client.interceptor;

import lombok.SneakyThrows;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

public class ApiKeyInterceptor implements Interceptor {
    private final String apiKey;

    public ApiKeyInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @NotNull
    @Override
    @SneakyThrows
    public Response intercept(@NotNull Chain chain) {
        Request request = chain.request().newBuilder()
                .addHeader("x-api-key", apiKey)
                .build();

        return chain.proceed(request);
    }
}
