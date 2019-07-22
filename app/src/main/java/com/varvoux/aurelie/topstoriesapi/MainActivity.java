package com.varvoux.aurelie.topstoriesapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.varvoux.aurelie.topstoriesapi.pojo.TopStoriesParentObject;
import com.varvoux.aurelie.topstoriesapi.retrofit.RetrofitClient;
import com.varvoux.aurelie.topstoriesapi.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initRecyclerView( new TopStoriesParentObject());


        RetrofitService retrofitService = RetrofitClient.getInstance().create(RetrofitService.class);
        Call<TopStoriesParentObject> call = retrofitService.getTopStories();
        call.enqueue(new Callback<TopStoriesParentObject>() {
            @Override
            public void onResponse(Call<TopStoriesParentObject> call, Response<TopStoriesParentObject> response) {
                findViewById(R.id.spinner).setVisibility(View.GONE);
                initRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<TopStoriesParentObject> call, Throwable t) {
                Log.e("TAG",t.getMessage());
            }
        });

    }

    private void initRecyclerView(TopStoriesParentObject parentObject){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        TopStoriesAdapter adapter = new TopStoriesAdapter(parentObject.getResults(),MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }
}
