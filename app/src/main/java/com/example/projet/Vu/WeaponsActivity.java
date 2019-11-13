package com.example.projet.Vu;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;
import android.view.MenuInflater;


import com.example.projet.Controller.WeaponsController;
import com.example.projet.Modele.WeaponsOnItemClick;
import com.example.projet.Modele.Weapons;
import com.example.projet.R;

import java.util.List;


public class WeaponsActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private WeaponsAdapter weaponsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private WeaponsController crt;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapons);
        recyclerView = findViewById(R.id.myRecyclerView);
        progressBar = findViewById(R.id.chargement_main_activity);
        crt = new WeaponsController(this, getSharedPreferences("data", Context.MODE_PRIVATE));
        crt.onCreate();
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
    layoutManager=new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    weaponsAdapter = new WeaponsAdapter(listWeapons, getApplicationContext(), new WeaponsOnItemClick() {
        @Override
        public void onItemClick(Weapons arme) {
            Toast.makeText(getApplicationContext(), arme.getName(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), WeaponsDetailsActivity.class);
            intent.putExtra("nom", arme.getName());
            intent.putExtra("description", arme.getDescription());
            intent.putExtra("lvl", arme.getLvl());
            intent.putExtra("type", arme.getType());
            intent.putExtra("image", arme.getImgUrl());
            WeaponsActivity.this.startActivity(intent);
        }
    }
    );
    recyclerView.setAdapter(weaponsAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                weaponsAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

}
