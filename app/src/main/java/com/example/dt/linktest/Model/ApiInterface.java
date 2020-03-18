package com.example.dt.linktest.Model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("articles?source=the-next-web&apiKey=533af958594143758318137469b41ba9")
    Call<Item> getCourses();

    @GET("articles?source=associated-press&apiKey=533af958594143758318137469b41ba9")
    Call<Item> getSecondCourses();

}
