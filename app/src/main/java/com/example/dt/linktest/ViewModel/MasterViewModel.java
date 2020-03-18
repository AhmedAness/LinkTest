package com.example.dt.linktest.ViewModel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dt.linktest.Model.ApiClient;
import com.example.dt.linktest.Model.ApiInterface;
import com.example.dt.linktest.Model.Item;
import com.example.dt.linktest.Model.articles;
import com.example.dt.linktest.View.Activities.MainActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasterViewModel extends AndroidViewModel {
    ApiInterface apiService;
    Call<Item> call;
    MutableLiveData<List<articles> > Allcourses = new MutableLiveData<>();

    public MasterViewModel(@NonNull Application application) {
        super(application);
    }

    public void OpenURL(Uri uri, Context context) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(uri);
        context.startActivity(i);
    }

    public LiveData<List<articles>> getData() {
        return Allcourses;
    }

    public void LoadData() {
        apiService = ApiClient.createService(ApiInterface.class);
        call = apiService.getCourses();
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                LoadSecond(response.body().getArticles());
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d("asd", "onFailure: ");
            }
        });

    }

    private void LoadSecond(ArrayList<articles> articles) {
        apiService = ApiClient.createService(ApiInterface.class);
        call = apiService.getSecondCourses();
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                articles.addAll(response.body().getArticles());

                Allcourses.setValue(articles);

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d("asd", "onFailure: ");
            }
        });
    }


}
