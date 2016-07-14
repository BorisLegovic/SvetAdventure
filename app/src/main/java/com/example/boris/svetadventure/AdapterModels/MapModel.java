package com.example.boris.svetadventure.AdapterModels;

/**
 * Created by boris on 19/06/2016.
 */
public class MapModel {
    String mapName;
    String type;

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MapModel(String mapName, String type){
        this.mapName = mapName;
        this.type= type;

    }
}
