package com.example.projet;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;


public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Controller crt;
    private ProgressBar chargement;
    public int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        chargement = findViewById(R.id.chargement_main_activity);

        crt = new Controller(this);
        crt.creation();
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

    adapter = new Adapter(listWeapons);
    recyclerView.setAdapter(adapter);

    }
}
