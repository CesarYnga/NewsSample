package com.cesarynga.newssample.data.net;

import com.cesarynga.newssample.data.entity.NewsEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {

    String BASE_URL = "https://api.backendless.com/9A93628B-54D0-4D9C-FF79-03C4606CCB00/22C006FB-0F32-7A42-FF75-C799CD790300/";

    @GET("data/News?sortBy=created desc&pageSize=100")
    Call<List<NewsEntity>> getNewsList();

    @POST("data/News")
    Call<NewsEntity> insertNews(@Body NewsEntity newsEntity);
}
