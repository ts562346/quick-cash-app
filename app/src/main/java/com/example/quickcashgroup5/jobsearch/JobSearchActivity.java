//Adapted code from https://git.cs.dal.ca/prof3130/google-map-advance-demo

package com.example.quickcashgroup5.jobsearch;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.databasemanagement.Database;
import com.example.quickcashgroup5.feedbackmanagement.Feedback;
import com.example.quickcashgroup5.home.EmployeeHomeActivity;
import com.example.quickcashgroup5.jobcreation.JobPosting;
import com.example.quickcashgroup5.usermanagement.JobPreferenceActivity;
import com.example.quickcashgroup5.usermanagement.LogInActivity;
import com.example.quickcashgroup5.usermanagement.SessionManagement;
import com.example.quickcashgroup5.usermanagement.User;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * JobSearchActivity
 */
public class JobSearchActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    private static final float DEFAULT_ZOOM = 15f;
    private GoogleMap mMap;

    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    private Boolean mLocationPermissionGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private EditText inputSearch;

    private SessionManagement sessionManagement;
    private HashMap<String, JobPosting> allJobs;
    private HashMap<String, JobPosting> filteredJobs;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sidebar;
    Button search;
    Button searchByPreferences;
    private String preferredLocation;
    private String preferredCategory;
    private String preferredPayment;
    private String preferredHours;

    Database database;

    /**
     * Runs when Activity is created
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobsearch);

        sessionManagement = new SessionManagement(this);
        inputSearch = findViewById(R.id.input_search);
        allJobs = new HashMap<>();
        filteredJobs = new HashMap<>();
        database = new Database();

        sidebar = findViewById(R.id.sidebar);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sidebar.setNavigationItemSelectedListener(this);


        setJobLocations();
        setJobPreferences();

        searchByPreferences = findViewById(R.id.searchByPreferences);
        searchByPreferences.setOnClickListener(view -> {
            if (!preferredCategory.equals("")) {
                searchByPreferences();
                FragmentJobSearch f = new FragmentJobSearch(filteredJobs);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.recycleViewContainer, f, "Job search");
                ft.commit();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Your preferences have not been set", Toast.LENGTH_LONG);
                toast.show();
            }
        });


        search = findViewById(R.id.search);
        search.setOnClickListener(view -> {
            search();
            FragmentJobSearch f = new FragmentJobSearch(filteredJobs);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.recycleViewContainer, f, "Job search");
            ft.commit();
        });

        Log.d(TAG, "onCreate: Starts");
        getLocationPermission();
        Log.d(TAG, "onCreate: Ends");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * Sets the Job Preferences
     */
    private void setJobPreferences() {
        database.getFirebaseDatabase().getReference().child("User").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    User u = adSnapshot.getValue(User.class);
                    if (sessionManagement.getEmail().equals(u.getEmail())) {
                        preferredLocation = u.getPreferredLocation();
                        preferredCategory = u.getPreferredCategory();
                        preferredPayment = u.getPreferredPayment();
                        preferredHours = u.getPreferredHours();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: Something went wrong! Error:" + databaseError.getMessage());
            }
        });
    }

    /**
     * Gets the location permission
     */
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

    /**
     * Requests Permissions Results
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Deprecated
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: Requesting for permissions");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermissionGranted = false;
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0) {
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
        Log.d(TAG, "Error save me");

    }

    /**
     * Initializes the map
     */
    private void initMap() {
        Log.d(TAG, "initMap: starts");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Log.d(TAG, "initMap: ends");

    }

    /**
     * Stores the job location in the array
     */
    private void setJobLocations() {
        database.getFirebaseDatabase().getReference().child("JobPosting").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    JobPosting job = adSnapshot.getValue(JobPosting.class);
                    if (job.getStatus().equals("New")) {
                        allJobs.put(adSnapshot.getKey(), job);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Email does not exist", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onCancelled: Something went wrong! Error:" + databaseError.getMessage());
            }
        });
    }

    /**
     * Displays the filtered jobs
     */
    private void display() {
        mMap.clear();
        for (String key : filteredJobs.keySet()) {
            JobPosting job = filteredJobs.get(key);
            addMarker(job.getLocation());
        }
    }

    /**
     * Runs when map is ready
     *
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: starts");
        mMap = googleMap;
        if (Boolean.TRUE.equals(mLocationPermissionGranted)) {
            Log.d(TAG, "onMapReady: getting Device current location!!");
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mMap.getUiSettings().setScrollGesturesEnabled(false);
            mMap.getUiSettings().setZoomGesturesEnabled(false);
            mMap.getUiSettings().setScrollGesturesEnabledDuringRotateOrZoom(false);
        }
    }

    /**
     * Search based on the input
     */
    private void search() {
        String keyword = inputSearch.getText().toString();
        filteredJobs.clear();
        for (String key : allJobs.keySet()) {
            JobPosting job = allJobs.get(key);
            if (job.getTitle().matches("^(?i).*" + keyword + ".*$")) {
                filteredJobs.put(key, job);
            }
        }
        if (filteredJobs.size() == 0) {
            Toast.makeText(getApplicationContext(), "No jobs available right now", Toast.LENGTH_LONG).show();
        }
        display();
    }

    /**
     * Searches based on the preferences
     */
    private void searchByPreferences() {
        filteredJobs.clear();
        for (String key : allJobs.keySet()) {
            JobPosting job = allJobs.get(key);
            boolean isMatchedLocation = getCity(job.getLocation()).equals(getCity(preferredLocation));
            boolean isMatchedCategory = job.getCategory().equals(preferredCategory);
            boolean isMatchedHours = Float.parseFloat(job.getDuration()) >= Float.parseFloat((preferredHours));
            boolean isMatchedPayment = Float.parseFloat(job.getPayment()) >= Float.parseFloat((preferredPayment));
            if (isMatchedPayment && isMatchedHours && isMatchedCategory && isMatchedLocation) {
                filteredJobs.put(key, job);
            }
        }
        if (filteredJobs.size() == 0) {
            Toast.makeText(getApplicationContext(), "No preferred jobs available right now", Toast.LENGTH_LONG).show();
        }
        display();
    }

    /**
     * Gets the device's current location
     */
    public void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: starts");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (Boolean.TRUE.equals(mLocationPermissionGranted)) {
                Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "getDeviceLocation: onComplete: found location");
                        Location currentLocation = (Location) task.getResult();
                        if (currentLocation != null) {
                            Log.d(TAG, "getDeviceLocation: currentLocation Lattitude: " + currentLocation.getLatitude());
                            Log.d(TAG, "getDeviceLocation: currentLocation Longitude: " + currentLocation.getLongitude());
                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM, "current location");
                        } else
                            Log.d(TAG, "getDeviceLocation: Current location is null");
                    } else {
                        Log.d(TAG, "getDeviceLocation: Current location is null");
                        Toast.makeText(JobSearchActivity.this, "Unable to get curent location", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (SecurityException se) {
            Log.d(TAG, "getDeviceLocation: SecurityException: =" + se.getMessage());
        }
        Log.d(TAG, "getDeviceLocation: ends");
    }

    /**
     * Moves the camera of the map
     *
     * @param latlng
     * @param zoom
     * @param title
     */
    public void moveCamera(LatLng latlng, float zoom, String title) {
        Log.d(TAG, title + ", moveCamera: starts with latitude: " + latlng.latitude + " and Longitude: " + latlng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoom));
    }

    /**
     * Adds the marker of the location to the map
     *
     * @param markerLocation
     */
    private void addMarker(String markerLocation) {
        Log.d(TAG, "GeoLocate: starts");

        Geocoder geocoder = new Geocoder((JobSearchActivity.this));
        List<Address> addressLists = new ArrayList<>();

        try {
            addressLists = geocoder.getFromLocationName(markerLocation, 1);
        } catch (IOException ex) {
            Log.d(TAG, "GeoLocate: exception " + ex.getMessage());
        }

        if (!addressLists.isEmpty()) {
            Address address = addressLists.get(0);

            Log.d(TAG, "GeoLocate: Found a location" + address.toString());
            LatLng location = new LatLng(address.getLatitude(), address.getLongitude());
            MarkerOptions options = new MarkerOptions()
                    .position(location)
                    .title(address.getAddressLine(0));
            mMap.addMarker(options);
            moveCamera(location, DEFAULT_ZOOM, null);
        }
        Log.d(TAG, "GeoLocate: ends");
    }

    /**
     * Gets the city from the address
     *
     * @param location
     * @return
     */
    private String getCity(String location) {
        String address = null;
        Log.d(TAG, "GeoLocate: starts");
        Geocoder geocoder = new Geocoder((JobSearchActivity.this));
        List<Address> addressLists = new ArrayList<>();
        try {
            addressLists = geocoder.getFromLocationName(location, 1);
        } catch (IOException ex) {
            Log.d(TAG, "GeoLocate: exception " + ex.getMessage());
        }
        if (!addressLists.isEmpty()) {
            address = addressLists.get(0).getLocality();
        }
        Log.d(TAG, "GeoLocate: ends");

        return address;
    }

    /**
     * Checks if the services are ok
     *
     * @return
     */
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

    /**
     * To open and close the navigation drawer when the icon is clicked
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //https://stackoverflow.com/questions/42297381/onclick-event-in-navigation-drawer

    /**
     * Navigation Bar
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                Intent intent = new Intent(this, EmployeeHomeActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_searchJob: {
                Intent intent = new Intent(this, JobSearchActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_preferences: {
                Intent intent = new Intent(this, JobPreferenceActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_feedback: {
                Intent intent = new Intent(this, Feedback.class);
                startActivity(intent);
                ((Activity) this).finish();
                break;
            }
            case R.id.nav_logout: {
                sessionManagement.clearSession();
                Intent intent = new Intent(this, LogInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
                break;
            }
            default: {
                Log.d(TAG, "Sidebar error");
            }
        }

        return true;
    }
}