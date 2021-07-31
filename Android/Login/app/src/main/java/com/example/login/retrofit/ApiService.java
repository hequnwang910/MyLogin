package com.example.login.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("getUserNicknameBynameByretrofitget")
    Call<String> testget(@Query("username") String str);

    @POST("getUserNicknameBynameByretrofitpost")
    @FormUrlEncoded
    Call<String> testpost(@Field("username") String str);

}
