package com.example.projet.Vu.Activities;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.projet.R;
import com.example.projet.Vu.Activities.EncyclopedieActivityFragment;
import com.example.projet.Vu.Fragments.FragmentClasses;
import com.example.projet.Vu.Fragments.FragmentEquipments;
import com.example.projet.Vu.Fragments.FragmentWeapons;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static MediaPlayer mediaPlayer;
    public static int m=0;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        musiquePlay();
    }

    public void onPause()
    {
        super.onPause();
        if(m!=0) {
            mediaPlayer.pause();
            m=2;
        }
    }

    public void onResume()
    {
        super.onResume();
        if (m==2){
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            m=1;
        }
    }

    public void musiquePlay (){
        if(m==0)
        {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.dofusmusic);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            m=1;
        }
        if(m==3){
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            m=1;
        }
    }

    public void showEcycolpedie(View view) {
        Intent intent = new Intent(this, EncyclopedieActivityFragment.class);
        startActivity(intent);
    }

    public void showTuto(View view) {
        Intent intent = new Intent(this, TutoActivityFragment.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        closeDrawer();

        switch (menuItem.getItemId()){
            case R.id.nav_accueil_classe:
                Intent acc = new Intent(MainActivity.this,MainActivity.class);
                startActivity(acc);
                break;

            case R.id.nav_enc_classe:
                Intent enc = new Intent(MainActivity.this,EncyclopedieActivityFragment.class);
                startActivity(enc);
                break;

            case R.id.nav_tut_classe:
                Intent tut = new Intent(MainActivity.this,TutoActivityFragment.class);
                startActivity(tut);
                break;

            case R.id.nav_propos_classe:
                Intent pro = new Intent(MainActivity.this,Apropos.class);
                startActivity(pro);
                break;

        }

        return true;
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            closeDrawer();
        }
        super.onBackPressed();
    }
}
