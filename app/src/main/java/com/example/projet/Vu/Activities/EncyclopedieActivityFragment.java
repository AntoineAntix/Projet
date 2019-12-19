package com.example.projet.Vu.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.example.projet.Vu.Adapter.ViewPageAdapter;
import com.example.projet.Vu.Fragments.FragmentClasses;
import com.example.projet.Vu.Fragments.FragmentEquipments;
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
 * Permet de gérer l'activité Encyclopédie.
 */

public class EncyclopedieActivityFragment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_encyclopedie_fragment); //On définit le layout affilié

        //On initialise la Tablayout qui gère les fragments
        tablayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPageAdapter(getSupportFragmentManager());

        //On change la couleur du tablayout lorsque le fragment affilié est ouvert
        tablayout.setTabTextColors(getResources().getColor(R.color.RowColor),getResources().getColor(R.color.colorAccent));

        //On ajoute les fragments
        adapter.AddFragment(new FragmentClasses(), "Classes");
        adapter.AddFragment(new FragmentEquipments(), "Equipements");
        adapter.AddFragment(new FragmentWeapons(), "Armes");

        //On définit l'adapter des fragments
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);

        //Ajout d'icone sur les tablayout
        tablayout.getTabAt(0).setIcon(R.drawable.ic_group);
        tablayout.getTabAt(1).setIcon(R.drawable.ic_equipment);
        tablayout.getTabAt(2).setIcon(R.drawable.ic_arme);

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

    //Permet le filtrage du recyclerview
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //On définit la barre de recherche
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        //On gère les entrées utilisateur
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { // On filtre les liste en fonction de l'entrée utilisateur
                FragmentClasses.classesAdapter.getFilter().filter(newText);
                FragmentEquipments.equipmentsAdapter.getFilter().filter(newText);
                FragmentWeapons.weaponsAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;

    }

    //Gestion de la navigation drawer lorsque l'on clique sur un élément.
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        closeDrawer();

        switch (menuItem.getItemId()){
            case R.id.nav_accueil_classe:
                Intent acc = new Intent(EncyclopedieActivityFragment.this,MainActivity.class);
                startActivity(acc);
                break;

            case R.id.nav_enc_classe:
                Intent enc = new Intent(EncyclopedieActivityFragment.this,EncyclopedieActivityFragment.class);
                startActivity(enc);
                break;

            case R.id.nav_tut_classe:
                Intent tut = new Intent(EncyclopedieActivityFragment.this,TutoActivityFragment.class);
                startActivity(tut);
                break;
            case R.id.nav_propos_classe:
                Intent pro = new Intent(EncyclopedieActivityFragment.this,Apropos.class);
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
