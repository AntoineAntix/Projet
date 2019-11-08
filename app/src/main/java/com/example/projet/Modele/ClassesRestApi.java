package com.example.projet.Modele;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClassesRestApi {
    @GET("classes")
    Call<List<Classe>> getListClasses();
}
