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

import com.example.projet.Controller.EquipmentsController;
import com.example.projet.Modele.Equipments.Equipments;
import com.example.projet.Modele.Equipments.EquipmentsOnItemClick;
import com.example.projet.R;
import com.example.projet.Vu.Adapter.EquipmentsAdapter;
import com.example.projet.Vu.Activities.EquipmentsDetailsActivity;
import com.google.gson.Gson;

import java.util.List;

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Ce fragment gère l'affichage des equipements.
 */

public class FragmentEquipments extends Fragment {
    public View v ;
    private RecyclerView recyclerView;
    public static EquipmentsAdapter equipmentsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private EquipmentsController crt;
    private ProgressBar progressBar;

    public FragmentEquipments() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.equipments_fragment,container,false); //Définition du layout
        recyclerView = v.findViewById(R.id.myRecyclerViewRole); //Définition de la recyclerview
        progressBar = v.findViewById(R.id.chargement_main_activity); //Définition du splash de la progressbar

        //Appel du controller
        crt = new EquipmentsController( this, getActivity().getSharedPreferences("dataEquipment", Context.MODE_PRIVATE));
        crt.onCreate();
        return v;
    }

    public void showLoader(){
        progressBar.setVisibility(View.VISIBLE);
    }
    public void hideLoader(){
        progressBar.setVisibility(View.GONE);
    }

    //Affichage de la liste des Equipements
    public void showList(List<Equipments> listEquipments)
    {
        //Initialisation du recycleview
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //Appel de l'adapter
        equipmentsAdapter = new EquipmentsAdapter(listEquipments, getActivity().getApplicationContext(), new EquipmentsOnItemClick() {
            @Override
            public void onItemClick(Equipments equipments) {
                Toast.makeText(getActivity().getApplicationContext(), equipments.getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity().getApplicationContext(), EquipmentsDetailsActivity.class);
                Gson gson = new Gson();
                intent.putExtra("keyequipment", gson.toJson(equipments)); //Création du json au moment du clique avec les informations de l'item
                FragmentEquipments.this.startActivity(intent); //On lance l'activité details
            }
        }
        );
        recyclerView.setAdapter(equipmentsAdapter);
    }
}
