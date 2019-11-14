package com.example.projet.Modele.Equipments;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EquipmentsRestApi {
    @GET("equipments")
    Call<List<Equipments>> getListEquipments();
}
