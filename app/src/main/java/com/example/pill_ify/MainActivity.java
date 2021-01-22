package com.example.pill_ify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pill_ify.database.MedicatieDatabase;
import com.example.pill_ify.fragments.Fragment_Map;
import com.example.pill_ify.fragments.Fragment_MyMedication;
import com.example.pill_ify.fragments.Fragment_home;
import com.example.pill_ify.fragments.Fragment_search;
import com.example.pill_ify.models.Medication;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController mNavController;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.btn_addMedication);
        TextView txtdrugname = findViewById(R.id.txt_drugname);
        TextView txtdrugdescription = findViewById(R.id.txt_drugdescription);


        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        //zet het beginscherm
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new Fragment_home())
                .commit();



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
                        case R.id.ic_map:
                            selectedFragment = new Fragment_Map();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            };



    }