package com.example.dt.linktest.ViewModel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dt.linktest.Model.ApiClient;
import com.example.dt.linktest.Model.ApiInterface;
import com.example.dt.linktest.Model.Item;
import com.example.dt.linktest.Model.articles;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasterViewModel extends AndroidViewModel {

    ApiInterface apiService;
    Call<Item> call;
    List<articles> Returned = new ArrayList<>();
    MutableLiveData<List<articles>> data = new MutableLiveData<>();
    public MasterViewModel(@NonNull Application application) {
        super(application);
        new GetAllAsync().execute();
        new GetAllAsync2().execute();
    }

    public void OpenURL(Uri uri, Context context) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(uri);
        context.startActivity(i);
    }

    public LiveData<List<articles>> getData() {
        return data;
    }

    private class GetAllAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... Void) {
            apiService = ApiClient.createService(ApiInterface.class);
            call = apiService.getCourses();
            call.enqueue(new Callback<Item>() {
                @Override
                public void onResponse(Call<Item> call, Response<Item> response) {

                    Returned.addAll(response.body().getArticles());
                    data.setValue(Returned);

                }

                @Override
                public void onFailure(Call<Item> call, Throwable t) {
                    Log.d("asd", "onFailure: ");
                }
            });
            return null;
        }
    }

    private class GetAllAsync2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... Void) {
            apiService = ApiClient.createService(ApiInterface.class);
            call = apiService.getSecondCourses();
            call.enqueue(new Callback<Item>() {
                @Override
                public void onResponse(Call<Item> call, Response<Item> response) {

                    Returned.addAll(response.body().getArticles());
                    data.setValue(Returned);

                }

                @Override
                public void onFailure(Call<Item> call, Throwable t) {
                    Log.d("asd", "onFailure: ");
                }
            });
            return null;
        }

    }


}
