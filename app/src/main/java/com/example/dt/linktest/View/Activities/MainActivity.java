package com.example.dt.linktest.View.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dt.linktest.Model.articles;
import com.example.dt.linktest.R;
import com.example.dt.linktest.View.Adapter.ItemAdapter;
import com.example.dt.linktest.ViewModel.MasterViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    String TAG = MainActivity.class.getSimpleName();
    List<articles> Allcourses = new ArrayList<>();
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler)
    RecyclerView recycler;
    ItemAdapter adapter;
    MasterViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.opendrawer, R.string.closedrawer);
        drawerLayout.addDrawerListener(toggle);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();

        adapter = new ItemAdapter(MainActivity.this);

        viewModel = ViewModelProviders.of(this).get(MasterViewModel.class);
        viewModel.getData().observe(this, new Observer<List<articles>>() {
            @Override
            public void onChanged(List<articles> courses) {
                if (courses.size() > 0) {
                    Allcourses.clear();
                    Allcourses.addAll(courses);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    recycler.setLayoutManager(linearLayoutManager);
                    adapter.submitList(Allcourses);
                    recycler.setAdapter(adapter);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

        });

    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
