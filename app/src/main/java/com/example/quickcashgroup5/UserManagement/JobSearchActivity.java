//Adapted code from https://git.cs.dal.ca/prof3130/google-map-advance-demo

package com.example.quickcashgroup5.UserManagement;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.quickcashgroup5.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JobSearchActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final float DEFAULT_ZOOM = 15f;
    private GoogleMap mMap;

    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    private Boolean mLocationPermissionGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        Log.d(TAG, "onCreate: Starts");
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_jobsearch);
        getLocationPermission();
        Log.d(TAG, "onCreate: Ends");
    }

    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission : starts");
        String[] permissions = {FINE_LOCATION, COURSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                Log.d(TAG, "getLocationPermission : Permissions already granted");
                initMap();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions, LOCATION_PERMISSION_REQUEST_CODE);
                Log.d(TAG, "getLocationPermission : Request for permissions");
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions, LOCATION_PERMISSION_REQUEST_CODE);
            Log.d(TAG, "getLocationPermission : Request for permissions");
        }
        Log.d(TAG, "getLocationPermission :  ends");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: Requesting for permissions");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;
                    Log.d(TAG, "onRequestPermissionsResult: permissions given by user");
                    //initialize our map
                    initMap();
                }
            }

        }
    }

    private void initMap() {
        Log.d(TAG, "initMap: starts");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Log.d(TAG, "initMap: ends");

    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: starts");
        mMap = googleMap;
//        Toast.makeText(this, "Google Map is ready", Toast.LENGTH_SHORT).show();
        if (mLocationPermissionGranted) {
            Log.d(TAG, "onMapReady: getting Device current location!!");
            getPreferredLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

            //Temp hard coding
            addMarker("Halifax Public Gardens, Halifax");
            addMarker("Parkland At The Gardens, Halifax");
            addMarker("1491 South Park St, Halifax");
            addMarker("Park Lane Mall, Halifax");
        }
    }

    public void getPreferredLocation(){
//        Location userLocation = sessionManagement.getLocation();
        //Temp hard coding
        Location userLocation = new Location("Preferred Location");
        userLocation.setLatitude(44.64065436548407);
        userLocation.setLongitude(-63.5783925261504);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(userLocation.getLatitude(), userLocation.getLongitude()), DEFAULT_ZOOM));
    }

//    Add this function to signup and location permission functions
//    public void getDeviceLocation(){
//        Log.d(TAG, "getDeviceLocation: starts");
//        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//        try{
//            if(mLocationPermissionGranted){
//                Task location = mFusedLocationProviderClient.getLastLocation();
//                location.addOnCompleteListener(new OnCompleteListener() {
//                    @Override
//                    public void onComplete(@NonNull Task task) {
//                        if(task.isSuccessful()){
//                            Log.d(TAG, "getDeviceLocation: onComplete: found location");
//                            Location currentLocation = (Location) task.getResult();
//                            if(currentLocation != null) {
//                                Log.d(TAG, "getDeviceLocation: currentLocation Lattitude: " + currentLocation.getLatitude());
//                                Log.d(TAG, "getDeviceLocation: currentLocation Longitude: " + currentLocation.getLongitude());
//                                moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
//                                        DEFAULT_ZOOM,"current location");
//                            }else
//                                Log.d(TAG, "getDeviceLocation: Current location is null");
//                        }else {
//                            Log.d(TAG, "getDeviceLocation: Current location is null");
//                            Toast.makeText(JobSearchActivity.this, "Unable to get curent location", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        }catch (SecurityException se){
//            Log.d(TAG, "getDeviceLocation: SecurityException: =" + se.getMessage());
//        }
//        Log.d(TAG, "getDeviceLocation: ends");
//    }

    public void moveCamera(LatLng latlng, float zoom, String title){
        Log.d(TAG, "moveCamera: starts with latitude: "+ latlng.latitude + " and Longitude: " + latlng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,zoom));
        MarkerOptions options = new MarkerOptions()
                .position(latlng)
                .title(title);
        mMap.addMarker(options);
    }

    private void addMarker(String markerLocation) {
        Log.d(TAG, "GeoLocate: starts");

        Geocoder geocoder = new Geocoder((JobSearchActivity.this));
        List<Address> addressLists = new ArrayList<>();

        try {
            addressLists = geocoder.getFromLocationName(markerLocation, 1);
        }catch (IOException ex){
            Log.d(TAG, "GeoLocate: exception " + ex.getMessage());
        }

        if(addressLists.size() > 0){
            Address address = addressLists.get(0);

            Log.d(TAG, "GeoLocate: Found a location" + address.toString());
            moveCamera(new LatLng(address.getLatitude(),address.getLongitude()),
                    DEFAULT_ZOOM,
                    address.getAddressLine(0));
        }
        Log.d(TAG, "GeoLocate: ends");
    }

    // Search Functionality Ends

    // Check Services are working fine or not

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: Google Services is working fine");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(JobSearchActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            Log.d(TAG, "isServicesOK: Google play services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG, "isServicesOK: An error occurred but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(JobSearchActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You cannot make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}

