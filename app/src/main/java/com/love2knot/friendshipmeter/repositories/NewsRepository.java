package com.love2knot.friendshipmeter.repositories;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.love2knot.friendshipmeter.model.Articles;
import com.love2knot.friendshipmeter.network.ApiClient;
import com.love2knot.friendshipmeter.network.ApiServices;
import com.love2knot.friendshipmeter.response.NewsResponse;
import com.love2knot.friendshipmeter.utils.MySingleton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private static final String TAG = NewsRepository.class.getSimpleName();
    ApiServices apiServices = ApiClient.getClient().create(ApiServices.class);

    public NewsRepository() {

    }

    public MutableLiveData<List<Articles>> getArticles() {
        MutableLiveData<List<Articles>> mutableLiveData = new MutableLiveData<>();
        apiServices.getArticles().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {
                if (response.body() != null) {
                    mutableLiveData.postValue(response.body().getArticles());
                }
                Log.e(TAG, response.toString());
                // Toast.makeText(MySingleton.getInstance(),response.body().getMessage(),Toast.LENGTH_LONG).show();

            }


            @Override
            public void onFailure(@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
                mutableLiveData.postValue(null);
                Log.e(TAG, t.getMessage());
                Toast.makeText(MySingleton.getInstance(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        return mutableLiveData;

    }

}
