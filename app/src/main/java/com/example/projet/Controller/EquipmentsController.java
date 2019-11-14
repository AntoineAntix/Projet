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

public class EquipmentsController {

    private final FragmentEquipments fragmentEquipments;
    private SharedPreferences sharedPreferences;
    private static EquipmentsController equipmentsController = null;

    public List<Equipments> equipmentsList;

    private static String key = "dataEquipments";

    public static EquipmentsController getInstance(FragmentEquipments mainActivity, SharedPreferences sharedPreferences)
    {
        if(equipmentsController ==null)
        {
            equipmentsController =new EquipmentsController(mainActivity, sharedPreferences);
        }
        return equipmentsController;
    }
    public EquipmentsController(FragmentEquipments fragmentEquipments, SharedPreferences sharedPreferences)
    {
        this.fragmentEquipments = fragmentEquipments;
        this.sharedPreferences = sharedPreferences;
    }
    public void onCreate() {
        if (sharedPreferences.contains(key)) {

            fragmentEquipments.showLoader();
            String listJ = sharedPreferences.getString(key, null);
            Type listType = new TypeToken<List<Equipments>>(){}.getType();
            List<Equipments> listWeapons = new Gson().fromJson(listJ, listType);
            fragmentEquipments.showList(listWeapons);
            fragmentEquipments.hideLoader();


        } else {
            fragmentEquipments.showLoader();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://fr.dofus.dofapi.fr/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            EquipmentsRestApi equipmentsRestApi = retrofit.create(EquipmentsRestApi.class);

            Call<List<Equipments>> call = equipmentsRestApi.getListEquipments();
            call.enqueue(new Callback<List<Equipments>>() {
                @Override
                public void onResponse(Call<List<Equipments>> call, Response<List<Equipments>> response) {
                    equipmentsList = response.body();

                    Gson gson = new Gson();
                    String listJ = gson.toJson(equipmentsList);

                    sharedPreferences
                            .edit()
                            .putString(key, listJ)
                            .apply();

                    fragmentEquipments.showList(equipmentsList);
                    fragmentEquipments.hideLoader();
                }


                @Override
                public void onFailure(Call<List<Equipments>> call, Throwable t) {
                    Log.d("Erreur", "API erreur");
                }
            });

        }
    }

}
