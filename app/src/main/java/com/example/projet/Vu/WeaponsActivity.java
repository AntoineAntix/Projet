package com.example.projet.Vu;


import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.support.v7.widget.SearchView;
import android.widget.Toast;
import android.view.MenuInflater;


import com.example.projet.Controlleur.Controller;
import com.example.projet.Modele.OnItemClick;
import com.example.projet.Modele.Weapons;
import com.example.projet.R;

import java.util.List;


public class WeaponsActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Controller crt;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    private static int m=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapons);
        recyclerView = findViewById(R.id.myRecyclerView);
        progressBar = findViewById(R.id.chargement_main_activity);
        if(m==0)
        {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.dofusmusic);
            mediaPlayer.start();
            m=1;
        }
        crt = new Controller(this, getSharedPreferences("data", Context.MODE_PRIVATE));
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
    adapter = new Adapter(listWeapons, getApplicationContext(), new OnItemClick() {
        @Override
        public void onItemClick(Weapons arme) {
            Toast.makeText(getApplicationContext(), arme.getName(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
            intent.putExtra("nom", arme.getName());
            intent.putExtra("description", arme.getDescription());
            intent.putExtra("lvl", arme.getLvl());
            intent.putExtra("type", arme.getType());
            intent.putExtra("image", arme.getImgUrl());
            WeaponsActivity.this.startActivity(intent);
        }
    }
    );
    recyclerView.setAdapter(adapter);
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
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

}
