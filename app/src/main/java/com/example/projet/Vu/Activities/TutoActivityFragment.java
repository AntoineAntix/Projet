package com.example.projet.Vu.Activities;

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
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;
import com.example.projet.R;

public class TutoActivityFragment extends AppCompatActivity {
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;


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


    }
}
