package com.example.projet.Controller;

import android.util.Log;

import android.content.SharedPreferences;
import java.lang.reflect.Type;

import com.example.projet.Vu.Fragments.FragmentEquipments;
import com.example.projet.Modele.Equipments.Equipments;
import com.example.projet.Modele.Equipments.EquipmentsRestApi;
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
 * Ce controller gère l'appel API pour les équipements.
 * API Rest + retrofit + mémoire cache.
 */

public class EquipmentsController {

    //Initialisation des variables
    private final FragmentEquipments fragmentEquipments;
    private SharedPreferences sharedPreferences;
    public List<Equipments> equipmentsList;
    private static String key = "dataEquipments";

    //Appel du controller (pour en faire appel dans les autres classes). On définit toutes nos variables.
    public EquipmentsController(FragmentEquipments fragmentEquipments, SharedPreferences sharedPreferences)
    {
        this.fragmentEquipments = fragmentEquipments;
        this.sharedPreferences = sharedPreferences;
    }
    public void onCreate() {
        if (sharedPreferences.contains(key)) { //Si le cache n'est pas vide alors on récupère ce qu'il y a dedans.

            fragmentEquipments.showLoader(); //splach de chargement
            String listJ = sharedPreferences.getString(key, null); //On récupère le json du cache
            Type listType = new TypeToken<List<Equipments>>(){}.getType(); //On définit son type
            List<Equipments> listWeapons = new Gson().fromJson(listJ, listType); //On sauve dans une liste
            fragmentEquipments.showList(listWeapons); //On affiche la liste
            fragmentEquipments.hideLoader(); //Fin du splash

        } else { //Si le cache est vide on fait un appel api et on récupère le json que l'on sauve dans le cache.
            fragmentEquipments.showLoader(); //splach de chargement
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            //Réalisation du retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://fr.dofus.dofapi.fr/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            //Appel API Rest
            EquipmentsRestApi equipmentsRestApi = retrofit.create(EquipmentsRestApi.class);
            Call<List<Equipments>> call = equipmentsRestApi.getListEquipments();
            call.enqueue(new Callback<List<Equipments>>() {
                @Override
                public void onResponse(Call<List<Equipments>> call, Response<List<Equipments>> response) {
                    equipmentsList = response.body(); //On récupère l'api

                    Gson gson = new Gson(); //Transformation en json
                    String listJ = gson.toJson(equipmentsList);

                    sharedPreferences //On sauve dans le cache
                            .edit()
                            .putString(key, listJ)
                            .apply();

                    fragmentEquipments.showList(equipmentsList); //On affiche la liste
                    fragmentEquipments.hideLoader(); //Fin du splash
                }


                @Override
                public void onFailure(Call<List<Equipments>> call, Throwable t) { //Si l'API Rest ne fonctionne pas
                    Log.d("Erreur", "API erreur");
                }
            });

        }
    }

}
