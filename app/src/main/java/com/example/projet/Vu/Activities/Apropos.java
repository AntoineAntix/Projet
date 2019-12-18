package com.example.projet.Vu.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.projet.R;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Apropos extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.propos_activity);
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        if(MainActivity.m==3){
            MainActivity.mediaPlayer.setLooping(true);
            MainActivity.mediaPlayer.start();
            MainActivity.m=1;
        }
    }
    public void onPause()
    {
        super.onPause();
        if(MainActivity.m!=0) {
            MainActivity.mediaPlayer.pause();
            MainActivity.m=2;
        }
    }

    public void onResume()
    {
        super.onResume();
        if (MainActivity.m==2){
            MainActivity.mediaPlayer.setLooping(true);
            MainActivity.mediaPlayer.start();
            MainActivity.m=1;
        }
    }

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
