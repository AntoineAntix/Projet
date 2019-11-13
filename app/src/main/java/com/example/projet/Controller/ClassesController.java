package com.example.projet.Controller;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.projet.Modele.Classe;
import com.example.projet.Modele.ClassesRestApi;
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

public class ClassesController {
    private final FragmentClasses classesActivity;
    private SharedPreferences sharedPreferences;
    private static ClassesController classesController = null;

    public List<Classe> listClasses;

    private static String keyClasses = "dataClasse";

    public static ClassesController getInstance(FragmentClasses mainActivity, SharedPreferences sharedPreferences) {
        if (classesController == null) {
            classesController = new ClassesController(mainActivity, sharedPreferences);
        }
        return classesController;
    }

    public ClassesController(FragmentClasses classesActivity, SharedPreferences sharedPreferences) {
        this.classesActivity = classesActivity;
        this.sharedPreferences = sharedPreferences;
    }

    public void onCreate() {
        if (sharedPreferences.contains(keyClasses)) {

            classesActivity.showLoader();
            String listJ = sharedPreferences.getString(keyClasses, null);
            Type listType = new TypeToken<List<Classe>>() {
            }.getType();
            List<Classe> listClasses = new Gson().fromJson(listJ, listType);
            classesActivity.showList(listClasses);
            classesActivity.hideLoader();


        } else {
            classesActivity.showLoader();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://fr.dofus.dofapi.fr/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            ClassesRestApi classesRestApi = retrofit.create(ClassesRestApi.class);

            Call<List<Classe>> call = classesRestApi.getListClasses();
            call.enqueue(new Callback<List<Classe>>() {
                @Override
                public void onResponse(Call<List<Classe>> call, Response<List<Classe>> response) {
                    listClasses = response.body();

                    Gson gson = new Gson();
                    String listJ = gson.toJson(listClasses);

                    sharedPreferences
                            .edit()
                            .putString(keyClasses, listJ)
                            .apply();

                    classesActivity.showList(listClasses);
                    classesActivity.hideLoader();
                }

                @Override
                public void onFailure(Call<List<Classe>> call, Throwable t) {
                    Log.d("Erreur", "API erreur");
                }
            });

        }
    }
}

