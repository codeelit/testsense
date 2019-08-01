package com.codeelit.ts;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.codeelit.ts.Fragments.CompaniesFragment;
import com.codeelit.ts.Fragments.DiscussionFragment;
import com.codeelit.ts.Fragments.HomeFragment;
import com.codeelit.ts.Fragments.LearnFragment;
import com.codeelit.ts.Fragments.PracticeFragment;
import com.codeelit.ts.Fragments.PracticeHome;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button btnLogout;
    private FirebaseAuth firebaseAuth;
    TextView nav_username, nav_emial;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        LearnFragment fragment1 = new LearnFragment();
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.fragment_container, fragment1, "");
        ft1.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment = null;
                        switch (item.getItemId()) {
                            case R.id.learn:
                                LearnFragment fragment1 = new LearnFragment();
                                FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                                ft1.replace(R.id.fragment_container, fragment1, "");
                                ft1.commit();
                                return true;
                            case R.id.practice:
                                PracticeFragment fragment2= new PracticeFragment();
                                FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                                ft2.replace(R.id.fragment_container, fragment2, "");
                                ft2.commit();
                                return true;
                            case R.id.companies:
                                CompaniesFragment fragment3 = new CompaniesFragment();
                                FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                                ft3.replace(R.id.fragment_container, fragment3, "");
                                ft3.commit();
                                return true;
                            case R.id.discussion:
                                DiscussionFragment fragment4 = new DiscussionFragment();
                                FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                                ft4.replace(R.id.fragment_container, fragment4, "");
                                ft4.commit();
                                return true;
                        }
                        return true;
                    }
                });
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            FirebaseAuth.getInstance().signOut();
            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivity);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            getSupportActionBar().setTitle("Home");
        } else if (id == R.id.profile) {
            getSupportActionBar().setTitle("Profile");
        } else if (id == R.id.settings) {
            getSupportActionBar().setTitle("Settings");
        } else if (id == R.id.signout) {
            FirebaseAuth.getInstance().signOut();
            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivity);
            finish();
        } else if (id == R.id.learn) {
            getSupportActionBar().setTitle("Learn");
        } else if (id == R.id.practice) {
            getSupportActionBar().setTitle("practice");
        } else if (id == R.id.companies) {
            getSupportActionBar().setTitle("companies");
        } else if (id == R.id.discussion) {
            getSupportActionBar().setTitle("discussion forum");
        }
        return true;
    }
}
