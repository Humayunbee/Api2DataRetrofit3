package com.bytesoft.api2dataretrofit2.Network;

import com.bytesoft.api2dataretrofit2.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Methods {

    @GET("posts")
    Call<List<Model>> getAllData();

    @GET("posts")
    Call<List<Model>> getDetailsData(@Query("id") int id);


}
