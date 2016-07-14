package com.example.boris.svetadventure;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.boris.svetadventure.AdapterModels.MapModel;
import com.example.boris.svetadventure.Adapters.MapsAdapter;
import com.splunk.mint.Mint;

import java.util.ArrayList;

/**
 * Created by boris on 21/03/2016.
 */
public class MapSelection extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_map);
        Mint.initAndStartSession(MapSelection.this, "f241ce49");
        ArrayList<MapModel> array = new ArrayList<MapModel>();
        MapsAdapter adapter = new MapsAdapter(this,array);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        addCycleMaps(adapter);
        addRunMaps(adapter);

    }
    public void addCycleMaps(MapsAdapter adapter){ // add cycle maps to list
        MapModel[] mapa = new MapModel[4];
        mapa[0] = new MapModel("Savičentinka","C");
        mapa[1] = new MapModel("Roverija","C");
        mapa[2] = new MapModel("Savicenta Circular Trail","C");
        mapa[3] = new MapModel("Kranjčići - Režanci","C");
        for(int i=0;i< mapa.length;i++){
            adapter.add(mapa[i]);
        }
    }

    public void addRunMaps(MapsAdapter adapter){ // add run maps to list
        MapModel[] mapa = new MapModel[3];
        mapa[0] = new MapModel("Forest trail","R");
        mapa[1] = new MapModel("Jursici","R");
        mapa[2] = new MapModel("Rezanci","R");
        for(int i=0;i< mapa.length;i++){
            adapter.add(mapa[i]);
        }
    }
    @Override
    public void onBackPressed()
    {
      Intent intent = new Intent(this,MapsActivity.class);
      startActivity(intent);
        finish();
    }
}