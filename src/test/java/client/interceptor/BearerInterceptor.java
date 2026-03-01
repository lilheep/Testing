package client.interceptor;

import lombok.SneakyThrows;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;


public class BearerInterceptor implements Interceptor {
    private String bearerToken;

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    @NotNull
    @Override
    @SneakyThrows
    public Response intercept(@NotNull Chain chain){
        Request oldRequest = chain.request();

        if (bearerToken != null && !bearerToken.isEmpty()) {
            Request newRequest = oldRequest.newBuilder()
                    .addHeader("Authorization", "Bearer " + bearerToken)
                    .build();
            return chain.proceed(newRequest);
        }

        return chain.proceed(oldRequest);
    }
}
