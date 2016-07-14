package com.example.boris.svetadventure.Utilitys;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Aylar-HP on 28/09/2015.
 */
public class GPSLocationListener implements android.location.LocationListener {
    public static int GIFT_NUMBER1 = 0;
    public static int GIFT_NUMBER2 = 0;
    public static int GIFT_pusti = 0;
    public static int GIFT_smoljanci = 0;
    public static int GIFT_savicenta = 0;
    public static int GIFT_krancici = 0;
    public static int GIFT_bibici = 0;
    public static int GIFT_foli = 0;
    public static int GIFT_jursici = 0;
    public static int GIFT_stokovci = 0;



    public static double LATITUDE;
    public static int FINAL = 0;
    public static double LONGTITUDE;
    private Circle mCircle;

    @Override
    public void onLocationChanged(Location location)
    {


        CircleOptions rezanci = new CircleOptions()
                .center(new LatLng(45.04699, 13.90561))
                .radius(55);

        CircleOptions Stokovci = new CircleOptions()
                .center(new LatLng(45.04954, 13.89294))
                .radius(55);

        CircleOptions pusti = new CircleOptions()
                .center(new LatLng(45.07702, 13.89495))
                .radius(55);

        CircleOptions smoljanci = new CircleOptions()
                .center(new LatLng(45.09188, 13.8432))
                .radius(55);

        CircleOptions savicenta = new CircleOptions()
                .center(new LatLng(45.08565, 13.88096))
                .radius(55);

        CircleOptions krancici = new CircleOptions()
                .center(new LatLng(45.06884, 13.85956))
                .radius(55);

        CircleOptions bibici = new CircleOptions()
                .center(new LatLng(45.06433, 13.88289))
                .radius(55);

        CircleOptions foli = new CircleOptions()
                .center(new LatLng(45.09823, 13.91845))
                .radius(55);

        CircleOptions jursici = new CircleOptions()
                .center(new LatLng(45.02088, 13.87897))
                .radius(55);


        float[] distance = new float[2];
        if(location != null)
        {

            LATITUDE = location.getLatitude();
            LONGTITUDE = location.getLongitude();

            Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                    rezanci.getCenter().latitude, rezanci.getCenter().longitude, distance);
            if( distance[0] > rezanci.getRadius()  && GIFT_NUMBER1>0){
            } else {
                if(GIFT_NUMBER1 == 0){
                    GIFT_NUMBER1++;
                }
            }

            Location.distanceBetween(location.getLatitude(), location.getLongitude(),
            Stokovci.getCenter().latitude, Stokovci.getCenter().longitude, distance);

        if( distance[0] > Stokovci.getRadius() && GIFT_NUMBER2>0  ){
        } else {
            if(GIFT_NUMBER2 == 0){
                GIFT_NUMBER2++;
            }
        }



            Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                    pusti.getCenter().latitude, pusti.getCenter().longitude, distance);
            if( distance[0] > pusti.getRadius() && GIFT_pusti>0  ){
            } else {
                if(GIFT_pusti == 0){
                    GIFT_pusti++;
                }
            }

            Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                    smoljanci.getCenter().latitude, smoljanci.getCenter().longitude, distance);
            if( distance[0] > smoljanci.getRadius() && GIFT_smoljanci>0  ){
            } else {

                if(GIFT_smoljanci == 0){
                    GIFT_smoljanci++;
                }
            }

            Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                    savicenta.getCenter().latitude, savicenta.getCenter().longitude, distance);
            if( distance[0] > savicenta.getRadius() && GIFT_savicenta>0  ){
            } else {

                if(GIFT_savicenta == 0){
                    GIFT_savicenta++;
                }
            }

            Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                    krancici.getCenter().latitude, krancici.getCenter().longitude, distance);
            if( distance[0] > krancici.getRadius() && GIFT_krancici>0  ){
            } else {

                if(GIFT_krancici == 0){
                    GIFT_krancici++;
                }
            }

            Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                    bibici.getCenter().latitude, bibici.getCenter().longitude, distance);
            if( distance[0] > bibici.getRadius() && GIFT_bibici>0  ){
            } else {

                if(GIFT_bibici == 0){
                    GIFT_bibici++;
                }
            }

            Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                    foli.getCenter().latitude, foli.getCenter().longitude, distance);
            if( distance[0] > foli.getRadius() && GIFT_foli>0  ){
            } else {

                if(GIFT_foli == 0){
                    GIFT_foli++;
                }
            }

            Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                    jursici.getCenter().latitude, jursici.getCenter().longitude, distance);
            if( distance[0] > jursici.getRadius() && GIFT_jursici>0  ){
            } else {

                if(GIFT_jursici == 0){
                    GIFT_jursici++;
                }
            }

            FINAL = GIFT_NUMBER1 + GIFT_NUMBER2 + GIFT_bibici + GIFT_krancici + GIFT_jursici + GIFT_stokovci + GIFT_foli + GIFT_pusti + GIFT_savicenta;
        }
    }


    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

}
