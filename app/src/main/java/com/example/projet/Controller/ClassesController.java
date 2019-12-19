package com.example.projet.Controller;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.projet.Modele.Classes.Classe;
import com.example.projet.Modele.Classes.ClassesRestApi;
import com.example.projet.Vu.Fragments.FragmentClasses;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Ce controller gère l'appel API pour les classes.
 * API Rest + retrofit + mémoire cache.
 */

public class ClassesController {

    //Initialisation des variables
    private final FragmentClasses classesActivity;
    private SharedPreferences sharedPreferences;
    public List<Classe> listClasses;
    private static String keyClasses = "dataClasse";

    //Appel du controller (pour en faire appel dans les autres classes). On définit toutes nos variables.
    public ClassesController(FragmentClasses classesActivity, SharedPreferences sharedPreferences) {
        this.classesActivity = classesActivity;
        this.sharedPreferences = sharedPreferences;
    }

    public void onCreate() {
        //Si le cache n'est pas vide alors on récupère ce qu'il y a dedans.
        if (sharedPreferences.contains(keyClasses)) {

            classesActivity.showLoader(); //splach de chargement
            String listJ = sharedPreferences.getString(keyClasses, null); //On récupère le json du cache
            Type listType = new TypeToken<List<Classe>>() {}.getType(); //On définit son type
            List<Classe> listClasses = new Gson().fromJson(listJ, listType); //On sauve dans une liste
            classesActivity.showList(listClasses); //On affiche la liste
            classesActivity.hideLoader(); //Fin du splash

        } else { //Si le cache est vide on fait un appel api et on récupère le json que l'on sauve dans le cache.
            classesActivity.showLoader(); //splach de chargement
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            //Réalisation du retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://fr.dofus.dofapi.fr/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            //Appel API Rest
            ClassesRestApi classesRestApi = retrofit.create(ClassesRestApi.class);
            Call<List<Classe>> call = classesRestApi.getListClasses();
            call.enqueue(new Callback<List<Classe>>() {
                @Override
                public void onResponse(Call<List<Classe>> call, Response<List<Classe>> response) {
                    listClasses = response.body(); //On récupère l'api

                    Gson gson = new Gson(); //Transformation en json
                    String listJ = gson.toJson(listClasses);

                    sharedPreferences //On sauve dans le cache
                            .edit()
                            .putString(keyClasses, listJ)
                            .apply();

                    classesActivity.showList(listClasses); //On affiche la liste
                    classesActivity.hideLoader(); //Fin du splash
                }

                @Override
                public void onFailure(Call<List<Classe>> call, Throwable t) { //Si l'API Rest ne fonctionne pas
                    Log.d("Erreur", "API erreur");
                }
            });

        }
    }
}

