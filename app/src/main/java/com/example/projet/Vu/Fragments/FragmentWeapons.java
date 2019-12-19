package com.example.projet.Vu.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.Controller.WeaponsController;
import com.example.projet.Modele.Weapons.Weapons;
import com.example.projet.Modele.Weapons.WeaponsOnItemClick;
import com.example.projet.R;
import com.example.projet.Vu.Adapter.WeaponsAdapter;
import com.example.projet.Vu.Activities.WeaponsDetailsActivity;
import com.google.gson.Gson;

import java.util.List;

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Ce fragment gère l'affichage des weapons.
 */

public class FragmentWeapons extends Fragment {
    public View v ;
    private RecyclerView recyclerView;
    public static WeaponsAdapter weaponsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private WeaponsController crt;
    private ProgressBar progressBar;

    public FragmentWeapons() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.weapons_fragment,container,false); //Définition du layout
        recyclerView = v.findViewById(R.id.myRecyclerViewRole); //Définition de la recyclerview
        progressBar = v.findViewById(R.id.chargement_main_activity); //Définition du splash de la progressbar

        //Appel du controller
        crt = new WeaponsController( this, getActivity().getSharedPreferences("dataWeapons", Context.MODE_PRIVATE));
        crt.onCreate();
        return v;
    }

    public void showLoader(){
        progressBar.setVisibility(View.VISIBLE);
    }
    public void hideLoader(){
        progressBar.setVisibility(View.GONE);
    }

    //Affichage de la liste des armes
    public void showList(List<Weapons> listWeapons)
    {
        //Initialisation du recycleview
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //Appel de l'adapter
        weaponsAdapter = new WeaponsAdapter(listWeapons, getActivity().getApplicationContext(), new WeaponsOnItemClick() {
            @Override
            public void onItemClick(Weapons arme) {
                Toast.makeText(getActivity().getApplicationContext(), arme.getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity().getApplicationContext(), WeaponsDetailsActivity.class);
                Gson gson = new Gson();
                intent.putExtra("keyweapons", gson.toJson(arme)); //Création du json au moment du clique avec les informations de l'item
                FragmentWeapons.this.startActivity(intent); //On lance l'activité details
            }
        }
        );
        recyclerView.setAdapter(weaponsAdapter);
    }
}