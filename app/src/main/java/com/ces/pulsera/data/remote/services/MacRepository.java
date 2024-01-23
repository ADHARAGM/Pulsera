package com.ces.pulsera.data.remote.services;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.ces.pulsera.data.local.MacDao;
import com.ces.pulsera.data.local.MacPersonaDatabase;
import com.ces.pulsera.herramientas.BeaconReferenceApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MacRepository {
    private static MacRepository instance=null;
    private GetMacService getMacService;
    private Retrofit retrofit;
    private final MacDao macDao;

    public MacRepository() {
        MacPersonaDatabase macPersonaDatabase = Room.databaseBuilder(
                BeaconReferenceApplication.Companion.getContext(),
                MacPersonaDatabase.class,
                "personaDispositivo"
        ).build();

        macDao = macPersonaDatabase.getMacDao();

       retrofit = new Retrofit.Builder()
               .baseUrl("https://servicios.cesmorelos.gob.mx/Pulseras/")
               .addConverterFactory(GsonConverterFactory.create())
               .build();
        getMacService= retrofit.create(GetMacService.class);
    }

  public static MacRepository getInstance(){
        if(instance==null){
            instance= new MacRepository();
        }
        return instance;
    }

    public GetMacService getMacService(){
        return getMacService;
    }
}
