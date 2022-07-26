package com.bytesoft.api2dataretrofit2.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofitclient {


    private static Retrofit retrofit;
    private static String BASE_URL="https://jsonplaceholder.typicode.com/";

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;


    }
}
