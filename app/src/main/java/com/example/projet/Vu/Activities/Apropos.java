package com.example.projet.Vu.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.projet.R;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Permet de gérer l'activité À propos.
 */

public class Apropos extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.propos_activity); //On définit le layout affilié

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

        if(MainActivity.m==3){ //Gestion de la musique si on passe de l'acitivté tutoriel à celui ci.
            MainActivity.mediaPlayer.setLooping(true);
            MainActivity.mediaPlayer.start();
            MainActivity.m=1;
        }
    }
    public void onPause() //Si l'application est fermé la musique est mise sur pause
    {
        super.onPause();
        if(MainActivity.m!=0) {
            MainActivity.mediaPlayer.pause();
            MainActivity.m=2;
        }
    }

    public void onResume() //Quand on revient sur l'application la musique reprend
    {
        super.onResume();
        if (MainActivity.m==2){
            MainActivity.mediaPlayer.setLooping(true);
            MainActivity.mediaPlayer.start();
            MainActivity.m=1;
        }
    }

    //Gestion de la navigation drawer lorsque l'on clique sur un élément.
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        closeDrawer();
        switch (menuItem.getItemId()){
            case R.id.nav_accueil_classe:
                Intent acc = new Intent(Apropos.this,MainActivity.class);
                startActivity(acc);
                break;

            case R.id.nav_enc_classe:
                Intent enc = new Intent(Apropos.this,EncyclopedieActivityFragment.class);
                startActivity(enc);
                break;

            case R.id.nav_tut_classe:
                Intent tut = new Intent(Apropos.this,TutoActivityFragment.class);
                startActivity(tut);
                break;

            case R.id.nav_propos_classe:
                Intent pro = new Intent(Apropos.this,Apropos.class);
                startActivity(pro);
                break;
        }
        return true;
    }

    private void closeDrawer() { //Fermeture du drawer
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void openDrawer() { //Ouverture du drawer
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
