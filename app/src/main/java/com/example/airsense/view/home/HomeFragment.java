package com.example.airsense.view.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.airsense.R;
import com.example.airsense.domain.model.AssetModel.WeatherAsset;
import com.example.airsense.view.dashboard.DashBoardDetailViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeFragment extends Fragment {
    // private DashBoardDetailViewModel viewModel;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng defaultCenter = new LatLng(10.87, 106.80324);
            googleMap.addMarker(new MarkerOptions().position(defaultCenter ).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(defaultCenter ));

            // Set the bounds
            LatLngBounds bounds = new LatLngBounds(
                    new LatLng(10.86, 106.79),   // southwest
                    new LatLng(10.88, 106.82)    // northeast
            );
            googleMap.setLatLngBoundsForCameraTarget(bounds);

   //         viewModel.getData().observe(getViewLifecycleOwner(), new Observer<WeatherAsset>() {
  //              @Override
 //               public void onChanged(WeatherAsset weatherAsset) {
//                    if(weatherAsset != null && weatherAsset.attributes.location.value != null) {
//                        double latitude = weatherAsset.attributes.location.value.coordinates.get(1);
//                        double longitude = weatherAsset.attributes.location.value.coordinates.get(0);
//
//                        LatLng locationLatLng = new LatLng(latitude, longitude);
//                        googleMap.addMarker(new MarkerOptions().position(locationLatLng).title("Weather Location"));
//                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(locationLatLng));
//                    }
  //              }
  //          });


            // Set the default zoom level
            float defaultZoom = 16.0f;
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
      //  WeatherAssetRepository weatherAssetRepository = new WeatherAssetRepository(getContext());
      //  viewModel = new DashBoardDetailViewModel("5zI6XqkQVSfdgOrZ1MyWEf", weatherAssetRepository);

    }
}