package com.example.pill_ify.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pill_ify.MainActivity;
import com.example.pill_ify.MedicationAdapter;
import com.example.pill_ify.R;
import com.example.pill_ify.database.MedicatieDatabase;
import com.example.pill_ify.models.Medication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Fragment_MyMedication extends Fragment {


    private MedicationAdapter medicationAdapter;
    private RecyclerView rvMedication;
    private  MedicatieDatabase mDatabase;
    private List<Medication> medList = new ArrayList<>();
    private FragmentActivity context;
    ArrayList<String> name,description,ingredient;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        context = (FragmentActivity) context;
        mDatabase = new MedicatieDatabase(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mymedication,container,false);

                rvMedication = view.findViewById(R.id.rv_myMedication);
                Button btnDelete = view.findViewById(R.id.btn_clear);


                mDatabase = new MedicatieDatabase(getActivity());
                name = new ArrayList<>();
                description = new ArrayList<>();
                ingredient = new ArrayList<>();


    try {
        storeMedication();
        medicationAdapter = new MedicationAdapter(getActivity(), name, description, ingredient);
        rvMedication.setAdapter(medicationAdapter);
        rvMedication.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
    }
    catch (Exception e)
    {
        Toast.makeText(getContext(),"Add medication to your list first",Toast.LENGTH_SHORT).show();
    }


                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                        clearDB();
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getContext(),"Medication list is already empty",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        return view;
    }
        // database leegmaken
        void clearDB() {
            SQLiteDatabase medicationDB = getContext().openOrCreateDatabase("medication", Context.MODE_PRIVATE,null);

            medicationDB.execSQL("DROP TABLE Medication");
            Toast.makeText(getContext(),"Medication has been deleted",Toast.LENGTH_SHORT).show();
        }


        //database data weergeven in recyclerview
        void storeMedication(){
        Cursor cursor = mDatabase.readData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(context, "There is no saved medication found", Toast.LENGTH_SHORT).show();
        } else
        {
            while (cursor.moveToNext())
            {
                name.add(cursor.getString(0));
                description.add(cursor.getString(1));
                ingredient.add(cursor.getString(2));
            }
        }




    }


}
