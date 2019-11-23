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

public class TutoActivityFragment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tuto_fragment);

        tablayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPageAdapter(getSupportFragmentManager());

        tablayout.setTabTextColors(getResources().getColor(R.color.RowColor),getResources().getColor(R.color.colorAccent));

        adapter.AddFragment(new FragmentTutoXP(), "XP");
        adapter.AddFragment(new FragmentTutoKama(), "Kamas");

        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);


        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


    }

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
