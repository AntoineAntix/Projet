package com.example.projet.Vu.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.example.projet.Vu.Adapter.ViewPageAdapter;
import com.example.projet.Vu.Fragments.FragmentClasses;
import com.example.projet.Vu.Fragments.FragmentEquipments;
import com.example.projet.Vu.Fragments.FragmentWeapons;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;
import com.example.projet.R;

public class EncyclopedieActivityFragment extends AppCompatActivity {
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;


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
}
