package com.datechnologies.androidtest.api.apiInterface;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserClient {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> sendCred(
            @Field("email") String email,
            @Field("password") String password
    );


}
