package com.example.projet.Vu.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.example.projet.R;
import com.google.android.material.navigation.NavigationView;

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Permet de gérer l'activité principale de l'application.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static MediaPlayer mediaPlayer;
    public static int m=0;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //On définit le layout affilié

        //Définition de la toolbar
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Création du drawer layout
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);

        //Gestion de l'ouverture et de la fermeture du drawer.
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        musiquePlay();
    }

    public void onPause() //Si l'application est fermé la musique est mise sur pause
    {
        super.onPause();
        if(m!=0) {
            mediaPlayer.pause();
            m=2;
        }
    }

    public void onResume() //Quand on revient sur l'application la musique reprend
    {
        super.onResume();
        if (m==2){
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            m=1;
        }
    }

    public void musiquePlay (){ //Initialisation de la musique
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

    public void showEcycolpedie(View view) { //Permet l'ouverture de l'encyclopédie
        Intent intent = new Intent(this, EncyclopedieActivityFragment.class);
        startActivity(intent);
    }

    public void showTuto(View view) { //Permet l'ouverture de tutoriel
        Intent intent = new Intent(this, TutoActivityFragment.class);
        startActivity(intent);
    }

    public void showAPropos(View view) { //Permet l'ouverture du À propos
        Intent intent = new Intent(this, Apropos.class);
        startActivity(intent);
    }

    //Gestion de la navigation drawer lorsque l'on clique sur un élément.
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
    } //Fermeture du drawer

    private void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    } //Ouverture du drawer

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            closeDrawer();
        }
        super.onBackPressed();
    }
}
