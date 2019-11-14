package com.example.projet.Modele.Weapons;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeaponsRestApi {
    @GET("weapons")
    Call<List<Weapons>> getListWeapons();
}