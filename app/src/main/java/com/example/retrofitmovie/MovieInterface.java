package com.example.retrofitmovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieInterface {
    @GET("json/students/")
    Call<List<Movie>> goGetMovie();
}
