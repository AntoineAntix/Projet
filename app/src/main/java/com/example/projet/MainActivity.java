package com.example.projet;

import android.media.MediaPlayer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Controller crt;
    private ProgressBar chargement;
    private int m=0;

    public int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        chargement = findViewById(R.id.chargement_main_activity);
        if(m==0)
        {
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.dofusmusic);
            mediaPlayer.start();
            m=1;
        }
        crt = new Controller(this);
        crt.onCreate();
    }



    public void showLoader(){
        chargement.setVisibility(View.VISIBLE);
    }

    public void hideLoader(){
        chargement.setVisibility(View.GONE);
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
}
