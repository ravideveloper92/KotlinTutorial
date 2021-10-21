package com.demo.products.retrofit;

import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
  private static final String TAG = RetrofitClient.class.getSimpleName();
  public static final String BASE_URL = "https://22966f1d-9859-4044-a923-ec55f168660e.mock.pstmn.io/api/Retailers/4990224/";
  private static volatile Retrofit mRetrofit = null;

  public synchronized static Retrofit getRetrofit() {
    if (mRetrofit == null) {
      synchronized (RetrofitClient.class) {
        if (mRetrofit == null) {
          mRetrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
              .build();
        }
      }
    }
    return mRetrofit;
  }
}