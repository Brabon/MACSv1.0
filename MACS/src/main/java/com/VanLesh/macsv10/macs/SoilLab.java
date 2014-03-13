package com.VanLesh.macsv10.macs;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by samvanryssegem on 3/12/14.
 */
public class SoilLab {
    private static final String TAG = "SoilLab";
    private static final String FILENAME = "soils.json";

    private static SoilLab sSoilLab;
    private Context mAppcontext;
    private ArrayList<Soil> mSoils;
    private SoilJSONSerializer mSerializer;

    private Context mAppContext;

    private SoilLab(Context appcontext){
        mAppcontext = appcontext;
        mSerializer = new SoilJSONSerializer(mAppcontext, FILENAME);

        try {
            mSoils = mSerializer.loadSoils();
        }catch (Exception e){
            mSoils = new ArrayList<Soil>();
            Log.e(TAG, "Error loading soils:", e);

        }
    }

    public static SoilLab get(Context c){
        if(sSoilLab == null){
            sSoilLab = new SoilLab(c.getApplicationContext());
        }
        return sSoilLab;
    }

    public ArrayList<Soil> getSoils(){
        return mSoils;
    }

    public void addSoil(Soil c){
        mSoils.add(c);
    }

    public void deleteSoil(Soil c){
        mSoils.remove(c);
    }

    public Soil getSoil(String title){
        for(Soil c : mSoils){
            if(c.getName().equals(title))
                return c;
        }
        return null;
    }

    public boolean saveSoils(){
        try {
            mSerializer.saveSoils(mSoils);
            Log.d(TAG,"soils saved");
            return true;
        }catch (Exception e){
            Log.e(TAG,"Error saving",e);
            return false;
        }
    }


}

