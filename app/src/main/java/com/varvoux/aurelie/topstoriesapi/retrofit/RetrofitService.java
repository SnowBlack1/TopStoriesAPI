package com.varvoux.aurelie.topstoriesapi.retrofit;

import com.varvoux.aurelie.topstoriesapi.pojo.TopStoriesParentObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("/svc/topstories/v2/home.json?api-key=EjFoG6oBEBGEC5vowClsef2PQiayA4Tz")
    Call<TopStoriesParentObject> getTopStories();
}
