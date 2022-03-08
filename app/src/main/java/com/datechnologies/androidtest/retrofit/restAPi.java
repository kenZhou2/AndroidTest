package com.datechnologies.androidtest.retrofit;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface restAPi {

    @FormUrlEncoded
    @GET("/login.php")
    Call<ResponseBody> sendCred(
        @Field("email") String email,
        @Field("password") String password
    );
}
