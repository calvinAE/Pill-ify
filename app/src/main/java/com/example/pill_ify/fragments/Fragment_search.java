package com.example.pill_ify.fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import com.example.pill_ify.API;
import com.example.pill_ify.R;
import com.example.pill_ify.models.Medication;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Fragment_search extends Fragment  {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_search,container,false);

        TextView txtDrugname = view.findViewById(R.id.txt_drugname);
        TextView txtdrugDescription = view.findViewById(R.id.txt_drugdescription);
        TextView txtdrugIngredient = view.findViewById(R.id.txt_ingredient);
        EditText editText = view.findViewById(R.id.txt_search);
        Button btnSearch = view.findViewById(R.id.btn_search);
        FloatingActionButton fab = view.findViewById(R.id.btn_addMedication);

        // naam ophalen


        SQLiteDatabase medicationDB = getContext().openOrCreateDatabase("medication", Context.MODE_ENABLE_WRITE_AHEAD_LOGGING,null);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMedication( medicationDB,txtDrugname,txtdrugDescription,txtdrugIngredient);


            }
        });

       btnSearch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String searchMedication = editText.getText().toString();
            onClickBtn(searchMedication,txtDrugname,txtdrugDescription,txtdrugIngredient);
           }
        });
        return view;
    }

    public void onClickBtn( String searchMedication , TextView txtdrugname, TextView txtdrugdescription, TextView txtingredient)
    {

        API api = new API();
        api.callApi(searchMedication,txtdrugname, txtdrugdescription, txtingredient);

    }

    public void AddMedication(SQLiteDatabase medicationDB, TextView txtdrugname, TextView txtdrugdescription, TextView txtingredient)
    {

        String newName = txtdrugname.getText().toString().trim();
        String newDescription = txtdrugdescription.getText().toString().trim();
        String newIngredient = txtingredient.getText().toString().trim();

        Medication newMedication = new Medication(newName,newDescription,newIngredient);


        //create table if db doesn't exist


                medicationDB.execSQL("create table if not exists Medication(name varchar, description varchar, ingredient varchar)");

        //insert query

                medicationDB.execSQL("insert into Medication(name,description,ingredient) values('" + newName + "','" + newDescription + "','" + newIngredient + "')");

                //succesful
                Toast.makeText(getContext(), "Medication has been added succesfuly", Toast.LENGTH_SHORT).show();
            }
    }



