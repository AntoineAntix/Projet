package com.example.projet;

import android.util.Log;

import android.content.SharedPreferences;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

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
    private SharedPreferences sharedPreferences;
    private static Controller controller = null;

    private static String key = "data";

    public static Controller getInstance(MainActivity mainActivity, SharedPreferences sharedPreferences)
    {
        if(controller ==null)
        {
            controller =new Controller(mainActivity, sharedPreferences);
        }
        return controller;
    }
    public Controller(MainActivity mainActivity, SharedPreferences sharedPreferences)
    {
        this.mainActivity=mainActivity;
        this.sharedPreferences = sharedPreferences;
    }
    public void onCreate() {
        if (sharedPreferences.contains(key)) {

            mainActivity.showLoader();
            String listJ = sharedPreferences.getString(key, null);
            Type listType = new TypeToken<List<Weapons>>(){}.getType();
            List<Weapons> listWeapons = new Gson().fromJson(listJ, listType);
            mainActivity.showList(listWeapons);
            mainActivity.hideLoader();


        } else {
            mainActivity.showLoader();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://dofapi2.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            WeaponsRestApi weaponsRestApi = retrofit.create(WeaponsRestApi.class);

            Call<List<Weapons>> call = weaponsRestApi.getListWeapons();
            call.enqueue(new Callback<List<Weapons>>() {
                @Override
                public void onResponse(Call<List<Weapons>> call, Response<List<Weapons>> response) {
                    List<Weapons> listWeapons = response.body();

                    Gson gson = new Gson();
                    String listJ = gson.toJson(listWeapons);

                    sharedPreferences
                            .edit()
                            .putString(key, listJ)
                            .apply();

                    mainActivity.showList(listWeapons);
                    mainActivity.hideLoader();
                }


                @Override
                public void onFailure(Call<List<Weapons>> call, Throwable t) {
                    Log.d("Erreur", "API erreur");
                }
            });

        }
    }

}
