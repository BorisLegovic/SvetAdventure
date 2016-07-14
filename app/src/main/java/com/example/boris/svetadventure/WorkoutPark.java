package com.example.boris.svetadventure;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.boris.svetadventure.AdapterModels.MapModel;
import com.example.boris.svetadventure.Adapters.MapsAdapter;
import com.example.boris.svetadventure.Utilitys.GPSLocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.splunk.mint.Mint;

/**
 * Created by boris on 05/01/2016.
 */
public class WorkoutPark extends FragmentActivity implements OnMapReadyCallback {

private GoogleMap mMap;
public double longitude;
public double latitude;
        TextView longitudetx;
        TextView latutudetx;
        ScrollView sv;
        LinearLayout workouts;
public static int GIFT_NUMBER = 0;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workoutpark);
        Mint.initAndStartSession(WorkoutPark.this, "f241ce49");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.mapPark);
        mapFragment.getMapAsync(this);

        sv = (ScrollView) findViewById(R.id.scrollView);
        workouts = (LinearLayout) findViewById(R.id.all_workouts);





        LocationManager locationManager;
        GPSLocationListener loc = new GPSLocationListener();
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, loc);





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
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(new LatLng(45.08815, 13.8784)).title("Fit Park Pinetta"));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return;
        }
        mMap.setMyLocationEnabled(true);


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.08815, 13.8784), 15));


        Polyline line = mMap.addPolyline(new PolylineOptions()
        .add(new LatLng(45.05136, 13.90118), new LatLng(45.05111, 13.9006), new LatLng(45.05086, 13.89955), new LatLng(45.04939, 13.89761),
        new LatLng(45.04919,13.89661),new LatLng(45.04933,13.89482), new LatLng(45.0496,13.89296),new LatLng(45.05313,13.89622), new LatLng(45.05571,13.90021),new LatLng(45.05136, 13.90118))
        .width(8)
        .color(Color.RED));

        }
        public void show_park(View view){
                if(workouts.getVisibility() == View.VISIBLE){
                        workouts.setVisibility(View.GONE);
                        sv.setVisibility(View.VISIBLE);
                }
                else{
                        sv.setVisibility(View.GONE);
                        workouts.setVisibility(View.VISIBLE);
                }

        }

        public void addWorkouts(MapsAdapter adapter){ // add cycle maps to list
                MapModel[] mapa = new MapModel[4];
                mapa[0] = new MapModel("Savicentka","C");
                mapa[1] = new MapModel("Roverija","C");
                mapa[2] = new MapModel("Savicenta Circular Trail","C");
                mapa[3] = new MapModel("Kranjcici - Rezanci","C");
                for(int i=0;i< mapa.length;i++){
                        adapter.add(mapa[i]);
                }
        }
}
