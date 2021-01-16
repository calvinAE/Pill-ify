package com.example.pill_ify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pill_ify.fragments.Fragment_MyMedication;
import com.example.pill_ify.fragments.Fragment_home;
import com.example.pill_ify.fragments.Fragment_search;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);



    }

    public void onClickBtn(View v)
    {
        String name;
        EditText editText = findViewById(R.id.txt_search);
        name = editText.getText().toString();

        TextView textView = findViewById(R.id.txt_drugResult);
        textView.setText(name);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId())
                    {
                        case R.id.ic_search:
                            selectedFragment = new Fragment_search();
                            break;

                        case R.id.ic_mymedication:
                            selectedFragment = new Fragment_MyMedication();
                            break;
                        case R.id.ic_home:
                            selectedFragment = new Fragment_home();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            };



    }