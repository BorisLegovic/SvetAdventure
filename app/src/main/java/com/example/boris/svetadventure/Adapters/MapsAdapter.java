package com.example.boris.svetadventure.Adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.boris.svetadventure.AdapterModels.MapModel;
import com.example.boris.svetadventure.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by boris on 19/06/2016.
 */
public class MapsAdapter extends ArrayAdapter<MapModel> {
      public MapsAdapter(Context context, ArrayList<MapModel> mapModel){
          super(context,0, mapModel);
      }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View getView(int pos, View convertedView, ViewGroup parent){
        MapModel mapModel = getItem(pos);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        if(convertedView == null){
            convertedView = LayoutInflater.from(getContext()).inflate(R.layout.row_map,parent,false);
        }

        RelativeLayout row = (RelativeLayout) convertedView.findViewById(R.id.row);
        final TextView mapName = (TextView) convertedView.findViewById(R.id.map_name);
        ImageView mapImage = (ImageView) convertedView.findViewById(R.id.map_image);
        final ImageView buttonRadio = (ImageView) convertedView.findViewById(R.id.radioButton);

        mapName.setText(mapModel.getMapName());
        if(mapModel.getType() == "C"){
            mapImage.setImageDrawable(getContext().getResources().getDrawable(R.drawable.bicikl));
            if(mapName.getText().toString() == "Savicentka"){
                mapImage.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.savicentinka));
            }
            if(mapName.getText().toString() == "Roverija"){
                mapImage.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.Roverija));
            }
            if(mapName.getText().toString() == "Savicenta Circular Trail"){
                mapImage.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.savicentacirculartrail));
            }
            if(mapName.getText().toString() == "Kranjcici - Rezanci"){
                mapImage.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.KranjciciRezanci));
            }

        }
        else{
            mapImage.setImageDrawable(getContext().getResources().getDrawable(R.drawable.hodanjemod1));
            if(mapName.getText().toString() == "Forest trail"){
                mapImage.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.forestTrail));
            }
            if(mapName.getText().toString() == "Jursici"){
                mapImage.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.Jurisci));
            }
            if(mapName.getText().toString() == "Rezanci"){
                mapImage.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.rezanci));
            }
        }

        if(sharedPreferences.getBoolean(mapName.getText().toString(),true)) {
            buttonRadio.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.map_indicator_background));
        }
        else{
            buttonRadio.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.map_circle_gray));
        }

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(sharedPreferences.getBoolean(mapName.getText().toString(),true)) {
                  sharedPreferences.edit().putBoolean(mapName.getText().toString(), false).apply();
                  buttonRadio.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.map_circle_gray));
              }
                else{
                  sharedPreferences.edit().putBoolean(mapName.getText().toString(), true).apply();
                  buttonRadio.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.map_indicator_background));

              }
            }
        });


        return convertedView;

    }

}
