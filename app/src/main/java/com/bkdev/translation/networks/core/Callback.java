package com.bkdev.translation.networks.core;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;

public abstract class Callback<T> implements retrofit2.Callback<T> {


    public Callback() {
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.i("Bottom","code"+response.code());
        success(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        failure(new Error(t.getMessage()));
    }

    public abstract void success(T t);

    public abstract void failure(Error myError);

}
