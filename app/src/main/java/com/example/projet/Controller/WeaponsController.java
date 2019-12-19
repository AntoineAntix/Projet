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

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Ce controller gère l'appel API pour les armes.
 * API Rest + retrofit + mémoire cache.
 */

public class WeaponsController {

    //Initialisation des variables
    private final FragmentWeapons weaponsActivity;
    private SharedPreferences sharedPreferences;
    public List<Weapons> listWeapons;
    private static String key = "dataWeapons";

    //Appel du controller (pour en faire appel dans les autres classes). On définit toutes nos variables.
    public WeaponsController(FragmentWeapons weaponsActivity, SharedPreferences sharedPreferences)
    {
        this.weaponsActivity = weaponsActivity;
        this.sharedPreferences = sharedPreferences;
    }
    public void onCreate() {
        if (sharedPreferences.contains(key)) { //Si le cache n'est pas vide alors on récupère ce qu'il y a dedans.

            weaponsActivity.showLoader(); //splach de chargement
            String listJ = sharedPreferences.getString(key, null); //On récupère le json du cache
            Type listType = new TypeToken<List<Weapons>>(){}.getType(); //On définit son type
            List<Weapons> listWeapons = new Gson().fromJson(listJ, listType); //On sauve dans une liste
            weaponsActivity.showList(listWeapons); //On affiche la liste
            weaponsActivity.hideLoader(); //Fin du splash

        } else { //Si le cache est vide on fait un appel api et on récupère le json que l'on sauve dans le cache.
            weaponsActivity.showLoader(); //splach de chargement
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            //Réalisation du retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://fr.dofus.dofapi.fr/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            //Appel API Rest
            WeaponsRestApi weaponsRestApi = retrofit.create(WeaponsRestApi.class);
            Call<List<Weapons>> call = weaponsRestApi.getListWeapons();
            call.enqueue(new Callback<List<Weapons>>() {
                @Override
                public void onResponse(Call<List<Weapons>> call, Response<List<Weapons>> response) {
                    listWeapons = response.body(); //On récupère l'api

                    Gson gson = new Gson(); //Transformation en json
                    String listJ = gson.toJson(listWeapons);

                    sharedPreferences //On sauve dans le cache
                            .edit()
                            .putString(key, listJ)
                            .apply();

                    weaponsActivity.showList(listWeapons); //On affiche la liste
                    weaponsActivity.hideLoader(); //Fin du splash
                }


                @Override
                public void onFailure(Call<List<Weapons>> call, Throwable t) { //Si l'API Rest ne fonctionne pas
                    Log.d("Erreur", "API erreur");
                }
            });

        }
    }

}
