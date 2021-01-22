package com.example.pill_ify;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pill_ify.database.MedicatieDatabase;
import com.example.pill_ify.models.Medication;

import java.util.ArrayList;
import java.util.List;

public  class MedicationAdapter  extends RecyclerView.Adapter<MedicationAdapter.MedicationHolder> {

    Context context;
    ArrayList name,description,ingredient;
    private FragmentActivity activity;
    private MedicatieDatabase database;


    public MedicationAdapter(Context context,ArrayList name, ArrayList description, ArrayList ingredient)
    {
            this.context = context;
            this.name = name;
            this.description = description;
            this.ingredient = ingredient;


    }


    class MedicationHolder extends RecyclerView.ViewHolder
    {
        TextView drugName, drugDescription, drugIngredient;

        public MedicationHolder(@NonNull View itemView)
        {
            super(itemView);
            drugName = itemView.findViewById(R.id.tv_drugname);
            drugDescription = itemView.findViewById(R.id.tv_drugdescription);
            drugIngredient = itemView.findViewById(R.id.tv_drugIngredient);

        }
    }






    @NonNull
    @Override
    public MedicationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.medication_row,parent,false);
        return new MedicationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationAdapter.MedicationHolder holder, int position) {



                holder.drugName.setText(String.valueOf(name.get(position)));
                holder.drugDescription.setText(String.valueOf(description.get(position)));
                holder.drugIngredient.setText(String.valueOf(ingredient.get(position)));



    }



    @Override
    public int getItemCount() {
        return name.size();
    }





}
