package br.com.minhaagenda;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import model.AppDatabase;



public class MainActivity extends AppCompatActivity {

    static AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
         db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        NavController navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation_view);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}