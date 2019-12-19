package com.example.projet.Modele.Equipments;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface créée par LUCAS Antoine pour le 20/12/2019.
 * Interface permettant de gérer le get de l'appel API.
 */

public interface EquipmentsRestApi {
    @GET("equipments")
    Call<List<Equipments>> getListEquipments();
}
