package com.example.pill_ify.models;

import androidx.lifecycle.ViewModel;

public class MedicationViewModel extends ViewModel {

    private Medication medication;

    private void newMedication(String name, String description)
    {
        medication = new Medication(name,description);

    }
}
