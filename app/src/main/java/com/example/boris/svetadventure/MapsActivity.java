package com.example.boris.svetadventure;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boris.svetadventure.Adapters.MapsAdapter;
import com.example.boris.svetadventure.Maps.CycleMaps;
import com.example.boris.svetadventure.Maps.WalkingMaps;
import com.example.boris.svetadventure.Utilitys.AndroidPermissions;
import com.example.boris.svetadventure.Utilitys.GPSLocationListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.splunk.mint.Mint;

import java.io.File;
import java.util.Date;
import java.util.logging.Handler;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    GPSLocationListener loc;
    TextView gpstext;
    boolean isGpsOn = false;
    int check = 0;
    private GoogleMap mMap;
    public double longitude;
    public double latitude;
    TextView longitudetx;
    public TextView myStackGiftNumber;
    RelativeLayout stack;
    LocationManager locationManager;
    SharedPreferences sharedPreferences;
    Handler locationGiftHandler;
    RelativeLayout startGps;
    SharedPreferences.Editor editor;
    boolean gpsCheck = false;
    CheckBox box;
    RelativeLayout maps_button;
    SharedPreferences sharedPref;
    int context;
    CircleOptions mojaKuca;
    CircleOptions pula;
    Bitmap imgUser;
    int giftPula = 0;
    int giftMojaKuca = 0;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    int GiftNumber = 0;
    // LogCat tag
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private Location mLastLocation;
    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;
    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;
    private LocationRequest mLocationRequest;
    TextView longT, latT;
    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    Intent intent;
    String imgPath;
    private String selectedImagePath = "";
    final private int PICK_IMAGE = 1;
    final private int CAPTURE_IMAGE = 2;
    final int PERMISSIONS_REQUEST_CAMERA = 1;
    int finalGift;
    int giftRezanci = 0;
    int giftStokovci = 0;
    int giftPusti = 0;
    int giftSmoljanci = 0;
    int giftSavicenta = 0;
    int giftKrancici = 0;
    int giftBibici = 0;
    int giftFoli = 0;
    int giftJursici = 0;
    CircleOptions rezanci;
    CircleOptions stokovci;
    CircleOptions pusti;
    CircleOptions smoljanci;
    CircleOptions savicenta;
    CircleOptions krancici;
    CircleOptions bibici;
    CircleOptions foli;
    CircleOptions jursici;
    CycleMaps cycleMap;
    WalkingMaps walkingMaps;
    SupportMapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Mint.initAndStartSession(MapsActivity.this, "f241ce49");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
         mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
       // PERMISSION FOR ANDRODI 6>
           AndroidPermissions ap = new AndroidPermissions(MapsActivity.this);
           ap.checkPermisions();
        // PERMISION *************************************
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Activity activity = (Activity) this; // I use this for getting the context
        if (checkPlayServices()) {
            buildGoogleApiClient();
        }
        startGps = (RelativeLayout) findViewById(R.id.start_gift);
        stack = (RelativeLayout) findViewById(R.id.relativeLayoutStackMap);
        myStackGiftNumber = (TextView) findViewById(R.id.mystackmapsnumber);
        maps_button = (RelativeLayout) findViewById(R.id.maps_button_id);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPref.edit();

        pula = new CircleOptions().center(new LatLng(44.86631, 13.86479))
                .radius(68);
        mojaKuca = new CircleOptions().center(new LatLng(44.8663, 13.86433)).radius(75);

        rezanci = new CircleOptions()
                .center(new LatLng(45.04699, 13.90561))
                .radius(75);

         stokovci = new CircleOptions()
                .center(new LatLng(45.04954, 13.89294))
                .radius(75);

         pusti = new CircleOptions()
                .center(new LatLng(45.07702, 13.89495))
                .radius(75);

         smoljanci = new CircleOptions()
                .center(new LatLng(45.09188, 13.8432))
                .radius(75);

         savicenta = new CircleOptions()
                .center(new LatLng(45.08565, 13.88096))
                .radius(75);

         krancici = new CircleOptions()
                .center(new LatLng(45.06884, 13.85956))
                .radius(75);

         bibici = new CircleOptions()
                .center(new LatLng(45.06433, 13.88289))
                .radius(75);

         foli = new CircleOptions()
                .center(new LatLng(45.09823, 13.91845))
                .radius(75);

         jursici = new CircleOptions()
                .center(new LatLng(45.02088, 13.87897))
                .radius(75);

        giftJursici = sharedPref.getInt("giftJursici", 0);
        giftFoli =  sharedPref.getInt("giftFoli", 0);
        giftBibici = sharedPref.getInt("giftBibici", 0);
        giftKrancici =  sharedPref.getInt("giftKrancici", 0);
        giftSavicenta = sharedPref.getInt("giftSavicenta", 0);
        giftSmoljanci =  sharedPref.getInt("giftSmoljanci", 0);
        giftPusti = sharedPref.getInt("giftPusti", 0);
        giftStokovci =  sharedPref.getInt("giftStokovci",0);
        giftRezanci =  sharedPref.getInt("giftRezanci",0);
        finalGift = giftJursici + giftFoli + giftBibici+ giftKrancici + giftSavicenta+
                giftSmoljanci + giftPusti+giftStokovci+giftRezanci;
        myStackGiftNumber.setText(String.valueOf(finalGift));
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mapFragment.getMapAsync(this);
        stack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, HowToPlay.class);
                startActivity(intent);

            }
        });
    }

    public Uri setImageUri() {
        // Store image in dcim
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/", "image" + new Date().getTime() + ".png");
        Uri imgUri = Uri.fromFile(file);
        this.imgPath = file.getAbsolutePath();
        return imgUri;
    }

    public String getImagePath() {
        return imgPath;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_CANCELED) {
            if (requestCode == PICK_IMAGE) {
                selectedImagePath = getAbsolutePath(data.getData());
                // imgUser.setImageBitmap(decodeFile(selectedImagePath));
            } else if (requestCode == CAPTURE_IMAGE) {
                selectedImagePath = getImagePath();
                Toast.makeText(getApplicationContext(), R.string.image_taken, Toast.LENGTH_SHORT).show();
                displayLocation();
                // imgUser.setImageBitmap(decodeFile(selectedImagePath));
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }

    public Bitmap decodeFile(String path) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, o);
            // The new size we want to scale to
            final int REQUIRED_SIZE = 70;
            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;
            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeFile(path, o2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAbsolutePath(Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    private boolean checkLocation() {
        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);
        double latitude = mLastLocation.getLatitude();
        double longitude = mLastLocation.getLongitude();
        if(latitude <=0 && longitude <=0) {
            Toast.makeText(MapsActivity.this, "SvetAdventure in not able to track Your Location,\n please try again!", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
           return true;
        }
    }
    private void displayLocation() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            AlertDialog alertDialog = new AlertDialog.Builder(
                    MapsActivity.this).create();
            // Setting Dialog Title
            alertDialog.setTitle("Warning");
            // Setting Dialog Message
            alertDialog.setMessage("Please enable Location services for SvetAdventure in order to use all features of the app!");
            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to execute after dialog closed
                   // Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                }
            });
            alertDialog.show();
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();
            float[] distance = new float[2];

            Location.distanceBetween(mLastLocation.getLatitude(), mLastLocation.getLongitude(),
                    rezanci.getCenter().latitude, rezanci.getCenter().longitude, distance);

            Location.distanceBetween(mLastLocation.getLatitude(), mLastLocation.getLongitude(),
                    stokovci.getCenter().latitude, stokovci.getCenter().longitude, distance);

            Location.distanceBetween(mLastLocation.getLatitude(), mLastLocation.getLongitude(),
                    pusti.getCenter().latitude, pusti.getCenter().longitude, distance);

            Location.distanceBetween(mLastLocation.getLatitude(), mLastLocation.getLongitude(),
                    smoljanci.getCenter().latitude, smoljanci.getCenter().longitude, distance);

            Location.distanceBetween(mLastLocation.getLatitude(), mLastLocation.getLongitude(),
                    savicenta.getCenter().latitude, savicenta.getCenter().longitude, distance);

            Location.distanceBetween(mLastLocation.getLatitude(), mLastLocation.getLongitude(),
                    krancici.getCenter().latitude, krancici.getCenter().longitude, distance);

            Location.distanceBetween(mLastLocation.getLatitude(), mLastLocation.getLongitude(),
                    bibici.getCenter().latitude, bibici.getCenter().longitude, distance);

            Location.distanceBetween(mLastLocation.getLatitude(), mLastLocation.getLongitude(),
                    foli.getCenter().latitude, foli.getCenter().longitude, distance);

            Location.distanceBetween(mLastLocation.getLatitude(), mLastLocation.getLongitude(),
                    jursici.getCenter().latitude, jursici.getCenter().longitude, distance);


            if( distance[0] > rezanci.getRadius()){

            } else {
             giftRezanci= 1;
             editor.putInt("giftRezanci",1).commit();
            }
            if( distance[0] > stokovci.getRadius()){
            } else {
                giftStokovci = 1;
                editor.putInt("giftStokovci",1).commit();
            }
            if( distance[0] > pusti.getRadius()){
            } else {
                giftPusti = 1;
                editor.putInt("giftPusti",1).commit();
            }
            if( distance[0] > smoljanci.getRadius()){
            } else {
                giftSmoljanci = 1;
                editor.putInt("giftSmoljanci",1).commit();
            }
            if( distance[0] > savicenta.getRadius()){
            } else {
                giftSavicenta = 1;
                editor.putInt("giftSavicenta",1).commit();
            }
            if( distance[0] > krancici.getRadius()){
            } else {
                giftKrancici = 1;
                editor.putInt("giftKrancici",1).commit();
            }
            if( distance[0] > bibici.getRadius()){
            } else {
                giftBibici = 1;
                editor.putInt("giftBibici",1).commit();
            }
            if( distance[0] > foli.getRadius()){
            } else {
                giftFoli = 1;
                editor.putInt("giftFoli",1).commit();
            }
            if( distance[0] > jursici.getRadius()){
            } else {
                giftJursici = 1;
                editor.putInt("giftJursici",1).commit();
            }

        }

        giftJursici = sharedPref.getInt("giftJursici",0);
        giftFoli =  sharedPref.getInt("giftFoli", 0);
        giftBibici = sharedPref.getInt("giftBibici",0);
        giftKrancici =  sharedPref.getInt("giftKrancici",0);
        giftSavicenta = sharedPref.getInt("giftSavicenta",0);
        giftSmoljanci =  sharedPref.getInt("giftSmoljanci",0);
        giftPusti = sharedPref.getInt("giftPusti",0);
        giftStokovci =  sharedPref.getInt("giftStokovci",0);
        giftRezanci =  sharedPref.getInt("giftRezanci",0);
        finalGift = giftJursici + giftFoli + giftBibici+ giftKrancici + giftSavicenta+
                giftSmoljanci + giftPusti+giftStokovci+giftRezanci;
        editor.putInt("finalGift",finalGift);
        myStackGiftNumber.setText(String.valueOf(finalGift));
    }

    /**
     * Creating google api client object
     * */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    /**
     * Method to verify google play services on the device
     * */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }



    /**
     * Google api callback methods
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
    }

    @Override
    public void onConnected(Bundle arg0) {
        // Once connected with google api, get the location
        //displayLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }

    public void takePhoto(View view) {
        boolean isLocation = checkLocation();

        if(isLocation == true) {
            final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, setImageUri());
            startActivityForResult(intent, CAPTURE_IMAGE);
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        editor.putBoolean("test", true);
        maps_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(mMap.getMapType() == mMap.MAP_TYPE_NORMAL){
                   mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
               }
               else {
                   mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
               }
            }
        });

        LatLng latLng = new LatLng(45.05979, 13.88727);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 12);
        mMap.animateCamera(cameraUpdate);

        Drawable dr = getResources().getDrawable(R.drawable.coin_map_picked);
        Bitmap b = ((BitmapDrawable) dr).getBitmap();
        Bitmap bitmapN = Bitmap.createScaledBitmap(b, 60,60, false);

        Drawable gr = getResources().getDrawable(R.drawable.coin_map);
        Bitmap g = ((BitmapDrawable) gr).getBitmap();
        Bitmap bitmapG = Bitmap.createScaledBitmap(g, 60,60, false);

        MarkerOptions markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(bitmapN));

        MarkerOptions greenMarker =new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(bitmapG));
        if (giftRezanci == 0) { mMap.addMarker(markerOptions.position(new LatLng(45.04699, 13.90561)).title("Rezanci")); }
        else{ mMap.addMarker(greenMarker.position(new LatLng(45.04699, 13.90561)).title("Rezanci")); }

       if(giftStokovci == 0){ mMap.addMarker(markerOptions.position(new LatLng(45.04954, 13.89294)).title("Stokovci"));}
        else{ mMap.addMarker(greenMarker.position(new LatLng(45.04954, 13.89294)).title("Stokovci"));}

        if(giftPusti == 0) {mMap.addMarker(markerOptions.position(new LatLng(45.07702, 13.89495)).title("Pusti"));}
        else{mMap.addMarker(greenMarker.position(new LatLng(45.07702, 13.89495)).title("Pusti"));}

        if(giftSmoljanci == 0){ mMap.addMarker(markerOptions.position(new LatLng(45.09188, 13.8432)).title("Smoljanci"));}
        else{ mMap.addMarker(greenMarker.position(new LatLng(45.09188, 13.8432)).title("Smoljanci"));}

        if(giftSavicenta == 0){mMap.addMarker(markerOptions.position(new LatLng(45.08565, 13.88096)).title("Savicenta"));
        }
        else{mMap.addMarker(greenMarker.position(new LatLng(45.08565, 13.88096)).title("Savicenta"));
        }

        if(giftKrancici == 0){mMap.addMarker(markerOptions.position(new LatLng( 45.06884, 13.85956)).title("Krancici"));
        }
        else{mMap.addMarker(greenMarker.position(new LatLng( 45.06884, 13.85956)).title("Krancici"));
        }
       if(giftBibici == 0) { mMap.addMarker(markerOptions.position(new LatLng(45.06433, 13.88289)).title("Bibici"));}
       else{ mMap.addMarker(greenMarker.position(new LatLng(45.06433, 13.88289)).title("Bibici"));}

        if(giftFoli == 0){ mMap.addMarker(markerOptions.position(new LatLng( 45.09823, 13.91845)).title("Foli"));
        }
        else{ mMap.addMarker(greenMarker.position(new LatLng( 45.09823, 13.91845)).title("Foli"));
        }
        if(giftJursici == 0) { mMap.addMarker(markerOptions.position(new LatLng(45.02088, 13.87897)).title("Jursici"));
        }

        else{ mMap.addMarker(greenMarker.position(new LatLng(45.02088, 13.87897)).title("Jursici"));
        }

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
        Log.e("testing","testig");
        walkingMaps = new WalkingMaps(getApplicationContext(),mMap);
        cycleMap = new CycleMaps(getApplicationContext(),mMap);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Log.e("shared"," "+sharedPreferences.getBoolean("Savicentka",true));

        if(sharedPreferences.getBoolean("Savicentka",true)){
            cycleMap.Savicentinka();
        }

        if(sharedPreferences.getBoolean("Roverija",true)){
        cycleMap.Roverija();
        }
        if(sharedPreferences.getBoolean("Savicenta Circular Trail",true)) {
            cycleMap.SavicentaCircularTrail();
        }
        if(sharedPreferences.getBoolean("Kranjcici - Rezanci",true)) {
            cycleMap.KranjciciRezanci();
        }
        if(sharedPreferences.getBoolean("Forest trail",true)) {
            walkingMaps.forestTrail();
        }
        if(sharedPreferences.getBoolean("Jursici",true)) {
            walkingMaps.Jursici();
        }
        if(sharedPreferences.getBoolean("Rezanci",true)) {
            walkingMaps.Rezanci();
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
    }


public void show_maps(View view){
    Intent intent = new Intent(MapsActivity.this,MapSelection.class);
    startActivity(intent);
}
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}