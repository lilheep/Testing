package util;

import retrofit2.Retrofit;

public class ClientUtil {
    private Retrofit retrofit;

    public ClientUtil(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public <T> T setService(Class<T> service) {
        return retrofit.create(service);
    }
}
