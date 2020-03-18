package com.example.dt.linktest.Model;

import de.hdodenhof.circleimageview.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    static final String BASE_URL = "https://newsapi.org/v1/";

    private static Retrofit.Builder retrofit = null;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
        }

        return retrofit;
    }

    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = getClient().client(client).build();
        return retrofit.create(serviceClass);
    }
}
