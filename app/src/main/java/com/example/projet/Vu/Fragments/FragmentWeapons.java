package com.example.projet.Vu.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import com.example.projet.Controller.WeaponsController;
import com.example.projet.Modele.Weapons.Weapons;
import com.example.projet.Modele.Weapons.WeaponsOnItemClick;
import com.example.projet.R;
import com.example.projet.Vu.Adapter.WeaponsAdapter;
import com.example.projet.Vu.Activities.WeaponsDetailsActivity;

import java.util.List;

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
        v = inflater.inflate(R.layout.weapons_fragment,container,false);
        recyclerView = v.findViewById(R.id.myRecyclerView);
        progressBar = v.findViewById(R.id.chargement_main_activity);
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
    public void showList(List<Weapons> listWeapons)
    {
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        weaponsAdapter = new WeaponsAdapter(listWeapons, getActivity().getApplicationContext(), new WeaponsOnItemClick() {
            @Override
            public void onItemClick(Weapons arme) {
                Toast.makeText(getActivity().getApplicationContext(), arme.getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity().getApplicationContext(), WeaponsDetailsActivity.class);
                intent.putExtra("nom", arme.getName());
                intent.putExtra("description", arme.getDescription());
                intent.putExtra("lvl", arme.getLvl());
                intent.putExtra("type", arme.getType());
                intent.putExtra("image", arme.getImgUrl());
                FragmentWeapons.this.startActivity(intent);
            }
        }
        );
        recyclerView.setAdapter(weaponsAdapter);
    }
}