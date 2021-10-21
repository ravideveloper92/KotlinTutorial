package com.demo.products.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

  @GET("fetchFrequentlyOrderedProduct?retailerId=4990224")
  Call<ResponseBody> getProductDetails();
}
