package com.example.retrofitmovie;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClient {
    private static Retrofit zretrofit = null;
    static Retrofit getClient(){
        zretrofit = new Retrofit.Builder()
                //접속하고자 하는 / 연결 시킬 네트워크의 베이스 url
                .baseUrl("http://mellowcode.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return zretrofit;
    }
}
