package com.example.airsense.view.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.airsense.R;
import com.example.airsense.domain.model.AssetModel.LightAsset;
import com.example.airsense.domain.model.AssetModel.WeatherAsset;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeFragment extends Fragment {
    private double weatherLatitude, weatherLongitude;
    private double lightLatitude, lightLongitude;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng weatherMarker = new LatLng(weatherLatitude, weatherLongitude);
            googleMap.addMarker(new MarkerOptions().position(weatherMarker ).title("Weather Asset"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(weatherMarker));

            LatLng lightMarker = new LatLng(lightLatitude, lightLongitude);
            googleMap.addMarker(new MarkerOptions().position(lightMarker).title("Light Asset"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(lightMarker));

            // Set the bounds
            LatLngBounds bounds = new LatLngBounds(
                    new LatLng(10.86, 106.79),   // southwest
                    new LatLng(10.88, 106.82)    // northeast
            );
            googleMap.setLatLngBoundsForCameraTarget(bounds);

            // Set the default zoom level
            float defaultZoom = 15.0f;
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(defaultZoom));

            // Set min and max zoom levels
            googleMap.setMinZoomPreference(0);
            googleMap.setMaxZoomPreference(19);

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initiate();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private void initiate() {
        WeatherAssetRepository repository = new WeatherAssetRepository(getContext());
        repository.getAssetById("5zI6XqkQVSfdgOrZ1MyWEf").observe(getViewLifecycleOwner(), new Observer<WeatherAsset>() {
            @Override
            public void onChanged(WeatherAsset weatherAsset) {
                if(weatherAsset.attributes.location.value != null) {
                    weatherLongitude = weatherAsset.attributes.location.value.coordinates.get(0);
                    weatherLatitude = weatherAsset.attributes.location.value.coordinates.get(1);
                }
            }
        });

        repository.getLightAssetById("6iWtSbgqMQsVq8RPkJJ9vo").observe(getViewLifecycleOwner(), new Observer<LightAsset>() {
            @Override
            public void onChanged(LightAsset lightAsset) {
                if(lightAsset.attributes.location.value != null) {
                    lightLongitude = lightAsset.attributes.location.value.coordinates.get(0);
                    lightLatitude = lightAsset.attributes.location.value.coordinates.get(1);
                }
            }
        });
    }
}