package com.example.projet;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {

    private final MainActivity mainActivity;
    private static Controller controller =null;

    public static Controller getInstance(MainActivity mainActivity)
    {
        if(controller ==null)
        {
            controller =new Controller(mainActivity);
        }
        return controller;
    }
    public Controller(MainActivity mainActivity)
    {
        this.mainActivity=mainActivity;
    }
    public void onCreate()
    {
        mainActivity.showLoader();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dofapi2.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        WeaponsRestApi weaponsRestApi=retrofit.create(WeaponsRestApi.class);

        Call<List<Weapons>> call = weaponsRestApi.getListWeapons();
        call.enqueue(new Callback<List<Weapons>>() {
            @Override
            public void onResponse(Call<List<Weapons>> call, Response<List<Weapons>> response) {
                List<Weapons> listWeapons = response.body();
                mainActivity.showList(listWeapons);
                mainActivity.hideLoader();
            }



            @Override
            public void onFailure(Call<List<Weapons>> call, Throwable t) {
                Log.d("Erreur" , "API erreur");
            }
        });

    }

}
