package com.example.projet.Vu.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.example.projet.Vu.Adapter.ViewPageAdapter;
import com.example.projet.Vu.Fragments.FragmentClasses;
import com.example.projet.Vu.Fragments.FragmentEquipments;
import com.example.projet.Vu.Fragments.FragmentTutoKama;
import com.example.projet.Vu.Fragments.FragmentTutoXP;
import com.example.projet.Vu.Fragments.FragmentWeapons;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import com.example.projet.R;

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Permet de gérer l'activité Tutoriel.
 */

public class TutoActivityFragment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tuto_fragment); //On définit le layout affilié

        //On initialise la Tablayout qui gère les fragments
        tablayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPageAdapter(getSupportFragmentManager());

        //On change la couleur du tablayout lorsque le fragment affilié est ouvert
        tablayout.setTabTextColors(getResources().getColor(R.color.RowColor),getResources().getColor(R.color.colorAccent));

        //On ajoute les fragments
        adapter.AddFragment(new FragmentTutoXP(), "XP");
        adapter.AddFragment(new FragmentTutoKama(), "Kamas");

        //On définit l'adapter des fragments
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);

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

        if(MainActivity.m==1){ //Mise en pause de la musique
            MainActivity.mediaPlayer.pause();
            MainActivity.m=3;
        }


    }

    //Gestion de la navigation drawer lorsque l'on clique sur un élément.
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        closeDrawer();

        switch (menuItem.getItemId()){
            case R.id.nav_accueil_classe:
                Intent acc = new Intent(TutoActivityFragment.this,MainActivity.class);
                startActivity(acc);
                break;

            case R.id.nav_enc_classe:
                Intent enc = new Intent(TutoActivityFragment.this,EncyclopedieActivityFragment.class);
                startActivity(enc);
                break;

            case R.id.nav_tut_classe:
                Intent tut = new Intent(TutoActivityFragment.this,TutoActivityFragment.class);
                startActivity(tut);
                break;

            case R.id.nav_propos_classe:
                Intent pro = new Intent(TutoActivityFragment.this,Apropos.class);
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
