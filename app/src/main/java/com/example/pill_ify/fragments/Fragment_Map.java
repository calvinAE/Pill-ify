package com.example.pill_ify.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.pill_ify.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment_Map extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap gMap;
    private FragmentActivity Context;
    private   FusedLocationProviderClient fusedLocationClient;

    @Override
    public void onAttach(@NonNull android.content.Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map,container,false);


        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onPause() {

        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
        mapView.onDestroy();
    }



    private void addPharmacies()
    {
        LatLng LloydpharmaCoord = new LatLng(50.84143533094195, 4.2866091382938265);
        LatLng selfpharmaCoord = new LatLng(50.84240480980681, 4.333700402134254);
        LatLng brouckereCoord = new LatLng(50.85212460725759, 4.354280727112847);

        Marker LloydBrussel;
        Marker selfpharmaBrussel;
        Marker brouckereBrussel;

        LloydBrussel = gMap.addMarker(new MarkerOptions()
        .position(LloydpharmaCoord)
        .title("Lloydpharma Brussel")
        );

        selfpharmaBrussel = gMap.addMarker(new MarkerOptions()
        .position(selfpharmaCoord)
        .title("Selfpharma")


        ); brouckereBrussel = gMap.addMarker(new MarkerOptions()
        .position(brouckereCoord)
        .title("Pharmacie de Brouckère")
        );





    }



    private void belgiumMap()
    {

        LatLng Brussel = new LatLng(50.8503, 4.3517);
        CameraUpdate centerBelgium = CameraUpdateFactory.newLatLngZoom(Brussel,7);
        gMap.animateCamera(centerBelgium);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gMap = googleMap;
        belgiumMap();
        addPharmacies();




    }
}
