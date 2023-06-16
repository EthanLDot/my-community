package com.example.mycommunity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container,false);
        SupportMapFragment map = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);

        map.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                MarkerOptions marker = new MarkerOptions();


                LatLng p = new LatLng( ((34.0522266)),
                        ((-118.24368) ));
                marker.position(p);
                marker.title("Los Angeles");
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(p, 50));
                googleMap.addMarker(marker);

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        MarkerOptions marker = new MarkerOptions();

                        marker.position(latLng);
                        marker.title(latLng.latitude + "" + latLng.longitude);

                        //googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 50));
                        googleMap.addMarker(marker);
                    }
                });

            }
        });

        return view;
    }
}