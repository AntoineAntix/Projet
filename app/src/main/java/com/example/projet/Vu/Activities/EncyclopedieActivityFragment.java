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

public class EncyclopedieActivityFragment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_encyclopedie_fragment);

        tablayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPageAdapter(getSupportFragmentManager());

        tablayout.setTabTextColors(getResources().getColor(R.color.RowColor),getResources().getColor(R.color.colorAccent));

        adapter.AddFragment(new FragmentClasses(), "Classes");
        adapter.AddFragment(new FragmentEquipments(), "Equipements");
        adapter.AddFragment(new FragmentWeapons(), "Armes");

        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);

        tablayout.getTabAt(0).setIcon(R.drawable.ic_group);
        tablayout.getTabAt(1).setIcon(R.drawable.ic_equipment);
        tablayout.getTabAt(2).setIcon(R.drawable.ic_arme);

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
                FragmentClasses.classesAdapter.getFilter().filter(newText);
                FragmentEquipments.equipmentsAdapter.getFilter().filter(newText);
                FragmentWeapons.weaponsAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;

    }

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
