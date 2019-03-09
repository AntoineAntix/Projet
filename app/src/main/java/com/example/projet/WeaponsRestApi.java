package com.example.projet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeaponsRestApi {
    @GET("weapons")
    Call<List<Weapons>> getListWeapons();
}
