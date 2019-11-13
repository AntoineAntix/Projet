package com.example.projet.Vu.Fragments;

import android.os.Bundle;

import com.example.projet.Vu.ViewPageAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.projet.R;

public class MainActivityFragment extends AppCompatActivity {
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        tablayout=(TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPageAdapter(getSupportFragmentManager());


        adapter.AddFragment(new FragmentClasses(),"Classes");
        adapter.AddFragment(new FragmentWeapons(),"Armes");
        adapter.AddFragment(new FragmentFav(),"Panoplies");

        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);


    }

}
