package com.example.ezyeatsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashBoard extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;

    // Helper method to load fragments
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        bottomNavigationView = findViewById(R.id.bottomNav);
        drawerLayout = findViewById(R.id.drawer_layout);

        // Set the default fragment to HomeFragment
        loadFragment(new HomeFragment());

        // Bottom Navigation Listener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String itemTitle = item.getTitle().toString();

                switch (itemTitle) {
                    case "HOME":
                        loadFragment(new HomeFragment());
                        return true;
                    case "MENU":
                        loadFragment(new MenuFragment());
                        return true;
                    case "CART":
                        loadFragment(new CartFragment());
                        return true;
                    case "WALLET":
                        loadFragment(new WalletFragment());
                        return true;
                    default:
                        return false;
                }
            }
        });

        // Redirect to LoginActivity if user is not logged in
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
