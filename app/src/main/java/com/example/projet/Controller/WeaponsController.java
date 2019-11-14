package com.example.projet.Controller;

import android.util.Log;

import android.content.SharedPreferences;
import java.lang.reflect.Type;

import com.example.projet.Vu.Fragments.FragmentWeapons;
import com.example.projet.Modele.Weapons.Weapons;
import com.example.projet.Modele.Weapons.WeaponsRestApi;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeaponsController {

    private final FragmentWeapons weaponsActivity;
    private SharedPreferences sharedPreferences;
    private static WeaponsController weaponsController = null;

    public List<Weapons> listWeapons;

    private static String key = "dataWeapons";

    public static WeaponsController getInstance(FragmentWeapons mainActivity, SharedPreferences sharedPreferences)
    {
        if(weaponsController ==null)
        {
            weaponsController =new WeaponsController(mainActivity, sharedPreferences);
        }
        return weaponsController;
    }
    public WeaponsController(FragmentWeapons weaponsActivity, SharedPreferences sharedPreferences)
    {
        this.weaponsActivity = weaponsActivity;
        this.sharedPreferences = sharedPreferences;
    }
    public void onCreate() {
        if (sharedPreferences.contains(key)) {

            weaponsActivity.showLoader();
            String listJ = sharedPreferences.getString(key, null);
            Type listType = new TypeToken<List<Weapons>>(){}.getType();
            List<Weapons> listWeapons = new Gson().fromJson(listJ, listType);
            weaponsActivity.showList(listWeapons);
            weaponsActivity.hideLoader();


        } else {
            weaponsActivity.showLoader();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://fr.dofus.dofapi.fr/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            WeaponsRestApi weaponsRestApi = retrofit.create(WeaponsRestApi.class);

            Call<List<Weapons>> call = weaponsRestApi.getListWeapons();
            call.enqueue(new Callback<List<Weapons>>() {
                @Override
                public void onResponse(Call<List<Weapons>> call, Response<List<Weapons>> response) {
                    listWeapons = response.body();

                    Gson gson = new Gson();
                    String listJ = gson.toJson(listWeapons);

                    sharedPreferences
                            .edit()
                            .putString(key, listJ)
                            .apply();

                    weaponsActivity.showList(listWeapons);
                    weaponsActivity.hideLoader();
                }


                @Override
                public void onFailure(Call<List<Weapons>> call, Throwable t) {
                    Log.d("Erreur", "API erreur");
                }
            });

        }
    }

}
