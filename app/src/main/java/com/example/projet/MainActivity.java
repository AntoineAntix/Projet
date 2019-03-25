package com.example.projet;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener
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
        setContentView(R.layout.activity_main);
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
            MainActivity.this.startActivity(intent);
        }
    }
    );
    recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

   @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }
}
