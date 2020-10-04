package com.example.jokes.api;

import com.example.jokes.Pojo.Example;
import com.example.jokes.Pojo.Value;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {
    String uri="jokes/random/{number}?firstName=Chuck&lastName=Norris";
    @GET(uri)
    Observable <Example> getValue(@Path("number")String jokes, @Query("firstName") String firstName, @Query("lastName") String lastName );
}
