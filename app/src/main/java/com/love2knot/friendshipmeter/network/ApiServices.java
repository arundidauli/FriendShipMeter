package com.love2knot.friendshipmeter.network;

import com.google.gson.JsonObject;
import com.love2knot.friendshipmeter.model.Articles;
import com.love2knot.friendshipmeter.response.NewsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServices {


    @Headers("Content-Type: application/json")
    @GET("?type=trending")
    Call<NewsResponse> getArticles();

    @Headers("Content-Type: application/json")
    @POST("Student/getclassSection")
    Call<NewsResponse> getSection(@Header("Auth-Key") String authHeader, @Body JsonObject jsonObject);


}
